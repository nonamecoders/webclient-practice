package com.alan.webclientpratice.dto.match;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
public class TeamResponse implements Serializable {
    private List<BanResponse> bans;
    private ObjectivesResponse objectives;
    private Integer teamId;
    private Boolean win;

    @Data
    public static class BanResponse {
        private Integer championId;
        private Integer picTurn;
    }
}
