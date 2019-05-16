package com.gqk.protoss.service.exceptions;

public class BaseException extends Exception {
    private int code;
    private String msg;
    private int errorCode;

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

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public BaseException() {
    }

    public BaseException(int code, String msg, int errorCode) {
        this.code = code;
        this.msg = msg;
        this.errorCode = errorCode;
    }
}
