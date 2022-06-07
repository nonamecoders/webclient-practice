package com.alan.webclientpratice.repository;


import com.alan.webclientpratice.dto.RankDto;
import com.alan.webclientpratice.dto.RankPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface RankJpaRepository extends JpaRepository<RankDto, RankPk> {

//    @Transactional
//    @Query("select m from RankDto m where m.summonerId = :summoner_id")
//    List<RankDto> getSummonerRank(@Param("summoner_id") String summonerId);

    List<RankDto> findBySummonerId(String SummonerId);

}
