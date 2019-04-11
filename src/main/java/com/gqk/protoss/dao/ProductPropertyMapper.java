package com.gqk.protoss.dao;

import com.gqk.protoss.entity.ProductProperty;

public interface ProductPropertyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductProperty record);

    int insertSelective(ProductProperty record);

    ProductProperty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductProperty record);

    int updateByPrimaryKey(ProductProperty record);
}