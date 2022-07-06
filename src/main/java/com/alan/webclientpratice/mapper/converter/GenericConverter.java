package com.alan.webclientpratice.mapper.converter;

import com.google.gson.Gson;

import javax.persistence.AttributeConverter;

public class GenericConverter <T> implements AttributeConverter<T,String> {

    private Class<T> attributeType;

    final Gson gson = new Gson();

    public GenericConverter(Class<T> attributeType) {
        this.attributeType = attributeType;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public T convertToEntityAttribute(String dbData) {

        return gson.fromJson(dbData,attributeType);
    }
}