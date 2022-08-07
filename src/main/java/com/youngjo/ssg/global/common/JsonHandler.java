package com.youngjo.ssg.global.common;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;

@Slf4j
public class JsonHandler {
    private final static JSONParser parser = new JSONParser();

    public static String putStrData(String rawJson, String key, String value) {
        try {
            if (rawJson == null) rawJson = "{}";
            JSONObject parsedJson = (JSONObject) parser.parse(rawJson);
            parsedJson.put(key.strip(), value.strip());
            return parsedJson.toJSONString();
        } catch (ParseException pe) {
            log.error("ParseException occurred in JsonHandler's putData.");
            return rawJson;
        }
    }

    public static String createListData(Object... listValue) {
        JSONArray parsedJson = new JSONArray();
        parsedJson.addAll(List.of(listValue));
        return parsedJson.toJSONString();
    }

    public static String removeData(String rawJson, String key) {
        try {
            JSONObject parsedJson = (JSONObject) parser.parse(rawJson);
            parsedJson.remove(key.strip());
            return parsedJson.toJSONString();
        } catch (ParseException pe) {
            log.error("ParseException occurred in JsonHandler's removeData.");
            return rawJson;
        }
    }
}
