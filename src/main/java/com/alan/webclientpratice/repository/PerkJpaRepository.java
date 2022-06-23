package com.alan.webclientpratice.repository;

import com.alan.webclientpratice.dto.perk.PerkDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerkJpaRepository extends JpaRepository<PerkDto,Long> {
}
