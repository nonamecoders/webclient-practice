package com.alan.webclientpratice.repository;

import com.alan.webclientpratice.dto.match.ChallengeResponse;
import com.alan.webclientpratice.dto.match.entity.MatchInfoDto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface MatchInfoJpaRepository extends JpaRepository<MatchInfoDto, Long> {

    @EntityGraph("MatchInfoWithMetaData")
    List<MatchInfoDto> findAll();

    @EntityGraph("MatchInfoWithMetaData")
    Optional<MatchInfoDto> findById(Long id);

    List<MatchInfoDto> findTop10ByPuuid(String puuid);

    @Query(value = "select m.challenges as challenges from MatchInfoDto m where m.id =:id")
    ChallengeResponse findChallenges(@Param("id") Long id);

}
