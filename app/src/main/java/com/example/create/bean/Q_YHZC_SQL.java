package com.example.create.bean;


import org.litepal.crud.LitePalSupport;

public class Q_YHZC_SQL extends LitePalSupport {
    private int id;
    private String yhm;
    private String mima;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJine() {
        return jine;
    }

    public void setJine(int jine) {
        this.jine = jine;
    }

    public Q_YHZC_SQL(String yhm, String mima, int jine, String yx) {
        this.yhm = yhm;
        this.mima = mima;
        this.jine = jine;
        this.yx = yx;
    }

    private int jine;
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


    private String yx;
}
