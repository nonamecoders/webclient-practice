package com.alan.webclientpratice.dto.match.entity;

import com.alan.webclientpratice.dto.match.ChallengeResponse;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CustomJsonDeserializer extends JsonDeserializer<ChallengeResponse> {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public ChallengeResponse deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode jsonNode = objectMapper.readTree(jsonParser.getText());
        return objectMapper.treeToValue(jsonNode,ChallengeResponse.class);
    }
}