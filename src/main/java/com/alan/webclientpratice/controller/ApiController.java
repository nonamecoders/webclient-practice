package com.alan.webclientpratice.controller;

import com.alan.webclientpratice.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("search")
    public HashMap<String, Object> getSummoner(@RequestParam("summonerName") String summonerName) throws Exception {

        return apiService.getSummoner(summonerName);

    }

    @GetMapping("rank")
    public String getSummonerRank(@RequestParam("encryptedSummonerId") String encryptedSummonerId) throws Exception {

        return apiService.getSummonerRank(encryptedSummonerId);

    }
}
