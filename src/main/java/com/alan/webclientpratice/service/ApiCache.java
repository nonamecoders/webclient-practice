package com.alan.webclientpratice.service;

import com.alan.webclientpratice.dto.*;
import com.alan.webclientpratice.mapper.ChampionMapper;
import com.alan.webclientpratice.repository.ChampionJpaRepository;
import com.alan.webclientpratice.repository.RankJpaRepository;
import com.alan.webclientpratice.repository.SummonerJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ApiCache {

    @Autowired
    WebClient webClient;

    @Autowired
    SummonerJpaRepository summonerJpaRepository;

    @Autowired
    RankJpaRepository rankJpaRepository;

    @Autowired
    ChampionJpaRepository championJpaRepository;

    @Value("${api.baseUrl}")
    private String baseUrl;

    @Value("${api.matchBaseUrl}")
    private String matchBaseUrl;

    @Value("${api.matchUrl}")
    private String matchUrl;

    @Value("${api.summonerNameSearchUrl}")
    private String summonerNameSearchUrl;

    @Value("${api.puuidSearchUrl}")
    private String puuidSearchUrl;

    @Value("${api.rankUrl}")
    private String rankUrl;

    @Value("${api.masteryUrl}")
    private String masteryUrl;

    public SummonerResponse getSummonerData(String summoner,String command) throws RuntimeException, ParseException {
        String searchUrl = "";
        SummonerDto data = null;
        SummonerResponse response;

        if(command.equals("summonerName")) {
            data = summonerJpaRepository.findByName(summoner.replaceAll(" ",""));
            searchUrl=summonerNameSearchUrl;
        } else if (command.equals("puuid")) {
            data = summonerJpaRepository.findByPuuid(summoner);
            searchUrl = puuidSearchUrl;
        }

        long currentTime = System.currentTimeMillis();

        if(data != null && currentTime - data.getRevisionDate() < 86400l){

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
                    .uri(baseUrl + searchUrl, summoner)
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

    @Cacheable(value = "rank",key = "#encryptedSummonerId")
    public List<RankResponse> getRankData(String encryptedSummonerId) {
        List<RankResponse> response = new ArrayList<>();

            response = webClient.mutate()
                    .build()
                    .get()
                    .uri(baseUrl + rankUrl, encryptedSummonerId)
                    .retrieve()
                    .bodyToFlux(RankResponse.class)
                    .collectList()
                    .block();

            for (RankResponse rank : response) {
                rankJpaRepository.save(RankDto.builder()
                        .leagueId(rank.getLeagueId())
                        .queueType(rank.getQueueType())
                        .tier(rank.getTier())
                        .rank(rank.getRank())
                        .summonerId(rank.getSummonerId())
                        .summonerName(rank.getSummonerName())
                        .leaguePoints(rank.getLeaguePoints())
                        .wins(rank.getWins())
                        .losses(rank.getLosses())
                        .hotStreak(rank.getHotStreak())
                        .veteran(rank.getVeteran())
                        .freshBlood(rank.getFreshBlood())
                        .inactive(rank.getInactive())
                        .build());
            }

        return response;
    }

    @Cacheable(value = "champion")
    public List<ChampionResponse> getChampionList() {
        List<ChampionDto> data = this.championJpaRepository.findAll();
        List<ChampionResponse> result = new ArrayList<>();
        data.forEach(
                c-> {
                    result.add(ChampionMapper.INSTANCE.toChampionResponse(c));
                }
        );

        log.info("data : {}", result);

        return result;
    }

    @Cacheable(value = "champion", key = "#id")
    public ChampionResponse getChampion(Long id) {
        ChampionDto data = this.championJpaRepository.findById(id)
                .orElseGet(ChampionDto::new);
        ChampionResponse result = ChampionMapper.INSTANCE.toChampionResponse(data);
        log.info("data : {}", result);

        return result;
    }

}
