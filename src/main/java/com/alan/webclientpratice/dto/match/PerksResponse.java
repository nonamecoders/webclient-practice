package com.alan.webclientpratice.dto.match;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class PerksResponse implements Serializable {

    private PerStatsDto statPerks;
    private List<PerkStyleDto> styles;
    @Data
    public static class PerStatsDto implements Serializable {
        private Integer defense;
        private Integer flex;
        private Integer offense;
    }
    @Data
    public static class PerkStyleDto implements Serializable {
        private String description;
        private List<PerkStyleSelectionDto> selections;
        private Integer style;
    }

    @Data
    public static class PerkStyleSelectionDto implements Serializable {
        private Integer perk;
        private Integer var1;
        private Integer var2;
        private Integer var3;
    }

}
