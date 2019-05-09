package com.gqk.protoss.service;

import com.gqk.protoss.dao.*;
import com.gqk.protoss.entity.*;
import com.gqk.protoss.model.*;
import com.gqk.protoss.service.token.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private ProductImageMapper productImageMapper;

    @Autowired
    private ProductPropertyMapper productPropertyMapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    public BannerItemImageModel getBanner(Integer id){
        List<BannerItemModel> bannerItemModelList = new ArrayList<>();
        List<BannerItem> bannerItemList =  bannerItemMapper.selectListByBannerId(id);
        for (BannerItem bannerItem: bannerItemList ){
            BannerItemModel bannerItemModel = new BannerItemModel();
            bannerItemModel.setBannerItem(bannerItem);
            Image image = imageMapper.selectByPrimaryKey(bannerItem.getImgId());
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
                    themeImageModel.setTopicImage(topicImage);
                }
                if (theme.getHeadImgId()!=null){
                    Image headImage = imageMapper.selectByPrimaryKey(theme.getHeadImgId());
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
            productList.add(product);
        }
        themeImageModel.setProducts(productList);
        if (theme.getTopicImgId()!=null){
            Image topicImage = imageMapper.selectByPrimaryKey(theme.getTopicImgId());
            themeImageModel.setTopicImage(topicImage);
        }
        if (theme.getHeadImgId()!=null){
            Image headImage = imageMapper.selectByPrimaryKey(theme.getHeadImgId());
            themeImageModel.setHeadImage(headImage);
        }
        return themeImageModel;
    }

    public List<Product> getProductRecent(Integer count){
        List<Product> productList = productMapper.selectList();
        return productList;
    }

    public List<Product> getAllProductInCategory(Integer categoryId){
        List<Product> productList = productMapper.selectListByCategoryId(categoryId);
        return productList;
    }

    public ProductImageProModel getProductDetail(Integer id){
        ProductImageProModel productImageProModel = new ProductImageProModel();
        Product product = productMapper.selectByPrimaryKey(id);
        productImageProModel.setId(product.getId());
        productImageProModel.setName(product.getName());
        productImageProModel.setPrice(product.getPrice());
        productImageProModel.setPrice(product.getPrice());
        productImageProModel.setStock(product.getStock());
        productImageProModel.setCategoryId(product.getCategoryId());
        productImageProModel.setMainImgUrl(product.getMainImgUrl());
        productImageProModel.setImgId(product.getImgId());
        List<ProductImage> productImageList = productImageMapper.selectByProductId(id);
        List<ProductImageModel> productImageModelList = new ArrayList<>();
        ProductImageModel productImageModel = null;
        for (ProductImage productImage:productImageList){
            if(productImage!=null){
                if (productImage.getImgId()!=null){
                    productImageModel = new ProductImageModel();
                    Image image = imageMapper.selectByPrimaryKey(productImage.getImgId());
                    productImageModel.setId(productImage.getId());
                    productImageModel.setImgId(productImage.getImgId());
                    productImageModel.setOrder(productImage.getOrder());
                    productImageModel.setProductId(productImage.getProductId());
                    productImageModel.setImage(image);
                    productImageModelList.add(productImageModel);
                }
            }

        }
        productImageProModel.setProductImageModelList(productImageModelList);
        List<ProductProperty> productPropertyList = productPropertyMapper.selectByProductId(id);
        productImageProModel.setProductPropertyList(productPropertyList);
        return productImageProModel;
    }

    public void createOrUpdateAddr(UserAddress userAddress,String token) throws Exception{
        logger.info("获取到前端传过来的token令牌："+token);
        //用token去缓存中找对应的uid
        TokenCacheModel tokenCacheModel =tokenService.getMsgFromCacha(token);
        String uid ="";
        if (tokenCacheModel!=null){
            uid=tokenCacheModel.getUid();
        }
        int userId = Integer.parseInt(uid);
        User user = userMapper.selectByPrimaryKey(userId);
        if (user!=null && userAddress!=null){
            userAddress.setUserId(userId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            userAddress.setUpdateTime(Integer.parseInt(sdf.format(new Date())));
            UserAddress userAddress1 = userAddressMapper.selectByUserId(userId);
            if (userAddress1==null){
                logger.info("用户地址表为空，插入数据");
                userAddressMapper.insertSelective(userAddress);
            }else {
                userAddress.setId(userAddress1.getId());
                logger.info("用户地址表非空，更新数据");
                userAddressMapper.updateByPrimaryKeySelective(userAddress);
            }
        }else {
            throw new Exception("用户不存在或者用户地址不存在");
        }
    }
}
