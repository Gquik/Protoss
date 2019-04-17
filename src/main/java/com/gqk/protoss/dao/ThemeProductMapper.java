package com.gqk.protoss.dao;

import com.gqk.protoss.entity.ThemeProductKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeProductMapper {
    int deleteByPrimaryKey(ThemeProductKey key);

    int insert(ThemeProductKey record);

    int insertSelective(ThemeProductKey record);

    List<ThemeProductKey> selectProductIdByThemeId(Integer themeId);
}