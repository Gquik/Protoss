package com.gqk.protoss.service.token;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gqk.protoss.dao.UserMapper;
import com.gqk.protoss.entity.User;
import com.gqk.protoss.entity.enums.ScopeEnum;
import com.gqk.protoss.model.TokenCacheModel;
import com.gqk.protoss.util.ApplicationContextUtil;
import com.gqk.protoss.util.HttpClientUtil;
import com.gqk.protoss.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TokenService extends Token{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CacheUtil cacheUtil;

    private String getUserToken(String code) {
        ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
        String wxAppId = applicationContext.getEnvironment().getProperty("token.config.wxAppId");
        String wxAppSecret = applicationContext.getEnvironment().getProperty("token.config.wxAppSecret");
        //https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
        return  "https://api.weixin.qq.com/sns/jscode2session?appid="+wxAppId+"&secret="+wxAppSecret+"&js_code="+code+"&grant_type=authorization_code";
    }
    public String get(String code) throws Exception{
        logger.info("1111code的内容："+code);
        String wxLoginUrl = getUserToken(code);
        String result = HttpClientUtil.doGet(wxLoginUrl);
        //JSON字符串转map
        Map tokenMap = (Map) JSON.parse(result);
        String key ="";
        if(result==null||result==""){
            throw new Exception("获取session_key及open_id异常 微信内部错误");
        }else {
            //请求失败的错误码
            /*if (tokenMap.get("errcode").toString()=="-1"){
                throw new Exception("系统繁忙，此时请开发者稍候再试["+tokenMap.get("errmsg")+"]");
            }else if (tokenMap.get("errcode").toString()=="40029"){
                throw new Exception("code 无效["+tokenMap.get("errmsg")+"]");
            }else if (tokenMap.get("errcode").toString()=="45011"){
                throw new Exception("频率限制，每个用户每分钟100次["+tokenMap.get("errmsg")+"]");
            }else if (tokenMap.get("errcode").toString()=="0"|| tokenMap.get("errcode").toString()==null){*/
                //请求成功后处理
            logger.info("1111获取session_key及open_id的内容："+result);
                key = sendToken(tokenMap.get("openid").toString(),tokenMap);

            //}
            logger.info("2222获取session_key及open_id的内容："+result);
        }
        return key;
    }
    private String sendToken(String openId,Map tokenMap) throws Exception{
        //拿到openId
        //数据库看一下，openid是否存在
        //如果存在，则不处理，如果不存在则新增一条user记录
        //生成令牌，准备缓存数据，写入缓存
        //把令牌返回到客户端
        User user = userMapper.selectByOpenId(openId);
        int uid;
        if (user!=null){
            uid = user.getId();
            logger.info("1111111111111111111111111111uid"+uid);
        }else {
            User user1 = new User();
            user1.setOpenid(openId);
            logger.info("111111111111111111111111111user1.getId()"+user1.getId());
            userMapper.insertSelective(user1);
            uid = user1.getId();
            logger.info("222222222222222222222222222user1.getId()"+user1.getId());
        }
        tokenMap.put("uid",uid);
        tokenMap.put("scope", ScopeEnum.USER.getValue());
        String key = generateToken();
        JSONObject value = JSONUtil.mapToJson(tokenMap);
        //写入缓存
        //CacheUtil.writeCache(key,value.toString());
        cacheUtil.writeCache(key,value);
        return key;
    }

    public TokenCacheModel getMsgFromCacha(String key) throws Exception{
        return cacheUtil.readCache(key);
    }

}
