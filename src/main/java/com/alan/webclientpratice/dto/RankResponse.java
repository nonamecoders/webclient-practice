package com.alan.webclientpratice.dto;

import lombok.Data;

@Data
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
}
