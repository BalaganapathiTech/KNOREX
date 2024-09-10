package com.vastparser.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class MediaFile {
    @JsonProperty("type")
    private String type;

    @JsonProperty("bitrate")
    private int bitrate;

    @JsonProperty("width")
    private int width;

    @JsonProperty("height")
    private int height;

    @JsonProperty("source")
    private String source;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
