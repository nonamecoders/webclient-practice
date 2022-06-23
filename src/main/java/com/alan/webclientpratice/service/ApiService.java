package com.alan.webclientpratice.service;

import com.alan.webclientpratice.dto.*;
import com.alan.webclientpratice.dto.match.MatchResponse;
import com.alan.webclientpratice.dto.match.ParticipantResponse;
import com.alan.webclientpratice.dto.match.entity.MatchInfoDto;
import com.alan.webclientpratice.dto.match.entity.MatchMetaDataDto;
import com.alan.webclientpratice.dto.perk.PerkDto;
import com.alan.webclientpratice.repository.ChampionJpaRepository;
import com.alan.webclientpratice.repository.PerkJpaRepository;
import com.alan.webclientpratice.repository.RankJpaRepository;
import com.alan.webclientpratice.repository.SummonerJpaRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ApiService {

    final ApiCache apiCache;
    final WebClient webClient;

    final SummonerJpaRepository summonerJpaRepository;

    final RankJpaRepository rankJpaRepository;

    final PerkJpaRepository perkJpaRepository;

    @Value("${api.baseUrl}")
    private String baseUrl;

    @Value("${api.matchBaseUrl}")
    private String matchBaseUrl;

    @Value("${api.matchIdUrl}")
    private String matchIdUrl;

    @Value("${api.matchInfoUrl}")
    private String matchInfoUrl;

    @Value("${api.masteryUrl}")
    private String masteryUrl;

    @Value("${api.perkInfoUrl}")
    private String perkInfoUrl;

    final EntityManagerFactory entityManagerFactory;

    final
    ChampionJpaRepository championJpaRepository;

    public ApiService(WebClient webClient, EntityManagerFactory entityManagerFactory, ChampionJpaRepository championJpaRepository, SummonerJpaRepository summonerJpaRepository,RankJpaRepository rankJpaRepository,PerkJpaRepository perkJpaRepository,ApiCache apiCache) {
        this.webClient = webClient;
        this.entityManagerFactory = entityManagerFactory;
        this.championJpaRepository = championJpaRepository;
        this.summonerJpaRepository = summonerJpaRepository;
        this.rankJpaRepository = rankJpaRepository;
        this.perkJpaRepository = perkJpaRepository;
        this.apiCache = apiCache;
    }

    public SummonerResponse getSummoner(String summonerName,String commnad) throws Exception{

        SummonerResponse response = apiCache.getSummonerData(summonerName, commnad);

        return response;

    }

    public List<RankResponse> getSummonerRank(String encryptedSummonerId) {

        List<RankResponse> response = apiCache.getRankData(encryptedSummonerId);

        return response;
    }

    public SummonerInfo getUserInfo(String summoner,String command) throws Exception {

        List<SummonerInfo.SummonerRank> rankList = new ArrayList<>();
        //userInfo
        SummonerResponse user = getSummoner(summoner,command);
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
            result.add(getUserInfo(nickname,"summonerName"));
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
                .toStream()
                .limit(5)
                .collect(Collectors.toList());

        response.forEach(r->{
            ChampionResponse championResponse = apiCache.getChampion(r.getChampionId());
            Mastery.ChampionDetail detail = Mastery.ChampionDetail.builder()
                    .ChampionId(championResponse.getChampionId())
                    .name(championResponse.getName())
                    .title(championResponse.getTitle())
                    .imageFullUrl(championResponse.getImageFullUrl())
                    .imageSprite(championResponse.getImageSprite())
                    .tags(String.join(",", championResponse.getTags()))
                    .build();
            r.setChampion(detail);
        });

        return response;
    }

    public String getChampion() throws Exception{

        String response = webClient.mutate()
                .build()
                .get()
                .uri("http://ddragon.leagueoflegends.com/cdn/12.11.1/data/ko_KR/champion.json")
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

    public MatchResponse getMatchList(String puuid) throws Exception {

        String[] response = webClient.mutate()
                .build()
                .get()
                .uri(matchBaseUrl+ matchIdUrl,puuid,0,10)
                .retrieve()
                .bodyToMono(String[].class)
                .block();

        List<String> result = Arrays.asList(response);

        return getMatchInfo(result.get(0));

//        return result;

    }

    public List<ChampionResponse> getChampionList(List<String> tag){
        List<ChampionResponse> result= apiCache.getChampionList();

        //sorting
        if(tag != null) {
            for(String s : tag){
                result = result.stream().filter(r-> Arrays.stream(r.getTags()).anyMatch(rr->rr.equals(s))).collect(Collectors.toList());
            }
        }

        return result;
    }

    public MatchResponse getMatchInfo(String matchId) throws Exception{

        MatchResponse response = webClient.mutate()
                .build()
                .get()
                .uri(matchBaseUrl+ matchInfoUrl,matchId)
                .retrieve()
                .bodyToMono(MatchResponse.class)
                .block();

        MatchMetaDataDto metaDataDto = MatchMetaDataDto.builder()
                .matchId(response.getMetadata().getMatchId())
                .dataVersion(response.getMetadata().getDataVersion())
                .participants(response.getMetadata().getParticipants())
                .build();
        List<MatchInfoDto> matchInfoDtoList = new ArrayList<>();

        return response;
    }


    public List<PerkDto> getPerkInfo() {

        List<PerkDto> response = webClient.mutate()
                .build()
                .get()
                .uri(perkInfoUrl)
                .retrieve()
                .bodyToFlux(PerkDto.class)
                .collectList()
                .block();

        perkJpaRepository.saveAll(response);

        return response;
    }

    public void mergeMatchData () {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //todo

        } catch (Exception e){
            log.info("error message : {}", e.getMessage());
        }
    }
}