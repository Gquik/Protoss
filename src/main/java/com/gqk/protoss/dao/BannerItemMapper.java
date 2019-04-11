package com.gqk.protoss.dao;

import com.gqk.protoss.entity.BannerItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BannerItem record);

    int insertSelective(BannerItem record);

    BannerItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BannerItem record);

    int updateByPrimaryKey(BannerItem record);

    List<BannerItem> selectListByBannerId (Integer bannerId);
}