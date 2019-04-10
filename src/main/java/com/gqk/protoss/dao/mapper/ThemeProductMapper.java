package com.gqk.protoss.dao.mapper;

import com.gqk.protoss.entity.ThemeProductKey;

public interface ThemeProductMapper {
    int deleteByPrimaryKey(ThemeProductKey key);

    int insert(ThemeProductKey record);

    int insertSelective(ThemeProductKey record);
}