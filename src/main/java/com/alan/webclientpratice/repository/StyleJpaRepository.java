package com.alan.webclientpratice.repository;

import com.alan.webclientpratice.dto.match.entity.StyleDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StyleJpaRepository extends JpaRepository<StyleDto,Long> {
}