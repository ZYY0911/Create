package com.example.create.bean3;

/**
 * Create by 张瀛煜 on 2020-02-03
 */
public class CK_CG {
    private int id;
    private String gys;
    private int cgl, yl, price;
    private int input;


    public CK_CG(int id, String gys, int cgl, int yl, int price, int input) {
        this.id = id;
        this.gys = gys;
        this.cgl = cgl;
        this.yl = yl;
        this.price = price;
        this.input = input;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGys() {
        return gys;
    }

    public void setGys(String gys) {
        this.gys = gys;
    }

    public int getCgl() {
        return cgl;
    }

    public void setCgl(int cgl) {
        this.cgl = cgl;
    }

    public int getYl() {
        return yl;
    }

    public void setYl(int yl) {
        this.yl = yl;
    }
}
