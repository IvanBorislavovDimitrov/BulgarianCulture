package com.bulgarian.culture.factory;

import com.bulgarian.culture.parser.JSONParser;
import com.bulgarian.culture.parser.Parser;

public final class ParserFactory {

    private static Parser parser;

    private ParserFactory() {

    }

    public static Parser getDefaultJSONParser() {
        if (parser == null) {
            parser = new JSONParser();
        }
        return parser;
    }
}
