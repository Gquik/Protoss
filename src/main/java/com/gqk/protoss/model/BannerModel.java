package com.gqk.protoss.model;

import com.gqk.protoss.entity.Banner;
import com.gqk.protoss.entity.BannerItem;

import java.util.List;

public class BannerModel extends Banner{

    private List<BannerItem> items;

    public List<BannerItem> getItems() {
        return items;
    }

    public void setItems(List<BannerItem> items) {
        this.items = items;
    }
}
