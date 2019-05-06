package com.bulgarian.culture.factory;

import com.bulgarian.culture.parser.JSONParser;
import com.bulgarian.culture.parser.Parser;

public final class ParserFactory {

    private ParserFactory() {

    }

    public static Parser getDefaultJSONParser() {
        return new JSONParser();
    }
}
