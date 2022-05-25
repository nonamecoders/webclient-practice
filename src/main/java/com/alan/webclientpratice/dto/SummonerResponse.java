package com.alan.webclientpratice.dto;

import lombok.Data;

@Data
public class SummonerResponse {

    private String id;
    private String puuid;
    private String accountId;
    private String name;
    private Long summonerLevel;
    private Integer profileIconId;
    private Long revisionDate;

}
