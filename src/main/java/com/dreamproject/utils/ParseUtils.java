package com.dreamproject.utils;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.util.Map;

public class ParseUtils {

    public static Map<String, Object> parseJson(String body){
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        return jsonParser.parseMap(body);
    }
}
