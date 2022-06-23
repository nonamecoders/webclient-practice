package com.alan.webclientpratice.dto.match;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class InfoResponse implements Serializable {

    private Long gameCreation;
    private Long gameDuration;
    private Long gameEndTimestamp;
    private Long gameId;
    private String gameMode;
    private String gameName;
    private Long gameStartTimestamp;
    private String gameType;
    private String gameVersion;
    private Integer mapId;
    private List<ParticipantResponse> participants;
    private String platformId;
    private String queueId;
    private List<TeamResponse> teams;
    private String tournamentCode;

}
