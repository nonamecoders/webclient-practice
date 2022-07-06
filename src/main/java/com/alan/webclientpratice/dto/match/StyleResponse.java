package com.alan.webclientpratice.dto.match;

import com.alan.webclientpratice.dto.match.entity.StyleDto;
import lombok.Data;

import java.util.List;

@Data
public class StyleResponse {
    private Integer schemaVersion;
    private List<StyleDto> styles;
}