package com.example.create.bean5;

import org.litepal.crud.LitePalSupport;

/**
 * Create by 张瀛煜 on 2020-02-06 ：）
 */
public class WXCLBean {
    private int id;
    private String clnum, clxh, question, state, time,wxtime;
    private boolean is;

    public WXCLBean(int id, String clnum, String clxh, String question, String state, String time,String wxtime) {
        this.id = id;
        this.clnum = clnum;
        this.clxh = clxh;
        this.question = question;
        this.state = state;
        this.time = time;
        this.wxtime =wxtime;
    }

    public String getWxtime() {
        return wxtime;
    }

    public void setWxtime(String wxtime) {
        this.wxtime = wxtime;
    }

    public boolean isIs() {
        return is;
    }

    public void setIs(boolean is) {
        this.is = is;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClnum() {
        return clnum;
    }

    public void setClnum(String clnum) {
        this.clnum = clnum;
    }

    public String getClxh() {
        return clxh;
    }

    public void setClxh(String clxh) {
        this.clxh = clxh;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
