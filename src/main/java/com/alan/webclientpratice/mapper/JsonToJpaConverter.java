package com.alan.webclientpratice.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class JsonToJpaConverter implements AttributeConverter<Object,String> {

    private final ObjectMapper objectMapper= new ObjectMapper();


    @Override
    public String convertToDatabaseColumn(Object attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException ex) {
            return null;
            // or throw an error
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Object.class);
        } catch (IOException ex) {
            // logger.error("Unexpected IOEx decoding json from database: " + dbData);
            return null;
        }
    }
}
