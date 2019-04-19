package com.gqk.protoss.service.token;

import com.alibaba.fastjson.JSON;
import com.gqk.protoss.util.ApplicationContextUtil;
import com.gqk.protoss.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TokenService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String getUserToken(String code) {
        ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
        String wxAppId = applicationContext.getEnvironment().getProperty("token.config.wxAppId");
        String wxAppSecret = applicationContext.getEnvironment().getProperty("token.config.wxAppSecret");
        //https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
        return  "https://api.weixin.qq.com/sns/jscode2session?appid="+wxAppId+"&secret="+wxAppSecret+"&js_code="+code+"&grant_type=authorization_code";
    }
    public void get(String code) throws Exception{
        String wxLoginUrl = getUserToken(code);
        String result = HttpClientUtil.doGet(wxLoginUrl);
        //JSON字符串转map
        Map maps = (Map) JSON.parse(result);
        if(result==null||result==""){
            throw new Exception("获取session_key及open_id异常 微信内部错误");
        }else {
            //请求失败的错误码
            if (maps.get("errcode").toString()=="-1"){
                throw new Exception("系统繁忙，此时请开发者稍候再试["+maps.get("errmsg")+"]");
            }else if (maps.get("errcode").toString()=="40029"){
                throw new Exception("code 无效["+maps.get("errmsg")+"]");
            }else if (maps.get("errcode").toString()=="45011"){
                throw new Exception("频率限制，每个用户每分钟100次["+maps.get("errmsg")+"]");
            }else if (maps.get("errcode").toString()=="0"|| maps.get("errcode").toString()==null){
                //请求成功后处理
                sendToken(maps.get("openid").toString(),maps.get("session_key").toString());

            }
            logger.info("获取session_key及open_id的内容："+result);
        }
    }
    private void sendToken(String openId,String sessionKey){

    }
}
