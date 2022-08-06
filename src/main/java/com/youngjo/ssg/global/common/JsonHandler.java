package com.youngjo.ssg.global.common;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Slf4j
public class JsonHandler {
    private final static JSONParser parser = new JSONParser();

    public static String putData(String rawJson, String key, String value) {
        try {
            JSONObject newJson = (JSONObject) parser.parse(rawJson);
            newJson.put(key.strip(), value.strip());
            return newJson.toJSONString();
        } catch (ParseException pe) {
            log.error("ParseException occurred in JsonHandler's putData.");
            return rawJson;
        }
    }

    public static String removeData(String rawJson, String key) {
        try {
            JSONObject newJson = (JSONObject) parser.parse(rawJson);
            newJson.remove(key.strip());
            return newJson.toJSONString();
        } catch (ParseException pe) {
            log.error("ParseException occurred in JsonHandler's removeData.");
            return rawJson;
        }
    }
}
