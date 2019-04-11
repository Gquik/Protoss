package com.gqk.protoss.dao;

import com.gqk.protoss.entity.ThirdApp;

public interface ThirdAppMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThirdApp record);

    int insertSelective(ThirdApp record);

    ThirdApp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThirdApp record);

    int updateByPrimaryKey(ThirdApp record);
}