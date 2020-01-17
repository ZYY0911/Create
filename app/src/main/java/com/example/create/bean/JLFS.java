package com.example.create.bean;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

/**
 * Create by 张瀛煜 on 2020-01-17
 */
public class JLFS extends LitePalSupport {
    private String sendTime;
    private int id;
    private int gsId;
    private String user, jlFile, jlName, jlInfo, jlTime;

    public int getGsId() {
        return gsId;
    }

    public void setGsId(int gsId) {
        this.gsId = gsId;
    }

    public JLFS() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getJlFile() {
        return jlFile;
    }

    public void setJlFile(String jlFile) {
        this.jlFile = jlFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJlName() {
        return jlName;
    }

    public void setJlName(String jlName) {
        this.jlName = jlName;
    }

    public String getJlInfo() {
        return jlInfo;
    }

    public void setJlInfo(String jlInfo) {
        this.jlInfo = jlInfo;
    }

    public String getJlTime() {
        return jlTime;
    }

    public void setJlTime(String jlTime) {
        this.jlTime = jlTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
