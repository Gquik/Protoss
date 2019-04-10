package com.gqk.protoss.controller.rest;

public abstract class AbstractResult {
    private int code;
    private String message;

    public AbstractResult(int code) {
        this.code = code;
    }

    public AbstractResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
