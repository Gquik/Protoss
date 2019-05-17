package com.gqk.protoss.controller;

import com.gqk.protoss.condition.StatusCondition;
import com.gqk.protoss.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @RequestMapping(value = "preOrder",method = RequestMethod.POST)
    public StatusCondition getPreOrder(@RequestParam int orderId){
        return payService.getPreOrder(orderId);
    }
}
