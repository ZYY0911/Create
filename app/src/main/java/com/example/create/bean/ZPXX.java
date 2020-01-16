package com.example.create.bean;

import org.litepal.crud.LitePalSupport;

/**
 * Create by 张瀛煜 on 2020-01-16
 */
public class ZPXX extends LitePalSupport {
    private int id;
    private String gzName,hyType,gwName,zyRepues,szCity,xlReques,zxLocation,lxEmail
    ,time;

    public ZPXX() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGzName() {
        return gzName;
    }

    public void setGzName(String gzName) {
        this.gzName = gzName;
    }

    public String getHyType() {
        return hyType;
    }

    public void setHyType(String hyType) {
        this.hyType = hyType;
    }

    public String getGwName() {
        return gwName;
    }

    public void setGwName(String gwName) {
        this.gwName = gwName;
    }

    public String getZyRepues() {
        return zyRepues;
    }

    public void setZyRepues(String zyRepues) {
        this.zyRepues = zyRepues;
    }

    public String getSzCity() {
        return szCity;
    }

    public void setSzCity(String szCity) {
        this.szCity = szCity;
    }

    public String getXlReques() {
        return xlReques;
    }

    public void setXlReques(String xlReques) {
        this.xlReques = xlReques;
    }

    public String getZxLocation() {
        return zxLocation;
    }

    public void setZxLocation(String zxLocation) {
        this.zxLocation = zxLocation;
    }

    public String getLxEmail() {
        return lxEmail;
    }

    public void setLxEmail(String lxEmail) {
        this.lxEmail = lxEmail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
