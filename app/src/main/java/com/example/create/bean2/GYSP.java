package com.example.create.bean2;

import org.litepal.crud.LitePalSupport;

/**
 * Create by 张瀛煜 on 2020-01-19
 * 供应商原料列表 供应商编号，原料名称、原料编号、原料图片、价格
 */
public class GYSP extends LitePalSupport {
    private int id,gysNum;
    private String ylName,ylNum,ylPhoto;
    private int ylPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGysNum() {
        return gysNum;
    }

    public void setGysNum(int gysNum) {
        this.gysNum = gysNum;
    }

    public String getYlName() {
        return ylName;
    }

    public void setYlName(String ylName) {
        this.ylName = ylName;
    }

    public String getYlNum() {
        return ylNum;
    }

    public void setYlNum(String ylNum) {
        this.ylNum = ylNum;
    }

    public String getYlPhoto() {
        return ylPhoto;
    }

    public void setYlPhoto(String ylPhoto) {
        this.ylPhoto = ylPhoto;
    }

    public int getYlPrice() {
        return ylPrice;
    }

    public void setYlPrice(int ylPrice) {
        this.ylPrice = ylPrice;
    }
}
