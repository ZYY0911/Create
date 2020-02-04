package com.example.create.bean3;

import org.litepal.crud.LitePalSupport;

/**
 * Create by 张瀛煜 on 2020-02-03
 */
public class YZ extends LitePalSupport {
    private int id,apple,banban,cherry,grape,mango,orange;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApple() {
        return apple;
    }

    public void setApple(int apple) {
        this.apple = apple;
    }

    public int getBanban() {
        return banban;
    }

    public void setBanban(int banban) {
        this.banban = banban;
    }

    public int getCherry() {
        return cherry;
    }

    public void setCherry(int cherry) {
        this.cherry = cherry;
    }

    public int getGrape() {
        return grape;
    }

    public void setGrape(int grape) {
        this.grape = grape;
    }

    public int getMango() {
        return mango;
    }

    public void setMango(int mango) {
        this.mango = mango;
    }

    public int getOrange() {
        return orange;
    }

    public void setOrange(int orange) {
        this.orange = orange;
    }
}
