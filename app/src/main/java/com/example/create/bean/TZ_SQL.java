package com.example.create.bean;

import org.litepal.crud.LitePalSupport;

public class TZ_SQL extends LitePalSupport {
    private int is;
    private String neirong,time;

    public int getIs() {
        return is;
    }

    public void setIs(int is) {
        this.is = is;
    }

    public String getNeirong() {
        return neirong;
    }

    public void setNeirong(String neirong) {
        this.neirong = neirong;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TZ_SQL(int is, String neirong, String time) {
        this.is = is;
        this.neirong = neirong;
        this.time = time;
    }
}
