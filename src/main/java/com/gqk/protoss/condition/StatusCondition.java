package com.gqk.protoss.condition;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class StatusCondition {
    private int orderId;
    private boolean pass;
    private String errorMsg;
    private BigDecimal orderPrice;
    private int orderCount;
    private Date createTime;
    private List<ProductsCondition> productsConditionList;

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public List<ProductsCondition> getProductsConditionList() {
        return productsConditionList;
    }

    public void setProductsConditionList(List<ProductsCondition> productsConditionList) {
        this.productsConditionList = productsConditionList;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
