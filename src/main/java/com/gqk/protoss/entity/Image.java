package com.gqk.protoss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Image {
    @JsonIgnore
    private Integer id;

    private String url;

    @JsonIgnore
    private Byte from;

    @JsonIgnore
    private Integer deleteTime;

    @JsonIgnore
    private Integer updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Byte getFrom() {
        return from;
    }

    public void setFrom(Byte from) {
        this.from = from;
    }

    public Integer getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Integer deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }
}