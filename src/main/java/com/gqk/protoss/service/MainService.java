package com.gqk.protoss.service;

import com.gqk.protoss.controller.rest.Result;
import com.gqk.protoss.dao.BannerItemMapper;
import com.gqk.protoss.dao.BannerMapper;
import com.gqk.protoss.dao.ImageMapper;
import com.gqk.protoss.entity.Banner;
import com.gqk.protoss.entity.BannerItem;
import com.gqk.protoss.entity.Image;
import com.gqk.protoss.model.BannerItemImageModel;
import com.gqk.protoss.model.BannerItemModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private BannerItemMapper bannerItemMapper;

    @Autowired
    private ImageMapper imageMapper;

    public Result<BannerItemImageModel> getBanner(Integer id){
        List<BannerItemModel> bannerItemModelList = new ArrayList<>();
        BannerItemModel bannerItemModel = null;
        List<BannerItem> bannerItemList =  bannerItemMapper.selectListByBannerId(id);
        for (BannerItem bannerItem: bannerItemList ){
            bannerItemModel = new BannerItemModel();
            bannerItemModel.setBannerItem(bannerItem);
            Image image = imageMapper.selectByPrimaryKey(bannerItem.getImgId());
            if(image.getFrom()==1){
                image.setUrl("http://47.99.218.29:8080/static/images"+image.getUrl());
            }
            bannerItemModel.setImage(image);
            bannerItemModelList.add(bannerItemModel);
        }
        Banner banner = bannerMapper.selectByPrimaryKey(id);
        BannerItemImageModel bannerItemImageModel = new BannerItemImageModel() ;
        bannerItemImageModel.setBanner(banner);
        bannerItemImageModel.setItems(bannerItemModelList);
        return Result.one(bannerItemImageModel);
    }
}
