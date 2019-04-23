package com.gqk.protoss.model;

import com.gqk.protoss.entity.Category;
import com.gqk.protoss.entity.Image;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class CategoryImageModel extends Category implements Serializable{
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
