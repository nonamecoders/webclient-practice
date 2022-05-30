package com.alan.webclientpratice.service;

import com.alan.webclientpratice.dto.*;
import com.alan.webclientpratice.repository.ChampionJpaRepository;
import com.alan.webclientpratice.repository.SummonerJpaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.EntityManagerFactory;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ApiService {

    @Autowired
    private ApiCache apiCache;
    final WebClient webClient;

    final
    SummonerJpaRepository summonerJpaRepository;

    @Value("${api.baseUrl}")
    private String baseUrl;

    @Value("${api.matchBaseUrl}")
    private String matchBaseUrl;

    @Value("${api.matchUrl}")
    private String matchUrl;

    @Value("${api.searchUrl}")
    private String searchUrl;

    @Value("${api.rankUrl}")
    private String rankUrl;

    @Value("${api.masteryUrl}")
    private String masteryUrl;

    final EntityManagerFactory entityManagerFactory;

    final
    ChampionJpaRepository championJpaRepository;

    public ApiService(WebClient webClient, EntityManagerFactory entityManagerFactory, ChampionJpaRepository championJpaRepository, SummonerJpaRepository summonerJpaRepository) {
        this.webClient = webClient;
        this.entityManagerFactory = entityManagerFactory;
        this.championJpaRepository = championJpaRepository;
        this.summonerJpaRepository = summonerJpaRepository;
    }

    @Transactional(readOnly = true)
    public SummonerResponse getSummoner(String summonerName) throws JsonProcessingException {

        SummonerResponse response;
        SummonerDto data = apiCache.getSummonerData(summonerName);

        log.info("data : {}", data);

        if(data != null){
            response = SummonerResponse.builder()
                    .id(data.getEncryptedId())
                    .puuid(data.getPuuid())
                    .accountId(data.getAccountId())
                    .name(data.getName())
                    .summonerLevel(data.getSummonerLevel())
                    .profileIconId(data.getProfileIconId())
                    .revisionDate(data.getRevisionDate())
                    .build();
            return response;

        } else {

            response = webClient.mutate()
                    .build()
                    .get()
                    .uri(baseUrl + searchUrl, summonerName)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            clientResponse -> clientResponse.bodyToMono(String.class).map(body -> new RuntimeException(body)))
                    .bodyToMono(SummonerResponse.class)
                    .block();

            summonerJpaRepository.save(SummonerDto.builder()
                    .encryptedId(response.getId())
                    .accountId(response.getAccountId())
                    .puuid(response.getPuuid())
                    .name(response.getName().replaceAll(" ","").toLowerCase())
                    .profileIconId(response.getProfileIconId())
                    .revisionDate(response.getRevisionDate())
                    .summonerLevel(response.getSummonerLevel())
                    .build());
        }

        return response;
    }

    public List<RankResponse> getSummonerRank(String encryptedSummonerId) throws JsonProcessingException {

        List<RankResponse> response = webClient.mutate()
                .build()
                .get()
                .uri(baseUrl+rankUrl,encryptedSummonerId)
                .retrieve()
                .bodyToFlux(RankResponse.class)
                .collectList()
                .block();

        return response;
    }

    public SummonerInfo getUserInfo(String summonerName) throws Exception {

        List<SummonerInfo.SummonerRank> rankList = new ArrayList<>();
        //userInfo
        SummonerResponse user = getSummoner(summonerName);
        String encryptedId = user.getId();

        List<RankResponse> rankInfo = getSummonerRank(encryptedId);
        List<Mastery> masteries = getMastery(encryptedId);

        for(RankResponse response : rankInfo) {
            rankList.add(SummonerInfo.SummonerRank.builder()
                    .queueType(response.getQueueType())
                    .rank(response.getTier() + " " + response.getRank())
                    .leaguePoints(response.getLeaguePoints())
                    .wins(response.getWins())
                    .losses(response.getLosses())
                    .build());
        }

        SummonerInfo info =  SummonerInfo.builder()
                .id(user.getId())
                .name(user.getName())
                .level(user.getSummonerLevel())
                .profileIconId(user.getProfileIconId())
                .revisionDate(user.getRevisionDate())
                .rank(rankList)
                .masteries(masteries)
                .build();

        return info;

    }

    public List<SummonerInfo> getMultiSearch(String keyword) throws Exception {
        List<SummonerInfo> result = new ArrayList<>();

        String[] arr = Arrays.stream(keyword.split("\n"))
                .filter(s-> s.contains("님이"))
                .map(s -> s.split("님이")[0])
                .toArray(String[]::new);

        for(String nickname : arr){
            result.add(getUserInfo(nickname));
        }

        return result;
    }

    public List<Mastery> getMastery(String encryptedSummonerId) throws Exception{
        List<Mastery> response = webClient.mutate()
                .build()
                .get()
                .uri(baseUrl+masteryUrl,encryptedSummonerId)
                .retrieve()
                .bodyToFlux(Mastery.class)
                .collectList()
                .block();

        response.stream().limit(5).forEach(r->{
            ChampionDto dto = championJpaRepository.getById(r.getChampionId());
            Mastery.ChampionDetail detail = Mastery.ChampionDetail.builder()
                    .ChampionId(dto.getChampionId())
                    .name(dto.getName())
                    .title(dto.getTitle())
                    .imageFullUrl(dto.getImageFullUrl())
                    .imageSprite(dto.getImageSprite())
                    .tags(dto.getTags())
                    .build();
            r.setChampion(detail);
        });

        return response;
    }

    public String getChampion() throws Exception{

        String response = webClient.mutate()
                .build()
                .get()
                .uri("https://ddragon.leagueoflegends.com/cdn/12.10.1/data/ko_KR/champion.json")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Map<String,Map<String,Object>> map = new ObjectMapper().readValue(response,HashMap.class);
        Map<String,Object> innerMap = map.get("data");

        Champion champion = new Champion();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        for(Map.Entry<String, Object> elem : innerMap.entrySet()){

            champion = mapper.convertValue(elem.getValue(),Champion.class);
            ChampionDto dto = ChampionDto.builder()
                    .championId(champion.getId())
                    .championKey(champion.getKey())
                    .name(champion.getName())
                    .title(champion.getTitle())
                    .tags(champion.getTags().stream().map(String::valueOf).collect(Collectors.joining(",")))
                    .imageFullUrl(champion.getImage().getFull())
                    .imageSprite(champion.getImage().getSprite())
                    .build();

            championJpaRepository.save(dto);

        }

        return response;
    }

    public List<String> getMatchList(String puuid) {

        List<String> response = webClient.mutate()
                .build()
                .get()
                .uri(matchBaseUrl+matchUrl,puuid,0,10)
                .retrieve()
                .bodyToFlux(String.class)
                .collectList()
                .block();

        return response;

    }

}
