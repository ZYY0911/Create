package com.example.create.bean5;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * Create by 张瀛煜 on 2020-02-06 ：）
 */
public class SCX extends LitePalSupport implements Serializable {
    private int id,cj;
    private String state,hj,ts,scx;

    public SCX(int cj, String scx, String state, String hj, String ts) {
        this.cj = cj;
        this.scx = scx;
        this.state = state;
        this.hj = hj;
        this.ts = ts;
    }

    public SCX() {
    }

    public int getCj() {
        return cj;
    }

    public void setCj(int cj) {
        this.cj = cj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScx() {
        return scx;
    }

    public void setScx(String scx) {
        this.scx = scx;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHj() {
        return hj;
    }

    public void setHj(String hj) {
        this.hj = hj;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }
}
