package com.example.create.bean2;

import org.litepal.crud.LitePalSupport;

/**
 * Create by 张瀛煜 on 2020-01-27
 */
public class GSCP extends LitePalSupport {
    private int id;
    private String name,photo,xh;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }
}
