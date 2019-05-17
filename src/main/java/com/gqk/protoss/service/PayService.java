package com.gqk.protoss.service;

import com.gqk.protoss.condition.StatusCondition;
import com.gqk.protoss.dao.OrderMapper;
import com.gqk.protoss.entity.Order;
import com.gqk.protoss.entity.enums.OrderStatusEnum;
import com.gqk.protoss.model.TokenCacheModel;
import com.gqk.protoss.service.exceptions.AuthException;
import com.gqk.protoss.service.exceptions.ErrorCode;
import com.gqk.protoss.service.exceptions.OrderException;
import com.gqk.protoss.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TokenService tokenService;

    private int orderId = 0;

    private String orderNo;

    public StatusCondition getPreOrder(int orderId){
        this.orderId=orderId;
        //检测原则（服务器资源消耗先小后大）
        //订单号传空检测
        checkOrderIdNotNull();
        //订单号对应订单不存在检测
        //订单号和当前用户不匹配
        //订单未支付检测
        checkOrderValid();
        //库存量检测
        OrderService os = new OrderService();
        StatusCondition statusCondition = os.checkOrderStock(orderId);
        if(!statusCondition.isPass()){
            return statusCondition;
        }

        return null;
    }
    private void makeWxPreOrder(){
        //openId
        TokenCacheModel tcm = tokenService.getMsgFromCacha();
        String openId = tcm.getOpenId();
        if (openId==null){
            throw new AuthException("缓存异常，openId不存在");
        }

    }

    private void checkOrderIdNotNull(){
        if (orderId==0){
            throw new OrderException(ErrorCode.ORDER_ID_IS_NULL);
        }
    }

    private void checkOrderValid(){
        Order order = orderMapper.selectByPrimaryKey(orderId);
        //订单号对应订单不存在检测
        if (order==null){
            throw new OrderException(ErrorCode.ORDER_NOT_EXIST);
        }
        //订单号和当前用户不匹配
        if (order.getUserId()==null){
            throw new OrderException("订单信息异常，无对应用户信息");
        }else {
            tokenService.isValidOperate(order.getUserId());
        }
        //订单未支付检测
        if(order.getStatus()!= OrderStatusEnum.UNPAID.getValue()){//1为待支付状态
            throw new OrderException(ErrorCode.ORDER_STATUS_UNUSUAL);
        }
        orderNo=order.getOrderNo();
    }

}
