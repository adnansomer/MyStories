package com.adnansomer.mystories.WebServiceModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Story {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("createdAt")
    @Expose
    private Integer createdAt;
    @SerializedName("is_video")
    @Expose
    private Boolean isVideo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(Boolean isVideo) {
        this.isVideo = isVideo;
    }

}