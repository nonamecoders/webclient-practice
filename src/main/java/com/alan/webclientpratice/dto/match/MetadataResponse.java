package com.alan.webclientpratice.dto.match;

import lombok.Data;

import java.util.List;

@Data
public class MetadataResponse {

    private String dataVersion;

    private String matchId;

    private List<String> participants;

}
