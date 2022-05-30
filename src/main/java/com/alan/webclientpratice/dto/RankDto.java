package com.alan.webclientpratice.dto;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@Table(name ="RANK")
public class RankDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column

    private String leagueId;
    @Column

    private String queueType;
    @Column

    private String tier;
    @Column

    private String rank;
    @Column
    private String summonerId;

    @Column
    private String summonerName;

    @Column
    private Integer leaguePoints;

    @Column
    private Integer wins;

    @Column
    private Integer losses;

    @Column
    private Boolean veteran;

    @Column
    private Boolean inactive;

    @Column
    private Boolean freshBlood;

    @Column
    private Boolean hotStreak;


}
