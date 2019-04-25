package com.gqk.protoss.model;

import com.gqk.protoss.entity.Image;
import com.gqk.protoss.entity.ProductImage;

public class ProductImageModel extends ProductImage{
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
