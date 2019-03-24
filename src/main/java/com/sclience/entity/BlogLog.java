package com.sclience.entity;

import java.util.Date;

public class BlogLog {
    private Integer logId;

    private String logTitle;

    private String logContent;

    private Integer logOperatorId;

    private Date logDate;

    private Blogger blogger;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLogTitle() {
        return logTitle;
    }

    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle == null ? null : logTitle.trim();
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent == null ? null : logContent.trim();
    }

    public Integer getLogOperatorId() {
        return logOperatorId;
    }

    public void setLogOperatorId(Integer logOperatorId) {
        this.logOperatorId = logOperatorId;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public Blogger getBlogger() {
        return blogger;
    }

    public void setBlogger(Blogger blogger) {
        this.blogger = blogger;
    }
}