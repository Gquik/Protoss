package com.gqk.protoss.service;

import com.alibaba.fastjson.JSONObject;
import com.gqk.protoss.condition.*;
import com.gqk.protoss.controller.rest.Result;
import com.gqk.protoss.dao.OrderMapper;
import com.gqk.protoss.dao.OrderProductMapper;
import com.gqk.protoss.dao.ProductMapper;
import com.gqk.protoss.dao.UserAddressMapper;
import com.gqk.protoss.entity.Order;
import com.gqk.protoss.entity.OrderProduct;
import com.gqk.protoss.entity.Product;
import com.gqk.protoss.entity.UserAddress;
import com.gqk.protoss.model.TokenCacheModel;
import com.gqk.protoss.service.token.TokenService;
import com.gqk.protoss.util.OrderNumUtil;
import com.gqk.protoss.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    //客户端传过来的订单商品信息
    private List<OrderCondition> opList;

    //查询数据库的详细商品信息
    private List<Product> pList =new ArrayList<>();

    //对应的用户ID
    private int userId;

    public Result placeOrder(OrderProductsCondition orderProductsCondition) throws Exception{
        TokenCacheModel tokenCacheModel =tokenService.getMsgFromCacha();
        String uid ="";
        if (tokenCacheModel!=null){
            uid=tokenCacheModel.getUid();
        }
        userId = Integer.parseInt(uid);
        //校验订单信息库存量
        StatusCondition statusCondition = this.getProductsByOrder(orderProductsCondition);
        if (!statusCondition.isPass()){
            statusCondition.setOrderId(-1);
            return Result.one(statusCondition);
        }
        //开始创建订单
        //先生成快照
        SnapCondition snapCondition = snapOrder(statusCondition);
        Order order = new Order();
        try {
            //用token去缓存中找对应的uid
            //补全order订单信息并写入order表
            order.setUserId(userId);
            order.setOrderNo(OrderNumUtil.createOrderNum());
            order.setTotalPrice(snapCondition.getOrderPrice());
            order.setTotalCount(snapCondition.getTotalCount());
            order.setSnapImg(snapCondition.getSnapImg());
            order.setSnapName(snapCondition.getSnapName());
            order.setSnapAddress(snapCondition.getSnapAddress());
            //暂时不做序列化，直接对象转jsonString
            //order.setSnapItems(SerializeUtil.serialize(snapCondition.getProductsConditionList()));
            order.setSnapItems(JSONObject.toJSONString(snapCondition.getProductsConditionList()));
            orderMapper.insertSelective(order);
            //补全orderProduct订单关联信息并写入order_product表
            for (OrderCondition orderCondition : opList){
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrderId(order.getId());
                orderProduct.setProductId(orderCondition.getProductId());
                orderProduct.setCount(orderCondition.getCount());
                orderProductMapper.insertSelective(orderProduct);
            }
            statusCondition.setOrderId(order.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.one(statusCondition);
    }

    //生成订单快照
    private SnapCondition snapOrder(StatusCondition statusCondition) throws Exception{
        SnapCondition snapCondition = new SnapCondition();
        snapCondition.setOrderPrice(statusCondition.getOrderPrice());
        snapCondition.setTotalCount(statusCondition.getOrderCount());
        snapCondition.setProductsConditionList(statusCondition.getProductsConditionList());
        snapCondition.setSnapAddress(this.getUserAddress());
        snapCondition.setSnapName(pList.get(0).getName());
        snapCondition.setSnapImg(pList.get(0).getMainImgUrl());
        //种类数量大于1，要在订单名称后面加个“等”
        if (snapCondition.getTotalCount()>1){
            snapCondition.setSnapName(snapCondition.getSnapName()+"等");
        }
        return snapCondition;
    }

    //获取用户地址并序列化成String返回
    private String getUserAddress() throws Exception{
        UserAddress userAddress =userAddressMapper.selectByUserId(userId);
        if (userAddress==null){
            throw new Exception("用户收货地址不存在，下单失败");
        }
        //暂时不做序列化，直接对象转jsonString
        //String snapAddress = SerializeUtil.serialize(userAddress);
        String snapAddress = JSONObject.toJSONString(userAddress);
        return snapAddress;
    }

    //根据订单信息查找真实的商品信息
    private StatusCondition getProductsByOrder(OrderProductsCondition orderProductsCondition){
        opList = orderProductsCondition.getOrderList();
        List<ProductsCondition> productsConditionList = new ArrayList<>();
        StatusCondition statusCondition = new StatusCondition();
        //下单先判断库存量是否不足
        if (opList == null || opList.size() <= 0) {
            statusCondition.setPass(false);
            statusCondition.setErrorMsg("订单信息有误请重新下单");
            return statusCondition;
        }
        BigDecimal orderPrice = new BigDecimal(0.00);
        int orderCount = 0;
        for (OrderCondition orderProduct : opList) {
            if (orderProduct.getProductId() == 0 || orderProduct.getCount() <= 0) {
                statusCondition.setPass(false);
                statusCondition.setErrorMsg("订单信息有误请重新下单");
                return statusCondition;
            }
            Product product = productMapper.selectByPrimaryKey(orderProduct.getProductId());
            if (product == null) {
                statusCondition.setPass(false);
                statusCondition.setErrorMsg("订单商品不存在，请重新下单");
                return statusCondition;
            }
            if (product.getStock() <= 0 || (product.getStock() - orderProduct.getCount()) < 0) {
                statusCondition.setPass(false);
                statusCondition.setErrorMsg("订单商品[" + product.getName() + "]库存不足(" + orderProduct.getCount() + ")，请重新下单");
                return statusCondition;
            }
            //查询出来的商品信息放入商品信息列表
            pList.add(product);
            //计算出单种商品的总价格（单价*数量）
            BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(orderProduct.getCount()));
            //计算订单所有商品的总数
            orderCount = orderCount+orderProduct.getCount();
            //计算订单的总价格（每种商品总价格之和）
            orderPrice =orderPrice.add(totalPrice);
            ProductsCondition productsCondition = new ProductsCondition();
            productsCondition.setId(product.getId());
            productsCondition.setTotalPrice(totalPrice);
            productsCondition.setHaveStock(true);
            productsCondition.setCount(orderProduct.getCount());
            productsCondition.setName(product.getName());
            productsConditionList.add(productsCondition);
        }
        statusCondition.setPass(true);
        statusCondition.setOrderPrice(orderPrice);
        statusCondition.setOrderCount(orderCount);
        statusCondition.setProductsConditionList(productsConditionList);
        return statusCondition;
    }
}