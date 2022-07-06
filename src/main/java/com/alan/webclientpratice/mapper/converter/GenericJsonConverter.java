package com.alan.webclientpratice.mapper.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.io.IOException;

@Slf4j
public class GenericJsonConverter<T> implements AttributeConverter<T,String> {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public String convertToDatabaseColumn(T attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            log.error("fail to serialize as object into Json : {}", attribute, e);
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public T convertToEntityAttribute(String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, new TypeReference<T>(){});
        } catch (IOException e) {
            log.error("fail to deserialize as Json into Object : {}", jsonStr, e);
            throw new IllegalArgumentException(e);
        }
    }


}
