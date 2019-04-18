package com.gqk.protoss.controller;

import com.gqk.protoss.controller.rest.Result;
import com.gqk.protoss.entity.Product;
import com.gqk.protoss.model.BannerItemImageModel;
import com.gqk.protoss.model.ThemeImageModel;
import com.gqk.protoss.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
