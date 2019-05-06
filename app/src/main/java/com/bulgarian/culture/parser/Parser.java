package com.bulgarian.culture.parser;

public interface Parser {

    <T> T parse(String content, Class<T> targetClass);
}
