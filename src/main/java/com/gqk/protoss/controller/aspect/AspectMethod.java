package com.gqk.protoss.controller.aspect;

import com.gqk.protoss.model.TokenCacheModel;
import com.gqk.protoss.service.token.TokenService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class AspectMethod {

    @Autowired
    private TokenService tokenService;

    @Pointcut(value = "execution(public * com.gqk.protoss.controller.MainController.createOrUpdateAddr(..))")
    public void checkPrimaryScope(){
        //这里控制更新地址的权限
    }

    @Before("checkPrimaryScope()")
    public void before(JoinPoint joinPoint) throws Exception{
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = req.getHeader("token");
        TokenCacheModel tokenCacheModel =tokenService.getMsgFromCacha(token);
        String scope ="";
        if (tokenCacheModel!=null){
            scope=tokenCacheModel.getScope();
            int scopeInt = Integer.parseInt(scope);
            if (scopeInt<16){
                throw new Exception("该用户权限不足");
            }
        }else {
            throw new Exception("令牌已失效");
        }
    }
}
