package com.gqk.protoss.controller;

import com.gqk.protoss.entity.Product;
import com.gqk.protoss.entity.UserAddress;
import com.gqk.protoss.model.BannerItemImageModel;
import com.gqk.protoss.model.ProductImageProModel;
import com.gqk.protoss.model.ThemeImageModel;
import com.gqk.protoss.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2019/4/10.
 */
@RestController
@RequestMapping(value = "/api/v1")
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping(value = "banner",method = RequestMethod.GET)
    public BannerItemImageModel getBanner(@RequestParam Integer id) {
        return mainService.getBanner(id);
    }

    @RequestMapping(value = "theme",method = RequestMethod.GET)
    public List<ThemeImageModel> getTheme(@RequestParam Integer[] idList) {
        return mainService.getTheme(idList);
    }

    @RequestMapping(value = "theme-products",method = RequestMethod.GET)
    public ThemeImageModel getThemeProducts(@RequestParam Integer id) {
        return mainService.getThemeProducts(id);
    }

    @RequestMapping(value = "product-recent",method = RequestMethod.GET)
    public List<Product> getProductRecent(@RequestParam Integer count) {
        return mainService.getProductRecent(count);
    }

    @RequestMapping(value = "product/by_category",method = RequestMethod.GET)
    public List<Product> getAllProductInCategory(@RequestParam Integer id) {
        return mainService.getAllProductInCategory(id);
    }

    @RequestMapping(value = "product-detail",method = RequestMethod.GET)
    public ProductImageProModel getProductDetail(@RequestParam Integer id) {
        return mainService.getProductDetail(id);
    }

    @RequestMapping(value = "address",method = RequestMethod.POST)
    public void createOrUpdateAddr(@RequestBody UserAddress userAddress, HttpServletRequest httpServletRequest) throws Exception{
        String token = httpServletRequest.getHeader("token");
        mainService.createOrUpdateAddr(userAddress,token);
    }

}
