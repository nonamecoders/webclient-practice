package com.alan.webclientpratice.mapper.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;

public class GenericListConverter <T> implements AttributeConverter<List<T>,String> {

    final ObjectMapper mapper = new ObjectMapper();
    final Gson gson = new Gson();

    @Override
    public String convertToDatabaseColumn(List<T> attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public List<T> convertToEntityAttribute(String dbData) {

        try {
            if (StringUtils.isBlank(dbData)) {
                return null;
            }
            return mapper.readValue(dbData, new TypeReference<List<T>>() {});
        } catch (IOException e) {
            return null;
        }
    }
}