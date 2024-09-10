package com.vastparser.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Extension {
    @JsonProperty("type")
    private String type;

    @JsonProperty("totalAvailable")
    private String totalAvailable;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotalAvailable() {
        return totalAvailable;
    }

    public void setTotalAvailable(String totalAvailable) {
        this.totalAvailable = totalAvailable;
    }
}
