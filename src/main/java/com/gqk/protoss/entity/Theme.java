package com.gqk.protoss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class Theme {
    private Integer id;

    private String name;

    private String description;

    @JsonIgnore
    private Integer topicImgId;

    @JsonIgnore
    private Integer deleteTime;

    @JsonIgnore
    private Integer headImgId;

    @JsonIgnore
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getTopicImgId() {
        return topicImgId;
    }

    public void setTopicImgId(Integer topicImgId) {
        this.topicImgId = topicImgId;
    }

    public Integer getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Integer deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Integer getHeadImgId() {
        return headImgId;
    }

    public void setHeadImgId(Integer headImgId) {
        this.headImgId = headImgId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}