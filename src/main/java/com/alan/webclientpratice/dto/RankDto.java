package com.alan.webclientpratice.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@IdClass(RankPk.class)
@RequiredArgsConstructor
@Table(name ="RANK")
public class RankDto implements Serializable {

    private static final long serialVersionUID = 1l;

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column
    private String leagueId;

    @Id
    @Column
    private String queueType;

    @Column
    private String tier;
    @Column

    private String rank;

    @Id
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

    @Builder
    public RankDto(String leagueId, String queueType, String tier, String rank, String summonerId, String summonerName, Integer leaguePoints, Integer wins, Integer losses, Boolean veteran, Boolean inactive, Boolean freshBlood, Boolean hotStreak) {
        this.leagueId = leagueId;
        this.queueType = queueType;
        this.tier = tier;
        this.rank = rank;
        this.summonerId = summonerId;
        this.summonerName = summonerName;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
        this.veteran = veteran;
        this.inactive = inactive;
        this.freshBlood = freshBlood;
        this.hotStreak = hotStreak;
    }
}
