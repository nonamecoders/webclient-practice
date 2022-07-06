package com.alan.webclientpratice.dto.match;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MatchResponse implements Serializable {

    private MetadataResponse metadata;
    private InfoResponse info;

}