package com.alan.webclientpratice.dto;

import lombok.Builder;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

@Data
public class Mastery {

    private Long championId;
    private Integer championLevel;
    private Integer championPoints;
    private Long lastPlayTime;
    private Long championPointsSinceLastLevel;
    private Long championPointsUntilNextLevel;
    private Boolean chestGranted;
    private Integer tokensEarned;

    @JsonIgnore
    private String summonerId;

    private ChampionDetail champion;

    @Data
    @Builder
    public static class ChampionDetail {

        private String ChampionId;
        private String name;
        private String title;
        private String imageFullUrl;
        private String imageSprite;
        private String tags;

    }

}
