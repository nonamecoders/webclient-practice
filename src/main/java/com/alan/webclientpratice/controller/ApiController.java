package com.alan.webclientpratice.controller;

import com.alan.webclientpratice.dto.RankResponse;
import com.alan.webclientpratice.dto.SummonerInfo;
import com.alan.webclientpratice.dto.SummonerResponse;
import com.alan.webclientpratice.service.ApiService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("summoner")
    public SummonerResponse getSummoner(@RequestParam("summonerName") String summonerName) throws Exception {

        return apiService.getSummoner(summonerName);

    }

    @GetMapping("rank")
    public List<RankResponse> getSummonerRank(@RequestParam("encryptedSummonerId") String encryptedSummonerId) throws Exception {

        List<RankResponse> result = apiService.getSummonerRank(encryptedSummonerId);

        return result;

    }

    @GetMapping("search")
    public SummonerInfo getSummonerInfo(@RequestParam("nickname") String nickname) throws Exception {

        return apiService.getUserInfo(nickname);

    }

    @PostMapping("multiSearch")
    public List<SummonerInfo> getMultiSearch(@RequestBody String keyword) throws Exception{

        return apiService.getMultiSearch(keyword);

    }

}
