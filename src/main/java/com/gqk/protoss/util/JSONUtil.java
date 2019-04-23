package com.gqk.protoss.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class JSONUtil {
    public static JSONObject mapToJson(Map map){
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject;
    }
}
