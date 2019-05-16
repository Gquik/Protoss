package com.gqk.protoss.service;

import com.gqk.protoss.condition.StatusCondition;
import com.gqk.protoss.dao.OrderMapper;
import com.gqk.protoss.entity.Order;
import com.gqk.protoss.service.exceptions.ErrorCode;
import com.gqk.protoss.service.exceptions.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayService {

    @Autowired
    private OrderMapper orderMapper;

    private int orderId = 0;

    public void getPreOrder(int orderId){
        this.orderId=orderId;
        //检测原则（服务器资源消耗先小后大）
        //订单号传空检测
        checkOrderIdNotNull();
        //订单号对应订单不存在检测
        checkOrderValid();
        //订单号和当前用户不匹配
        //订单未支付检测
        //库存量检测
        OrderService os = new OrderService();
        StatusCondition statusCondition = os.checkOrderStock(orderId);
    }
    private void checkOrderIdNotNull(){
        if (orderId==0){
            throw new OrderException(ErrorCode.ORDER_ID_IS_NULL);
        }
    }

    //订单号对应订单不存在检测
    private void checkOrderValid(){
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order==null){
            throw new OrderException(ErrorCode.ORDER_NOT_EXIST);
        }
    }

}
