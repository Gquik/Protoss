package com.gqk.protoss.model;

import com.gqk.protoss.entity.BannerItem;
import com.gqk.protoss.entity.Image;

public class BannerItemModel {

    private BannerItem bannerItem;

    private Image image;

    public BannerItem getBannerItem() {
        return bannerItem;
    }

    public void setBannerItem(BannerItem bannerItem) {
        this.bannerItem = bannerItem;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
