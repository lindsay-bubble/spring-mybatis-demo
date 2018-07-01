package cn.ling.angelhack.api.domain.model;

import java.io.Serializable;

public class PersonInfo implements Serializable {
    private Integer id;

    private String picPath;

    private String scores;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores == null ? null : scores.trim();
    }
}