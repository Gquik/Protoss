package com.gqk.protoss.service;

import com.gqk.protoss.dao.CategoryMapper;
import com.gqk.protoss.dao.ImageMapper;
import com.gqk.protoss.entity.Category;
import com.gqk.protoss.model.CategoryImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ImageMapper imageMapper;

    public List<CategoryImageModel> getAllCategories(){
        List<CategoryImageModel> categoryImageModelList = new ArrayList<>();
        List<Category> categoryList = categoryMapper.selectList();
        CategoryImageModel categoryImageModel = null;
        for (Category category : categoryList){
            categoryImageModel = new CategoryImageModel();
            categoryImageModel.setId(category.getId());
            categoryImageModel.setName(category.getName());
            categoryImageModel.setTopicImgId(category.getTopicImgId());
            if (category.getTopicImgId()!=null){
                categoryImageModel.setImage(imageMapper.selectByPrimaryKey(category.getTopicImgId()));
            }
            categoryImageModelList.add(categoryImageModel);
        }
        return categoryImageModelList;
    }
}
