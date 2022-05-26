package com.alan.webclientpratice.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SummonerInfo {

    //info
    private String id;
    private String name;
    private Long level;
    private Integer profileIconId;
    private LocalDateTime revisionDate;

    private List<SummonerRank> rank;
    private List<Mastery> masteries;


    //rank

    @Data
    @Builder
    public static class SummonerRank {
        private String queueType;
        private String rank;
        private Integer leaguePoints;
        private Integer wins;
        private Integer losses;

    }

}
