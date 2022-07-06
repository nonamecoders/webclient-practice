package com.alan.webclientpratice.dto.match;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MetadataResponse implements Serializable {

    private String dataVersion;

    private String matchId;

    private List<String> participants;

}
