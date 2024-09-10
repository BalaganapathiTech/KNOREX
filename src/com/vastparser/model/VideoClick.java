package com.vastparser.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoClick {
    @JsonProperty("id")
    private String id;

    @JsonProperty("url")
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
