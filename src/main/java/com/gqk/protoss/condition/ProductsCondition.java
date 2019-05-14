package com.gqk.protoss.condition;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductsCondition implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private boolean haveStock;
    private int count;
    private String name;
    private BigDecimal totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHaveStock() {
        return haveStock;
    }

    public void setHaveStock(boolean haveStock) {
        this.haveStock = haveStock;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
