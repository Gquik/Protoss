package com.gqk.protoss.service;

import com.gqk.protoss.service.token.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TokenService tokenService;

    public String getToken(String code){
       String key="";
        if(code != null&&code != ""){
            try {
                key = tokenService.get(code);
            }catch (Exception e){
                e.printStackTrace();
            }

        }else {
            //错误信息，code不能为空
            System.err.println("code不能为空");
        }
        return key;
    }
}
