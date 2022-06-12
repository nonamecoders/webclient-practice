package com.alan.webclientpratice.service;

import com.alan.webclientpratice.dto.*;
import com.alan.webclientpratice.repository.ChampionJpaRepository;
import com.alan.webclientpratice.repository.RankJpaRepository;
import com.alan.webclientpratice.repository.SummonerJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

    @Value("${api.searchUrl}")
    private String searchUrl;

    @Value("${api.rankUrl}")
    private String rankUrl;

    @Value("${api.masteryUrl}")
    private String masteryUrl;

    @Cacheable(value = "summoner",key = "#summonerName")
    public SummonerResponse getSummonerData(String summonerName) throws RuntimeException{
        SummonerDto data = summonerJpaRepository.findByName(summonerName.replaceAll(" ",""));
        SummonerResponse response;

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
    public List<ChampionDto> getChampionList() {
        List<ChampionDto> data = championJpaRepository.findAll();
        log.info("data : {}", data);

        return data;
    }

    @Cacheable(value = "champion", key = "#id")
    public ChampionDto getChampion(Long id) {
        ChampionDto data = championJpaRepository.findById(id)
                .orElseGet(ChampionDto::new);
        log.info("data : {}", data);

        return data;
    }

}
