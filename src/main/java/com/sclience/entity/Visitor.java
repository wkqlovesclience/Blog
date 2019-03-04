package com.sclience.entity;

import java.util.Date;

public class Visitor {
    private Integer visitorId;

    private String visitorIp;

    private String visitoryUrl;

    private String visitorCountry;

    private String visitorProvince;

    private String visitorCity;

    private String visitorArea;

    private Date visitoryTime;

    private Integer visitorCount;

    public Visitor() {
    }

    public Visitor(Integer visitorId, String visitorIp, String visitoryUrl, String visitorCountry, String visitorProvince, String visitorCity, String visitorArea, Date visitoryTime, Integer visitorCount) {
        this.visitorId = visitorId;
        this.visitorIp = visitorIp;
        this.visitoryUrl = visitoryUrl;
        this.visitorCountry = visitorCountry;
        this.visitorProvince = visitorProvince;
        this.visitorCity = visitorCity;
        this.visitorArea = visitorArea;
        this.visitoryTime = visitoryTime;
        this.visitorCount = visitorCount;
    }

    public Integer getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Integer visitorId) {
        this.visitorId = visitorId;
    }

    public String getVisitorIp() {
        return visitorIp;
    }

    public void setVisitorIp(String visitorIp) {
        this.visitorIp = visitorIp == null ? null : visitorIp.trim();
    }

    public String getVisitoryUrl() {
        return visitoryUrl;
    }

    public void setVisitoryUrl(String visitoryUrl) {
        this.visitoryUrl = visitoryUrl == null ? null : visitoryUrl.trim();
    }

    public String getVisitorCountry() {
        return visitorCountry;
    }

    public void setVisitorCountry(String visitorCountry) {
        this.visitorCountry = visitorCountry == null ? null : visitorCountry.trim();
    }

    public String getVisitorProvince() {
        return visitorProvince;
    }

    public void setVisitorProvince(String visitorProvince) {
        this.visitorProvince = visitorProvince == null ? null : visitorProvince.trim();
    }

    public String getVisitorCity() {
        return visitorCity;
    }

    public void setVisitorCity(String visitorCity) {
        this.visitorCity = visitorCity == null ? null : visitorCity.trim();
    }

    public String getVisitorArea() {
        return visitorArea;
    }

    public void setVisitorArea(String visitorArea) {
        this.visitorArea = visitorArea == null ? null : visitorArea.trim();
    }

    public Date getVisitoryTime() {
        return visitoryTime;
    }

    public void setVisitoryTime(Date visitoryTime) {
        this.visitoryTime = visitoryTime;
    }

    public Integer getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(Integer visitorCount) {
        this.visitorCount = visitorCount;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "visitorId=" + visitorId +
                ", visitorIp='" + visitorIp + '\'' +
                ", visitoryUrl='" + visitoryUrl + '\'' +
                ", visitorCountry='" + visitorCountry + '\'' +
                ", visitorProvince='" + visitorProvince + '\'' +
                ", visitorCity='" + visitorCity + '\'' +
                ", visitorArea='" + visitorArea + '\'' +
                ", visitoryTime=" + visitoryTime +
                ", visitorCount=" + visitorCount +
                '}';
    }
}