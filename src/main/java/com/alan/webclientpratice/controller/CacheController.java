package com.alan.webclientpratice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CacheController {

    @CacheEvict(value = "champion", allEntries = true)
    @GetMapping("evict/champion")
    public String cacheEvict(){

        log.info("champion cache removed");

        return "delete";
    }

    @CacheEvict(value = "rank", allEntries = true)
    @GetMapping("evict/rank")
    public String cacheEvictRank(){

        log.info("rank cache removed");

        return "delete";
    }
}
