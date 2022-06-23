package com.alan.webclientpratice.dto.match;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class PerksResponse implements Serializable{

    private PerStatsDto statPerks;
    private List<PerkStyleDto> styles;
    @Data
    public static class PerStatsDto {
        private Integer defense;
        private Integer flex;
        private Integer offense;
    }
    @Data
    public static class PerkStyleDto {
        private String description;
        private List<PerkStyleSelectionDto> selections;
        private Integer style;
    }
    @Data
    public static class PerkStyleSelectionDto {
        private Integer perk;
        private Integer var1;
        private Integer var2;
        private Integer var3;
    }

}
