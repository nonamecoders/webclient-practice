package com.alan.webclientpratice.mapper.converter.team;

import com.alan.webclientpratice.dto.match.TeamResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class BanListConveter implements AttributeConverter<List<TeamResponse.BanResponse>,String> {

    private final ObjectMapper objectMapper= new ObjectMapper();


    @Override
    public String convertToDatabaseColumn(List<TeamResponse.BanResponse> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            log.info("failed to parse database. data to json.");
            return new String();
        }
    }

    @Override
    public List<TeamResponse.BanResponse> convertToEntityAttribute(String dbData) {
        try {
            return Arrays.asList(objectMapper.readValue(dbData, TeamResponse.BanResponse[].class));
        } catch (JsonProcessingException e) {
            log.info("failed to parse database. data to json.");
            return Collections.emptyList();
        }
    }
}