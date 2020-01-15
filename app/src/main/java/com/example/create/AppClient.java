package com.example.create;

import android.app.Application;

import org.litepal.LitePal;

/**
 * Create by 张瀛煜 on 2020-01-15
 */
public class AppClient extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
