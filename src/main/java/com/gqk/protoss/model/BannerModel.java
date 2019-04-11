package com.gqk.protoss.model;

import com.gqk.protoss.entity.Banner;
import com.gqk.protoss.entity.BannerItem;

import java.util.List;

public class BannerModel{

    private Banner banner;

    private List<BannerItem> items;

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public List<BannerItem> getItems() {
        return items;
    }

    public void setItems(List<BannerItem> items) {
        this.items = items;
    }
}
