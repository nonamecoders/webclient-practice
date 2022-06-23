package com.alan.webclientpratice.dto.match;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchResponse {

    private MetadataResponse metadata;
    private InfoResponse info;

}