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
    private static String name = "zhangsan";
    private static SharedPreferences preferences;
    private static RequestQueue requestQueue;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        AppClient.name = name;
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


    public static void setJlSc(String jlSc) {
        preferences.edit().putString("JlSc", jlSc).apply();
    }

    public static String getJlSc() {
        return preferences.getString("JlSc", "");
    }

    public String getID() {
        return preferences.getString("Num", "13,1");
    }

    public void setID(String id) {
        preferences.edit().putString("Num", id).apply();
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

    public boolean getLight_1() {
        return preferences.getBoolean("light_1", false);
    }

    public void setLight_1(boolean light) {
        preferences.edit().putBoolean("light_1", light).apply();
    }
    public boolean getAir_1() {
        return preferences.getBoolean("air_1", false);
    }

    public void setAir_1(boolean light) {
        preferences.edit().putBoolean("air_1", light).apply();
    }
    public int getWd_1() {
        return preferences.getInt("wd_1",20);
    }

    public void setWd_1(int wd) {
        preferences.edit().putInt("wd_1", wd).apply();
    }




    public boolean getLight_2() {
        return preferences.getBoolean("light_2", false);
    }

    public void setLight_2(boolean light) {
        preferences.edit().putBoolean("light_2", light).apply();
    }
    public boolean getAir_2() {
        return preferences.getBoolean("air_2", false);
    }

    public void setAir_2(boolean light) {
        preferences.edit().putBoolean("air_2", light).apply();
    }
    public int getWd_2() {
        return preferences.getInt("wd_2",20);
    }

    public void setWd_2(int wd) {
        preferences.edit().putInt("wd_2", wd).apply();
    }






    public boolean getLight_3() {
        return preferences.getBoolean("light_3", false);
    }

    public void setLight_3(boolean light) {
        preferences.edit().putBoolean("light_3", light).apply();
    }
    public boolean getAir_3() {
        return preferences.getBoolean("air_3", false);
    }

    public void setAir_3(boolean light) {
        preferences.edit().putBoolean("air_3", light).apply();
    }
    public int getWd_3() {
        return preferences.getInt("wd_3",20);
    }

    public void setWd_3(int wd) {
        preferences.edit().putInt("wd_3", wd).apply();
    }



    public boolean getLight_4() {
        return preferences.getBoolean("light_4", false);
    }

    public void setLight_4(boolean light) {
        preferences.edit().putBoolean("light_4", light).apply();
    }
    public boolean getAir_4() {
        return preferences.getBoolean("air_4", false);
    }

    public void setAir_4(boolean light) {
        preferences.edit().putBoolean("air_4", light).apply();
    }
    public int getWd_4() {
        return preferences.getInt("wd_4",20);
    }

    public void setWd_4(int wd) {
        preferences.edit().putInt("wd_4", wd).apply();
    }

}
