package com.gqk.protoss.model;

import com.gqk.protoss.entity.Product;
import com.gqk.protoss.entity.ProductProperty;

import java.util.List;

public class ProductImageProModel extends Product{

    private List<ProductImageModel> productImageModelList;

    private List<ProductProperty> productPropertyList;

    public List<ProductImageModel> getProductImageModelList() {
        return productImageModelList;
    }

    public void setProductImageModelList(List<ProductImageModel> productImageModelList) {
        this.productImageModelList = productImageModelList;
    }

    public List<ProductProperty> getProductPropertyList() {
        return productPropertyList;
    }

    public void setProductPropertyList(List<ProductProperty> productPropertyList) {
        this.productPropertyList = productPropertyList;
    }
}
