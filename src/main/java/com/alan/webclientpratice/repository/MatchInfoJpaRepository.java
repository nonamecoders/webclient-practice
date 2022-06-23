package com.alan.webclientpratice.repository;

import com.alan.webclientpratice.dto.MatchInfoPk;
import com.alan.webclientpratice.dto.match.entity.MatchInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchInfoJpaRepository extends JpaRepository<MatchInfoDto, MatchInfoPk> {
}
