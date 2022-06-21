package com.alan.webclientpratice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SummonerResponse implements Serializable {

    private String id;
    private String puuid;
    private String accountId;
    private String name;
    private Long summonerLevel;
    private Integer profileIconId;
    private Long revisionDate;

    @Builder
    public SummonerResponse(String id, String puuid, String accountId, String name, Long summonerLevel, Integer profileIconId, Long revisionDate) {
        this.id = id;
        this.puuid = puuid;
        this.accountId = accountId;
        this.name = name;
        this.summonerLevel = summonerLevel;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
    }
}
