package com.gqk.protoss.model;

import com.gqk.protoss.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class UserToken{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String code;
    @Value("token.config.wxAppId")
    private String wxAppId;
    @Value("token.config.wxAppSecret")
    private String wxAppSecret;
    private String wxLoginUrl;

    /*public String UserToken(String code) {
        this.code = code;
        //String wxAppId = applicationContext.getEnvironment().getProperty("token.config.wxAppId");
        //String wxAppSecret = applicationContext.getEnvironment().getProperty("token.config.wxAppSecret");
        //https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
        //this.wxAppId=wxAppId;
        //this.wxAppSecret=wxAppSecret;
        return  "https://api.weixin.qq.com/sns/jscode2session?appid="+this.wxAppId+"&secret="+this.wxAppSecret+"&js_code="+code+"&grant_type=authorization_code";
    }
    public void get(String code) throws Exception{
        wxLoginUrl = UserToken(code);
        String result = HttpClientUtil.doGet(wxLoginUrl);
        if(result==null||result==""){
            throw new Exception("获取session_key及open_id异常 微信内部错误");
        }else {
            logger.info("获取session_key及open_id的内容："+result);
        }
    }*/

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWxAppId() {
        return wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    public String getWxAppSecret() {
        return wxAppSecret;
    }

    public void setWxAppSecret(String wxAppSecret) {
        this.wxAppSecret = wxAppSecret;
    }

    public String getWxLoginUrl() {
        return wxLoginUrl;
    }

    public void setWxLoginUrl(String wxLoginUrl) {
        this.wxLoginUrl = wxLoginUrl;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "code='" + code + '\'' +
                ", wxAppId='" + wxAppId + '\'' +
                ", wxAppSecret='" + wxAppSecret + '\'' +
                ", wxLoginUrl='" + wxLoginUrl + '\'' +
                '}';
    }
}
