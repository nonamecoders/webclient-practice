package com.alan.webclientpratice.controller;

import com.alan.webclientpratice.dto.*;
import com.alan.webclientpratice.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("summoner/{command}")
    public ResponseEntity<SummonerResponse> getSummoner(@PathVariable("command") String command,@RequestParam("summoner") String summoner) throws Exception {

        return ResponseEntity.ok()
                .body(apiService.getSummoner(summoner,command));

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

    @GetMapping("search/{command}")
    public SummonerInfo getSummonerInfo(@PathVariable("command")String command, @RequestParam("summoner") String summoner) throws Exception {

        return apiService.getUserInfo(summoner,command);

    }

    @PostMapping("multiSearch")
    public ResponseEntity<List<SummonerInfo>> getMultiSearch(@RequestBody String keyword) throws Exception{

        return ResponseEntity.ok()
                        .body(apiService.getMultiSearch(keyword));

    }

    @GetMapping("championFromRiot")
    public String getChampion() throws Exception {
        return apiService.getChampion();
    }

    @GetMapping("matchid")
    public List<String> getMatchList(@RequestParam("puuid")String puuid) throws Exception{
        return apiService.getMatchList(puuid);
    }

    @GetMapping("champion")
    public List<ChampionResponse> getChampionList(@RequestParam(value = "tag",required = false) List<String> tag){

        log.info("s : {}", tag);

        return apiService.getChampionList(tag);
    }

    @CacheEvict(value = "champion", allEntries = true)
    @GetMapping("evict")
    public String cacheEvict(){

        return "delete";
    }
}
