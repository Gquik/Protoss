package com.gqk.protoss.dao;

import com.gqk.protoss.entity.Theme;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Theme record);

    int insertSelective(Theme record);

    Theme selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Theme record);

    int updateByPrimaryKey(Theme record);
}