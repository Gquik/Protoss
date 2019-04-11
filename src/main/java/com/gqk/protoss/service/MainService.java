package com.gqk.protoss.service;

import com.gqk.protoss.controller.rest.Result;
import com.gqk.protoss.dao.BannerItemMapper;
import com.gqk.protoss.dao.BannerMapper;
import com.gqk.protoss.entity.Banner;
import com.gqk.protoss.entity.BannerItem;
import com.gqk.protoss.model.BannerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private BannerItemMapper bannerItemMapper;

    public Result<BannerModel> getBanner(Integer id){
        List<BannerItem> bannerItemList = bannerItemMapper.selectListByBannerId(id);
        Banner banner = bannerMapper.selectByPrimaryKey(id);
        BannerModel bannerModel = new BannerModel() ;
        bannerModel.setBanner(banner);
        bannerModel.setItems(bannerItemList);
        return Result.one(bannerModel);
    }
}
