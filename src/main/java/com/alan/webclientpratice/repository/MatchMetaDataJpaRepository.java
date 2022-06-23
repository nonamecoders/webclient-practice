package com.alan.webclientpratice.repository;

import com.alan.webclientpratice.dto.match.entity.MatchMetaDataDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchMetaDataJpaRepository extends JpaRepository<MatchMetaDataDto,Long> {
}
