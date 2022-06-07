package com.alan.webclientpratice.service;

import com.alan.webclientpratice.dto.RankDto;
import com.alan.webclientpratice.dto.SummonerDto;
import com.alan.webclientpratice.repository.RankJpaRepository;
import com.alan.webclientpratice.repository.SummonerJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ApiCache {

    @Autowired
    SummonerJpaRepository summonerJpaRepository;

    @Autowired
    RankJpaRepository rankJpaRepository;

    @Cacheable(value = "summoner",key = "#summonerName")
    public SummonerDto getSummonerData(String summonerName) {
        SummonerDto data = summonerJpaRepository.findByName(summonerName.replaceAll(" ",""));
        log.info("data : {}", data);

        return data;
    }

    @Cacheable(value = "rank",key = "#summonerId")
    public List<RankDto> getRankData(String summonerId) {
        List<RankDto> data = rankJpaRepository.findBySummonerId(summonerId);
        log.info("data : {}", data);

        return data;
    }

}
