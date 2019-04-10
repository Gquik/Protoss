package com.gqk.protoss.dao.mapper;

import com.gqk.protoss.entity.BannerItem;

public interface BannerItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BannerItem record);

    int insertSelective(BannerItem record);

    BannerItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BannerItem record);

    int updateByPrimaryKey(BannerItem record);
}