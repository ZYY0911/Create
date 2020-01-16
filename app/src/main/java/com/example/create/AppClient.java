package com.example.create;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.litepal.LitePal;

/**
 * Create by 张瀛煜 on 2020-01-15
 */
public class AppClient extends Application {
    private String name = "zhangsan";
    private static SharedPreferences preferences;
    private static RequestQueue requestQueue;

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
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        requestQueue = Volley.newRequestQueue(this);
    }

    public static void add(JsonObjectRequest jsonObjectRequest) {
        requestQueue.add(jsonObjectRequest);
    }

    public String getKt() {
        return preferences.getString("Kt", "热风");
    }

    public void setKt(String info) {
        preferences.edit().putString("Kt", info).apply();
    }

    public static void addUser(String username) {
        preferences.edit().putString("username", username).apply();
    }
    public static String getUser() {
        return preferences.getString("username", "xxx");
    }
    public void setLight(boolean light) {
        preferences.edit().putBoolean("light", light).apply();
    }
    public boolean getLight() {
        return preferences.getBoolean("light", false);
    }
}
