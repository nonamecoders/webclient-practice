package com.alan.webclientpratice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Type;
import java.util.HashMap;

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

    public HashMap<String,Object> getSummoner(String summonerName) throws JsonProcessingException {

        Gson gson = new Gson();
        String response = webClient.mutate()
                .build()
                .get()
                .uri(searchUrl,summonerName)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        HashMap<String,Object> responseMap = new ObjectMapper().readValue(response,HashMap.class);

        log.info("id : {}", responseMap.get("id").toString());
        log.info("name : {}",responseMap.get("name").toString());
        log.info("summonerLevel : {}",responseMap.get("summonerLevel").toString());

        log.info(gson.toJson(response));

        return responseMap;
    }

    public String getSummonerRank(String encryptedSummonerId) throws JsonProcessingException {

        String response = webClient.mutate()
                .build()
                .get()
                .uri(rankUrl,encryptedSummonerId)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }

}
