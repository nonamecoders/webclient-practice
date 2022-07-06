package com.alan.webclientpratice.mapper.converter.challenge;

import com.alan.webclientpratice.dto.match.ChallengeResponse;
import com.alan.webclientpratice.mapper.converter.GenericConverter;

public class ChallengeConverter extends GenericConverter<ChallengeResponse> {
    public ChallengeConverter() {
        super(ChallengeResponse.class);
    }

}