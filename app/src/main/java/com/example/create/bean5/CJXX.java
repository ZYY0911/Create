package com.example.create.bean5;

import org.litepal.crud.LitePalSupport;

/**
 * Create by 张瀛煜 on 2020-02-06 ：）
 */
public class CJXX extends LitePalSupport {
    private int id;
    private int num,gz,wd,dl,ylrl,qcrl;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getGz() {
        return gz;
    }

    public void setGz(int gz) {
        this.gz = gz;
    }

    public int getWd() {
        return wd;
    }

    public void setWd(int wd) {
        this.wd = wd;
    }

    public int getDl() {
        return dl;
    }

    public void setDl(int dl) {
        this.dl = dl;
    }

    public int getYlrl() {
        return ylrl;
    }

    public void setYlrl(int ylrl) {
        this.ylrl = ylrl;
    }

    public int getQcrl() {
        return qcrl;
    }

    public void setQcrl(int qcrl) {
        this.qcrl = qcrl;
    }
}
