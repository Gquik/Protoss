package com.gqk.protoss.service.token;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gqk.protoss.dao.UserMapper;
import com.gqk.protoss.entity.User;
import com.gqk.protoss.entity.enums.ScopeEnum;
import com.gqk.protoss.model.TokenCacheModel;
import com.gqk.protoss.service.exceptions.AuthException;
import com.gqk.protoss.util.ApplicationContextUtil;
import com.gqk.protoss.util.HttpClientUtil;
import com.gqk.protoss.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class TokenService extends Token{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Cache cache;

    private String getUserToken(String code) {
        ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
        String wxAppId = applicationContext.getEnvironment().getProperty("token.config.wxAppId");
        String wxAppSecret = applicationContext.getEnvironment().getProperty("token.config.wxAppSecret");
        //https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
        return  "https://api.weixin.qq.com/sns/jscode2session?appid="+wxAppId+"&secret="+wxAppSecret+"&js_code="+code+"&grant_type=authorization_code";
    }
    public String getKey(String code){
        logger.info("1111code的内容："+code);
        String wxLoginUrl = getUserToken(code);
        String result = HttpClientUtil.doGet(wxLoginUrl);
        //JSON字符串转map
        Map tokenMap = (Map) JSON.parse(result);
        String key ="";
        if(result==null||result==""){
            throw new RuntimeException("获取session_key及open_id异常 微信内部错误");
        }else {
                //请求成功后处理
            key = sendToken(tokenMap.get("openid").toString(),tokenMap);
        }
        return key;
    }
    private String sendToken(String openId,Map tokenMap){
        //拿到openId
        //数据库看一下，openid是否存在
        //如果存在，则不处理，如果不存在则新增一条user记录
        //生成令牌，准备缓存数据，写入缓存
        //把令牌返回到客户端
        User user = userMapper.selectByOpenId(openId);
        int uid;
        if (user!=null){
            uid = user.getId();
        }else {
            User user1 = new User();
            user1.setOpenid(openId);
            userMapper.insertSelective(user1);
            uid = user1.getId();
        }
        tokenMap.put("uid",uid);
        tokenMap.put("scope", ScopeEnum.USER.getValue());
        String key = generateToken();
        JSONObject jsonObject = JSONUtil.mapToJson(tokenMap);
        String value = jsonObject.toJSONString();
        //写入缓存
        cache.writeCache(key,value);
        return key;
    }

    public TokenCacheModel getMsgFromCacha(){
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = req.getHeader("token");
        logger.info("从前端传过来的token："+token);
        return cache.readCache(token);
    }

    public void isValidOperate(int orderUid){
        TokenCacheModel tokenCacheModel = getMsgFromCacha();
        if (!tokenCacheModel.getUid().equals(orderUid)){
            throw new AuthException("订单和用户不匹配");
        }
    }

}
