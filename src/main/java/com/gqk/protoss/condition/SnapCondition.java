package com.gqk.protoss.condition;

import java.math.BigDecimal;
import java.util.List;

public class SnapCondition {
    private BigDecimal orderPrice;
    private int totalCount;
    private String snapAddress;
    private List<ProductsCondition> productsConditionList;
    private String snapName;
    private String snapImg;

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getSnapAddress() {
        return snapAddress;
    }

    public void setSnapAddress(String snapAddress) {
        this.snapAddress = snapAddress;
    }

    public List<ProductsCondition> getProductsConditionList() {
        return productsConditionList;
    }

    public void setProductsConditionList(List<ProductsCondition> productsConditionList) {
        this.productsConditionList = productsConditionList;
    }

    public String getSnapName() {
        return snapName;
    }

    public void setSnapName(String snapName) {
        this.snapName = snapName;
    }

    public String getSnapImg() {
        return snapImg;
    }

    public void setSnapImg(String snapImg) {
        this.snapImg = snapImg;
    }
}
