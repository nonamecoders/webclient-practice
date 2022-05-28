package com.alan.webclientpratice.controller;

import com.alan.webclientpratice.dto.Mastery;
import com.alan.webclientpratice.dto.RankResponse;
import com.alan.webclientpratice.dto.SummonerInfo;
import com.alan.webclientpratice.dto.SummonerResponse;
import com.alan.webclientpratice.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("summoner")
    public ResponseEntity<SummonerResponse> getSummoner(@RequestParam("summonerName") String summonerName) throws Exception {

        return ResponseEntity.ok()
                .body(apiService.getSummoner(summonerName));

    }

    @GetMapping("rank")
    public List<RankResponse> getSummonerRank(@RequestParam("encryptedSummonerId") String encryptedSummonerId) throws Exception {

        List<RankResponse> result = apiService.getSummonerRank(encryptedSummonerId);

        return result;

    }

    @GetMapping("mastery")
    public List<Mastery> getMastery(@RequestParam("encryptedSummonerId") String encryptedSummonerId) throws Exception{

        return apiService.getMastery(encryptedSummonerId);
    }

    @GetMapping("search")
    public SummonerInfo getSummonerInfo(@RequestParam("nickname") String nickname) throws Exception {

        return apiService.getUserInfo(nickname);

    }

    @PostMapping("multiSearch")
    public ResponseEntity<List<SummonerInfo>> getMultiSearch(@RequestBody String keyword) throws Exception{

        return ResponseEntity.ok()
                        .body(apiService.getMultiSearch(keyword));

    }

    @GetMapping("champion")
    public String getChampion() throws Exception {
        return apiService.getChampion();
    }

    @GetMapping("matchid")
    public List<String> getMatchList(@RequestParam("puuid")String puuid) throws Exception{
        return apiService.getMatchList(puuid);
    }

}
