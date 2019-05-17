package com.gqk.protoss.entity.enums;

public enum OrderStatusEnum {
    UNPAID(1),//待支付
    PAID(2),//已支付
    DELIVERED(3),//已发货
    PAYED_BUT_OUT_OF(4);//已支付带库存量不足

    private int value;

    OrderStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "OrderStatusEnum{" +
                "value=" + value +
                '}';
    }
}
