package com.gqk.protoss.service;

import com.gqk.protoss.controller.rest.Result;
import com.gqk.protoss.dao.BannerItemMapper;
import com.gqk.protoss.dao.BannerMapper;
import com.gqk.protoss.dao.ImageMapper;
import com.gqk.protoss.dao.ThemeMapper;
import com.gqk.protoss.entity.Banner;
import com.gqk.protoss.entity.BannerItem;
import com.gqk.protoss.entity.Image;
import com.gqk.protoss.entity.Theme;
import com.gqk.protoss.model.BannerItemImageModel;
import com.gqk.protoss.model.BannerItemModel;
import com.gqk.protoss.model.ThemeImageModel;
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

    @Autowired
    private ThemeMapper themeMapper;

    public Result<BannerItemImageModel> getBanner(Integer id){
        List<BannerItemModel> bannerItemModelList = new ArrayList<>();
        List<BannerItem> bannerItemList =  bannerItemMapper.selectListByBannerId(id);
        for (BannerItem bannerItem: bannerItemList ){
            BannerItemModel bannerItemModel = new BannerItemModel();
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

    public Result<List<ThemeImageModel>> getTheme(Integer[] idList){
        List<ThemeImageModel> themeImageModelList = new ArrayList<>();
        for (Integer id:idList){
            if(id!=null){
                ThemeImageModel themeImageModel = new ThemeImageModel();
                Theme theme = themeMapper.selectByPrimaryKey(id);
                themeImageModel.setTheme(theme);
                if (theme.getTopicImgId()!=null){
                    Image topicImage = imageMapper.selectByPrimaryKey(theme.getTopicImgId());
                    if(topicImage.getFrom()==1){
                        topicImage.setUrl("http://47.99.218.29:8080/static/images"+topicImage.getUrl());
                    }
                    themeImageModel.setTopicImage(topicImage);
                }
                if (theme.getHeadImgId()!=null){
                    Image headImage = imageMapper.selectByPrimaryKey(theme.getHeadImgId());
                    if(headImage.getFrom()==1){
                        headImage.setUrl("http://47.99.218.29:8080/static/images"+headImage.getUrl());
                    }
                    themeImageModel.setHeadImage(headImage);
                }
                themeImageModelList.add(themeImageModel);
            }
        }
        return Result.one(themeImageModelList);
    }
}
