package com.gqk.protoss.controller;

import com.gqk.protoss.controller.rest.Result;
import com.gqk.protoss.model.BannerModel;
import com.gqk.protoss.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/4/10.
 */
@RestController
@RequestMapping(value = "/api/v1")
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping(value = "getBanner",method = RequestMethod.GET)
    public Result<BannerModel> getBanner(@RequestParam Integer id) {
        return mainService.getBanner(id);
    }

}
