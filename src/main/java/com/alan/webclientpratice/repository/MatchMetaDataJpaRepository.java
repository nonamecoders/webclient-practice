package com.alan.webclientpratice.repository;

import com.alan.webclientpratice.dto.match.entity.MatchMetaDataDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MatchMetaDataJpaRepository extends JpaRepository<MatchMetaDataDto,Long> {

    MatchMetaDataDto findByMatchId(String matchId);

}
