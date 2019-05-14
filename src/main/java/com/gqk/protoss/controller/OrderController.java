package com.gqk.protoss.controller;

import com.gqk.protoss.condition.OrderProductsCondition;
import com.gqk.protoss.controller.rest.Result;
import com.gqk.protoss.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //用户在选择商品后，向服务器提交所选商品的相关信息
    //服务器在接收到信息后，需要检查订单相关商品的库存量
    //有库存，把订单数据存入数据库中，下单成功了，返回客户端消息，告诉客户端可以支付了
    //调用支付接口进行支付
    //还需再次进行库存量检测
    //服务器这边就可以调用微信支付的接口进行支付
    //微信会返回给我们一个支付结果
    //成功也需检查库存量
    //成功根据微信返回的结果进行库存量的扣除

    @RequestMapping(value = "order",method = RequestMethod.POST)
    public Result placeOrder(@RequestBody OrderProductsCondition orderProductsCondition) throws Exception{
        return orderService.placeOrder(orderProductsCondition);
    }

}
