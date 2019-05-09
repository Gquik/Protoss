package com.gqk.protoss.util;

import javax.servlet.http.HttpServletRequest;

public class HttpServletUtil {
    private static HttpServletRequest request;
    public static String getHttpToken(){
        return request.getHeader("token");
    }
}
