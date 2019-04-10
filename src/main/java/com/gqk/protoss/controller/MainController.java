package com.gqk.protoss.controller;

import com.gqk.protoss.controller.rest.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/4/10.
 */
@RestController
@RequestMapping(value = "/api/v1")
public class MainController {

    public Result<Integer> getBanner() {
        return null;
    }

}
