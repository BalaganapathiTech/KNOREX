package com.vastparser.model;


import java.util.ArrayList;
import java.util.List;

public class Creative {
    private String id;
    private Linear linear;
    private List<CompanionBanner> companionBanners = new ArrayList<>();


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Linear getLinear() { return linear; }
    public void setLinear(Linear linear) { this.linear = linear; }
    public List<CompanionBanner> getCompanionBanners() { return companionBanners; }
    public void setCompanionBanners(List<CompanionBanner> companionBanners) { this.companionBanners = companionBanners; }
}
