package com.alan.webclientpratice.service;

import com.alan.webclientpratice.dto.RankResponse;
import com.alan.webclientpratice.dto.SummonerInfo;
import com.alan.webclientpratice.dto.SummonerResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ApiService {

    final WebClient webClient;

    @Value("${api.searchUrl}")
    private String searchUrl;

    @Value("${api.rankUrl}")
    private String rankUrl;

    public ApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public SummonerResponse getSummoner(String summonerName) throws JsonProcessingException {

        SummonerResponse response = webClient.mutate()
                .build()
                .get()
                .uri(searchUrl,summonerName)
                .retrieve()
                .bodyToMono(SummonerResponse.class)
                .block();


        return response;
    }

    public List<RankResponse> getSummonerRank(String encryptedSummonerId) throws JsonProcessingException {

        List<RankResponse> response = webClient.mutate()
                .build()
                .get()
                .uri(rankUrl,encryptedSummonerId)
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

        for(RankResponse response : rankInfo) {
            rankList.add(SummonerInfo.SummonerRank.builder()
                    .queueType(response.getQueueType())
                    .rank(response.getTier() + " " + response.getRank())
                    .wins(response.getWins())
                    .losses(response.getLosses())
                    .build());
        }

        SummonerInfo info =  SummonerInfo.builder()
                .id(user.getId())
                .name(user.getName())
                .level(user.getSummonerLevel())
                .profileIconId(user.getProfileIconId())
                .revisionDate(LocalDateTime.now())
                .rank(rankList).build();

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

}
