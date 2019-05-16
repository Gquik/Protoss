package com.gqk.protoss.service.exceptions;

public enum ErrorCode {
    //下单异常
    ORDER_NOT_EXIST(20001,"订单不存在，请检查ID"),
    ORDER_ID_IS_NULL(20002,"订单号不能为空"),
    ORDER_ADDRESS_NOT_EXIST(20003,"用户收货地址不存在，下单失败"),

    //权限异常
    USER_POWER_NOT_ENOUGH(30001,"该用户权限不足"),
    USER_TOKEN_INVALID(30002,"令牌已失效");

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
