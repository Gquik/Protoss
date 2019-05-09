package com.gqk.protoss.entity.enums;

public enum ScopeEnum {
    //用户和管理员
    USER(16), SUPER(32);
    private int value;

    ScopeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
