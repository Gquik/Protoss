package com.gqk.protoss.model;

import com.gqk.protoss.entity.Image;
import com.gqk.protoss.entity.Product;
import com.gqk.protoss.entity.Theme;

import java.util.List;

public class ThemeImageModel extends Theme{
    private List<Product> products;
    private Image topicImage;
    private Image headImage;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Image getTopicImage() {
        return topicImage;
    }

    public void setTopicImage(Image topicImage) {
        this.topicImage = topicImage;
    }

    public Image getHeadImage() {
        return headImage;
    }

    public void setHeadImage(Image headImage) {
        this.headImage = headImage;
    }
}
