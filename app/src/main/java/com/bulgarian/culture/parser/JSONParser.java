package com.bulgarian.culture.parser;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JSONParser implements Parser {

    private ObjectMapper objectMapper;

    public JSONParser() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public <T> T parse(String content, Class<T> targetClass) {
        try {
            return objectMapper.readValue(content, targetClass);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getCause());
        }
    }
}
