package com.gqk.protoss.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

public class JSONUtil {
    public static JSONObject mapToJson(Map map){
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject;
    }

    public static Map<String,String> objectToMap(Object obj){
        Map<String, String> params = JSONObject.parseObject(obj.toString(), new TypeReference<Map<String, String>>(){});
        System.out.println(params);
        return  params;
    }
}
