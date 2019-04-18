package com.gqk.protoss.controller;


import com.gqk.protoss.model.UserToken;
import com.gqk.protoss.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class AuthController {
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/token/user",method = RequestMethod.POST)
    public void getToken(@RequestBody UserToken userToken){
        String code = userToken.getCode();
        authService.getToken(code);
    }
}
