package com.gqk.protoss.service;

import com.gqk.protoss.dao.*;
import com.gqk.protoss.entity.*;
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

    @Autowired
    private ThemeProductMapper themeProductMapper;

    @Autowired
    private ProductMapper productMapper;

    public BannerItemImageModel getBanner(Integer id){
        List<BannerItemModel> bannerItemModelList = new ArrayList<>();
        List<BannerItem> bannerItemList =  bannerItemMapper.selectListByBannerId(id);
        for (BannerItem bannerItem: bannerItemList ){
            BannerItemModel bannerItemModel = new BannerItemModel();
            bannerItemModel.setBannerItem(bannerItem);
            Image image = imageMapper.selectByPrimaryKey(bannerItem.getImgId());
            if(image.getFrom()==1){
                image.setUrl("https://www.gquik.club:8443/static/images"+image.getUrl());
            }
            bannerItemModel.setImage(image);
            bannerItemModelList.add(bannerItemModel);
        }
        Banner banner = bannerMapper.selectByPrimaryKey(id);
        BannerItemImageModel bannerItemImageModel = new BannerItemImageModel() ;
        bannerItemImageModel.setBanner(banner);
        bannerItemImageModel.setItems(bannerItemModelList);
        return bannerItemImageModel;
    }

    public List<ThemeImageModel> getTheme(Integer[] idList){
        List<ThemeImageModel> themeImageModelList = new ArrayList<>();
        for (Integer id:idList){
            if(id!=null){
                ThemeImageModel themeImageModel = new ThemeImageModel();
                Theme theme = themeMapper.selectByPrimaryKey(id);
                themeImageModel.setId(theme.getId());
                themeImageModel.setName(theme.getName());
                themeImageModel.setDescription(theme.getDescription());
                themeImageModel.setTopicImgId(theme.getTopicImgId());
                themeImageModel.setDeleteTime(theme.getDeleteTime());
                themeImageModel.setHeadImgId(theme.getHeadImgId());
                themeImageModel.setUpdateTime(theme.getUpdateTime());
                if (theme.getTopicImgId()!=null){
                    Image topicImage = imageMapper.selectByPrimaryKey(theme.getTopicImgId());
                    if(topicImage.getFrom()==1){
                        topicImage.setUrl("https://www.gquik.club:8443/static/images"+topicImage.getUrl());
                    }
                    themeImageModel.setTopicImage(topicImage);
                }
                if (theme.getHeadImgId()!=null){
                    Image headImage = imageMapper.selectByPrimaryKey(theme.getHeadImgId());
                    if(headImage.getFrom()==1){
                        headImage.setUrl("https://www.gquik.club:8443/static/images"+headImage.getUrl());
                    }
                    themeImageModel.setHeadImage(headImage);
                }
                themeImageModelList.add(themeImageModel);
            }
        }
        return themeImageModelList;
    }

    public ThemeImageModel getThemeProducts(Integer id){
        ThemeImageModel themeImageModel = new ThemeImageModel();
        Theme theme = themeMapper.selectByPrimaryKey(id);
        themeImageModel.setId(theme.getId());
        themeImageModel.setName(theme.getName());
        themeImageModel.setDescription(theme.getDescription());
        themeImageModel.setTopicImgId(theme.getTopicImgId());
        themeImageModel.setDeleteTime(theme.getDeleteTime());
        themeImageModel.setHeadImgId(theme.getHeadImgId());
        themeImageModel.setUpdateTime(theme.getUpdateTime());
        List<ThemeProductKey> pruductIdList = themeProductMapper.selectProductIdByThemeId(id);
        List<Product> productList = new ArrayList<>();
        for (ThemeProductKey themeProductKey : pruductIdList){
            Product product = productMapper.selectByPrimaryKey(themeProductKey.getProductId());
            product.setMainImgUrl("https://www.gquik.club:8443/static/images"+product.getMainImgUrl());
            productList.add(product);
        }
        themeImageModel.setProducts(productList);
        if (theme.getTopicImgId()!=null){
            Image topicImage = imageMapper.selectByPrimaryKey(theme.getTopicImgId());
            if(topicImage.getFrom()==1){
                topicImage.setUrl("https://www.gquik.club:8443/static/images"+topicImage.getUrl());
            }
            themeImageModel.setTopicImage(topicImage);
        }
        if (theme.getHeadImgId()!=null){
            Image headImage = imageMapper.selectByPrimaryKey(theme.getHeadImgId());
            if(headImage.getFrom()==1){
                headImage.setUrl("https://www.gquik.club:8443/static/images"+headImage.getUrl());
            }
            themeImageModel.setHeadImage(headImage);
        }
        return themeImageModel;
    }

    public List<Product> getProductRecent(Integer count){
        List<Product> productList = productMapper.selectList();
        for (Product product : productList){
            product.setMainImgUrl("https://www.gquik.club:8443/static/images"+product.getMainImgUrl());
        }
        return productList;
    }
}
