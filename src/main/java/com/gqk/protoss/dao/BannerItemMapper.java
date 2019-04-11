package com.gqk.protoss.dao;

import com.gqk.protoss.entity.BannerItem;
import org.apache.ibatis.annotations.Param;
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

    List<BannerItem> selectListByBannerId (@Param("bannerId") Integer bannerId);
}