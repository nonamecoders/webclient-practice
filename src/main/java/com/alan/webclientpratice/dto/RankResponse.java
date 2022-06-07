package com.alan.webclientpratice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RankResponse {

    private String leagueId;
    private String summonerId; //	string	Player's encrypted summonerId.
    private String summonerName; //	string
    private String queueType; //	string
    private String tier; //	string
    private String rank; //	string	The player's division within a tier.
    private Integer leaguePoints; //	int
    private Integer wins; //	int	Winning team on Summoners Rift.
    private Integer losses; //	int	Losing team on Summoners Rift.
    private Boolean hotStreak; //	boolean
    private Boolean veteran;
    private Boolean freshBlood;
    private Boolean inactive;

    @Builder
    public RankResponse(String leagueId, String summonerId, String summonerName, String queueType, String tier, String rank, Integer leaguePoints, Integer wins, Integer losses, Boolean hotStreak, Boolean veteran, Boolean freshBlood, Boolean inactive) {
        this.leagueId = leagueId;
        this.summonerId = summonerId;
        this.summonerName = summonerName;
        this.queueType = queueType;
        this.tier = tier;
        this.rank = rank;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
        this.hotStreak = hotStreak;
        this.veteran = veteran;
        this.freshBlood = freshBlood;
        this.inactive = inactive;
    }
}
