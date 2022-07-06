package com.alan.webclientpratice.dto.match;

import com.alan.webclientpratice.mapper.converter.team.BanListConveter;
import com.alan.webclientpratice.mapper.converter.team.ObjectivesResponseConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
public class TeamResponse implements Serializable {
    @Convert(converter = BanListConveter.class)
    private List<BanResponse> bans;
    @Convert(converter = ObjectivesResponseConverter.class)
    private ObjectivesResponse objectives;
    private Integer teamId;
    private Boolean win;

    @Data
    public static class BanResponse implements Serializable {
        private Integer championId;
        private Integer picTurn;
    }
}
