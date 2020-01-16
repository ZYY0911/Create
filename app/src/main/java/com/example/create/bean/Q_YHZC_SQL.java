package com.example.create.bean;


import org.litepal.crud.LitePalSupport;

public class Q_YHZC_SQL extends LitePalSupport {
    private String yhm;
    private String mima;

    public String getYhm() {
        return yhm;
    }

    public void setYhm(String yhm) {
        this.yhm = yhm;
    }

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }

    public String getYx() {
        return yx;
    }

    public void setYx(String yx) {
        this.yx = yx;
    }

    public Q_YHZC_SQL(String yhm, String mima, String yx) {
        this.yhm = yhm;
        this.mima = mima;
        this.yx = yx;
    }

    private String yx;
}
