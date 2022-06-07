package com.alan.webclientpratice.dto;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name ="SUMMONER")
public class SummonerDto {

    @Column(name = "encrypted_id")
    private String encryptedId;

    @Column(name="account_id")
    private String accountId;

    @Column
    private String puuid;

    @Id
    @Column
    private String name;

    @Column(name = "profile_icon_id")
    private Integer profileIconId;

    @Column(name = "revision_date")
    private Long revisionDate;

    @Column(name = "summoner_level")
    private Long summonerLevel;

    @Builder
    public SummonerDto(String encryptedId, String accountId, String puuid, String name, Integer profileIconId, Long revisionDate, Long summonerLevel) {
        this.encryptedId = encryptedId;
        this.accountId = accountId;
        this.puuid = puuid;
        this.name = name;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
    }
}
