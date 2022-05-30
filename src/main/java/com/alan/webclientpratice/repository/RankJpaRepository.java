package com.alan.webclientpratice.repository;


import com.alan.webclientpratice.dto.RankDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankJpaRepository extends JpaRepository<RankDto,Long> {

}
