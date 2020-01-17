package com.example.create.bean;

import org.litepal.crud.LitePalSupport;

/**
 * Create by 张瀛煜 on 2020-01-16
 */
public class QYZP  extends LitePalSupport {
    private int id,state;
    private String user,qymc,hylx,szd,gsdz,tel,email,gw,xz,xlyq,zyyq,gzjl,gwyq,time,num,shtime;

    public int getId() {
        return id;
    }

    public String getShtime() {
        return shtime;
    }

    public String getHylx() {
        return hylx;
    }

    public void setHylx(String hylx) {
        this.hylx = hylx;
    }

    public void setShtime(String shtime) {
        this.shtime = shtime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    public String getSzd() {
        return szd;
    }

    public void setSzd(String szd) {
        this.szd = szd;
    }

    public String getGsdz() {
        return gsdz;
    }

    public void setGsdz(String gsdz) {
        this.gsdz = gsdz;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGw() {
        return gw;
    }

    public void setGw(String gw) {
        this.gw = gw;
    }

    public String getXz() {
        return xz;
    }

    public void setXz(String xz) {
        this.xz = xz;
    }

    public String getXlyq() {
        return xlyq;
    }

    public void setXlyq(String xlyq) {
        this.xlyq = xlyq;
    }

    public String getZyyq() {
        return zyyq;
    }

    public void setZyyq(String zyyq) {
        this.zyyq = zyyq;
    }

    public String getGzjl() {
        return gzjl;
    }

    public void setGzjl(String gzjl) {
        this.gzjl = gzjl;
    }

    public String getGwyq() {
        return gwyq;
    }

    public void setGwyq(String gwyq) {
        this.gwyq = gwyq;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
