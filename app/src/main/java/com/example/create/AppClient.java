package com.example.create;

import android.app.Application;

import org.litepal.LitePal;

/**
 * Create by 张瀛煜 on 2020-01-15
 */
public class AppClient extends Application {
    private String name = "zhangsan";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);

    }
}
