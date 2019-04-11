package com.gqk.protoss.dao;

import com.gqk.protoss.entity.Banner;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);
}