package com.gqk.protoss.service.token;

import com.gqk.protoss.util.ApplicationContextUtil;
import com.gqk.protoss.util.MD5Util;
import org.springframework.context.ApplicationContext;

import java.util.Date;

public class Token {
    public static String generateToken(){
        //32个字符组成一组随机字符串
        //用三组字符串用MD5加密randChars,timestamp,salt
        String randChars = getRandChars(32);
        String timestamp = new Date().toString();
        ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
        String salt = applicationContext.getEnvironment().getProperty("token.config.salt");
        String md5Result = "";
        try {
            md5Result = MD5Util.md5(randChars+"|"+timestamp+salt,salt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return md5Result;
    }

    private static String getRandChars(int length){
        String str = null;
        String strPol="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        int max = strPol.length()-1;
        for(int i=0;i<length;i++){
            str = str+strPol.indexOf((int)Math.random()*max);
        }
        return str;
    }
}
