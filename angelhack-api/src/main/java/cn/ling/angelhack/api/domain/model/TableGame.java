package cn.ling.angelhack.api.domain.model;

import java.io.Serializable;

public class TableGame implements Serializable {
    private Integer id;

    private String name;

    private String desc;

    private String picUrl;

    private Integer minNum;

    private Integer maxNum;

    private String scores;

    private static final long serialVersionUID = 1L;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Integer getMinNum() {
        return minNum;
    }

    public void setMinNum(Integer minNum) {
        this.minNum = minNum;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores == null ? null : scores.trim();
    }
}