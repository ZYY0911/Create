package com.example.create.bean2;

import org.litepal.crud.LitePalSupport;

/**
 * Create by 张瀛煜 on 2020-01-19
 * 供应商 供应商编号、名称、所在城市、地点、法人、联系人、电话、业务范围、图片
 */
public class GYS extends LitePalSupport {
    private int id,gysNum;
    private String gysName,gysCity,gysLocation,gysLaw,gysPeople,gysTel,gysRange,gysPhoto;

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

    public String getGysName() {
        return gysName;
    }

    public void setGysName(String gysName) {
        this.gysName = gysName;
    }

    public String getGysCity() {
        return gysCity;
    }

    public void setGysCity(String gysCity) {
        this.gysCity = gysCity;
    }

    public String getGysLocation() {
        return gysLocation;
    }

    public void setGysLocation(String gysLocation) {
        this.gysLocation = gysLocation;
    }

    public String getGysLaw() {
        return gysLaw;
    }

    public void setGysLaw(String gysLaw) {
        this.gysLaw = gysLaw;
    }

    public String getGysPeople() {
        return gysPeople;
    }

    public void setGysPeople(String gysPeople) {
        this.gysPeople = gysPeople;
    }

    public String getGysTel() {
        return gysTel;
    }

    public void setGysTel(String gysTel) {
        this.gysTel = gysTel;
    }

    public String getGysRange() {
        return gysRange;
    }

    public void setGysRange(String gysRange) {
        this.gysRange = gysRange;
    }

    public String getGysPhoto() {
        return gysPhoto;
    }

    public void setGysPhoto(String gysPhoto) {
        this.gysPhoto = gysPhoto;
    }
}
