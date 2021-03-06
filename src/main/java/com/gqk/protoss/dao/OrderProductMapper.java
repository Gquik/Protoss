package com.gqk.protoss.dao;

import com.gqk.protoss.entity.OrderProduct;
import com.gqk.protoss.entity.OrderProductKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductMapper {
    int deleteByPrimaryKey(OrderProductKey key);

    int insert(OrderProduct record);

    int insertSelective(OrderProduct record);

    OrderProduct selectByPrimaryKey(OrderProductKey key);

    int updateByPrimaryKeySelective(OrderProduct record);

    int updateByPrimaryKey(OrderProduct record);

    List<OrderProduct> selectByOrderId(int orderId);
}