package com.example.create.bean;

public class Q_YPRY_bean {
    private  int photo;
    private String xm;
    private String date;
    private String xl;

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getXl() {
        return xl;
    }

    public void setXl(String xl) {
        this.xl = xl;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Q_YPRY_bean(int photo, String xm, String date, String xl, String job) {
        this.photo = photo;
        this.xm = xm;
        this.date = date;
        this.xl = xl;
        this.job = job;
    }

    private String job;
}
