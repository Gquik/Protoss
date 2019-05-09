package com.gqk.protoss.service.token;

import com.alibaba.fastjson.JSONObject;
import com.gqk.protoss.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class CacheUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private CacheManager cacheManager;

    //写入缓存
    public void writeCache(String key,JSONObject value) throws Exception{
        Cache cache = cacheManager.getCache("userCache");
        if(cache==null){
            throw new Exception("服务器缓存异常");
        }
        cache.put(key, value);
        logger.info("###缓存成功###");
        logger.info("###缓存前内容为："+value);
        logger.info("###缓存后内容为："+cache.get(key).get());
    }
    //读取缓存
    public String readCache(String key)throws Exception{
        Cache cache = cacheManager.getCache("userCache");
        if(cache==null){
            throw new Exception("服务器缓存异常");
        }
        /*Cache.ValueWrapper value=cache.get(key);
        logger.info("从缓存中拿出来个什么玩意哦1："+value);
        logger.info("从缓存中拿出来个什么玩意哦2："+value.toString());*/
        Object object =cache.get(key).get();
        logger.info("从缓存中拿出来："+object);
        Map map = JSONUtil.objectToMap(object);
        String uid = map.get("uid").toString();
        //String openid = map.get("openid").toString();
        return uid;
    }
}
