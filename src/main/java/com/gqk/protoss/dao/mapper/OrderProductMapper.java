package com.gqk.protoss.dao.mapper;

import com.gqk.protoss.entity.OrderProduct;
import com.gqk.protoss.entity.OrderProductKey;

public interface OrderProductMapper {
    int deleteByPrimaryKey(OrderProductKey key);

    int insert(OrderProduct record);

    int insertSelective(OrderProduct record);

    OrderProduct selectByPrimaryKey(OrderProductKey key);

    int updateByPrimaryKeySelective(OrderProduct record);

    int updateByPrimaryKey(OrderProduct record);
}