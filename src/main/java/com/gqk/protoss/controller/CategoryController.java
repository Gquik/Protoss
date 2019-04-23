package com.gqk.protoss.controller;

import com.gqk.protoss.model.CategoryImageModel;
import com.gqk.protoss.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<CategoryImageModel> getAllCategories() {
        return categoryService.getAllCategories();
    }

}
