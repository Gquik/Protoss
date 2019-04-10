package com.gqk.protoss.controller.rest;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> extends AbstractResult {
    private T data; //单个数据

    @SuppressWarnings(value = "unchecked")
    public static <T> Result<T> ok() {
        return new Result(0, null);
    }

    public static <T> Result<T> ok(String message) {
        return new Result(0, message);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> Result<T> warning() {
        return new Result(1, null);
    }

    public static <T> Result<T> warning(String message) {
        return new Result(1, message);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> Result warning(String message, T user) {
        Result res = new Result(1, message);
        if (Objects.nonNull(user)) {
            res.data = user;
        } else {
            res.setCode(-1);
        }
        return res;
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> Result<T> error(int code, String message) {
        return new Result(code, message);
    }

    public static <T> Result<T> error(String message) {
        return new Result(-1, message);
    }

    public static <T> Result<T> error(int code) {
        return new Result(-1, null);
    }

    public Result(int code, String message) {
        super(code, message);
    }

    public static <T> Result<T> one(T obj) {
        Result<T> res = new Result(0, null);
        if (obj == null) {
            res.setCode(-1);
        } else {
            res.data = obj;
        }
        return res;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
