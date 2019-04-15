package com.gqk.protoss.model;

import com.gqk.protoss.entity.Banner;
import com.gqk.protoss.entity.BannerItem;

import java.util.List;

public class BannerItemImageModel {

    private Banner banner;

    private List<BannerItemModel> items;

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public List<BannerItemModel> getItems() {
        return items;
    }

    public void setItems(List<BannerItemModel> items) {
        this.items = items;
    }
}
