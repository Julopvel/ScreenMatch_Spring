package com.reto.screenMatchSpring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransformData implements ITransformData{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T gteData(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
