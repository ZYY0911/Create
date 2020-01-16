package com.example.create.bean;

import org.litepal.crud.LitePalSupport;

/**
 * Create by 张瀛煜 on 2020-01-15
 */
public class JLLB extends LitePalSupport {
    private int id;
    private String user,jlFile,jlName,jlInfo,jlTime;

    public JLLB() {
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
}
