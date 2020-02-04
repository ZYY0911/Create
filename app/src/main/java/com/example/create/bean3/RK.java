package com.example.create.bean3;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * 原料名称、型号、供应商，输入数量、单价、库存位置、
 * 采购员、联系人、对方账号，自动获取入库人、入库时间，并上传采购发票图片
 * Create by 张瀛煜 on 2020-02-02
 */
public class RK extends LitePalSupport implements Serializable {
    private int id;
    private String ylmc,xh,gys;//原料名称、型号、供应商
    private int num ,price;//数量、单价
    private String location;//库存位置
    private String time;//入库时间
    private String krname;//自动获取入库人
    private String countId;//对方账号
    private String shop;//采购员
    private String contacts;//联系人
    private String fp;//并上传采购发票图片

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYlmc() {
        return ylmc;
    }

    public void setYlmc(String ylmc) {
        this.ylmc = ylmc;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getGys() {
        return gys;
    }

    public void setGys(String gys) {
        this.gys = gys;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKrname() {
        return krname;
    }

    public void setKrname(String krname) {
        this.krname = krname;
    }

    public String getCountId() {
        return countId;
    }

    public void setCountId(String countId) {
        this.countId = countId;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getFp() {
        return fp;
    }

    public void setFp(String fp) {
        this.fp = fp;
    }
}
