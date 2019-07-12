package com.gqk.protoss.service.token;

import com.gqk.protoss.model.TokenCacheModel;
import com.gqk.protoss.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class Cache {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Redis redis;

    //写入缓存
    public void writeCache(String key,String value){
        redis.set(key,value);
        logger.info("###缓存成功###");
        logger.info("###缓存前内容为："+value);
        logger.info("###缓存后内容为："+redis.get(key));
    }
    //读取缓存

    public TokenCacheModel readCache(String key){
        TokenCacheModel tokenCacheModel = new TokenCacheModel();
        String value =redis.get(key);
        logger.info("从缓存中取数据："+value);
        if (StringUtils.isEmpty(value)){
            logger.info("从缓存中取数据异常!(这里需要抛个自定义异常)");
            return tokenCacheModel;
        }
        Map map = JSONUtil.objectToMap(value);
        String uid = map.get("uid").toString();
        String openId = map.get("openid").toString();
        String scope = map.get("scope").toString();
        String sessionKey = map.get("session_key").toString();
        tokenCacheModel.setUid(uid);
        tokenCacheModel.setOpenId(openId);
        tokenCacheModel.setScope(scope);
        tokenCacheModel.setSessionKey(sessionKey);
        return tokenCacheModel;
    }
}
