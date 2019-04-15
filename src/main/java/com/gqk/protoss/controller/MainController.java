package com.gqk.protoss.controller;

import com.gqk.protoss.condition.ThemeImageConditon;
import com.gqk.protoss.controller.rest.Result;
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
    public Result<BannerItemImageModel> getBanner(@RequestParam Integer id) {
        return mainService.getBanner(id);
    }

    @RequestMapping(value = "theme",method = RequestMethod.POST)
    public Result<List<ThemeImageModel>> getTheme(@RequestBody ThemeImageConditon themeImageConditon) {
        List<Integer> idList = themeImageConditon.getIdList();
        return mainService.getTheme(idList);
    }

}
