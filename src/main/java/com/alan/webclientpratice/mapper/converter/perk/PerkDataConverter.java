package com.alan.webclientpratice.mapper.converter.perk;

import com.alan.webclientpratice.dto.match.PerksResponse;
import com.alan.webclientpratice.mapper.converter.GenericConverter;

public class PerkDataConverter extends GenericConverter<PerksResponse> {
    public PerkDataConverter() {
        super(PerksResponse.class);
    }
}
