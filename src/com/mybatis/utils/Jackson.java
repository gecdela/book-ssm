package com.mybatis.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public  class  Jackson {
    public static Map<String, Object> JsonToMap(String json_str) {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(json_str, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
    public static String MapToJson(Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();
        String json_str="";
        try {
            json_str = mapper.writeValueAsString(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json_str;
    }
}
