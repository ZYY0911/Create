package com.example.create.bean2;

/**
 * Create by 张瀛煜 on 2020-01-20
 */
public class GYSCXBean2 {
    private String name;
    private String image;
    private String first;


    public GYSCXBean2(String name, String image, String first) {
        this.name = name;
        this.image = image;
        this.first = first;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
