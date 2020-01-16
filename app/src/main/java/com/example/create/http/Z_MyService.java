package com.example.create.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.create.AppClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fi.iki.elonen.NanoHTTPD;

/**
 * Create by 张瀛煜 on 2020-01-16
 */
public class Z_MyService extends NanoHTTPD {
    public static final int PORT = 3333;
    private AppClient appClient;

    public Z_MyService(int port, AppClient appClient) {
        super(3333);
        this.appClient = appClient;
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        Map<String ,String> file;
        JSONObject allJson = new JSONObject();
        try {
            allJson.put("RESULT", "S");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            switch (uri) {
                case "/get_factory_info":
                    Random random = new Random();
                    JSONObject jsonObject = new JSONObject();
                    JSONArray jsonArray = new JSONArray();
                    int wd = random.nextInt(40);
                    int dl = random.nextInt(150);
                    int dl2 = random.nextInt(120);
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("outWd", wd);
                    jsonObject1.put("intWd", (wd - 5));
                    if (dl > dl2) {
                        jsonObject1.put("butteryIn", dl);
                        jsonObject1.put("butteryOut", dl2);
                    } else {
                        jsonObject1.put("butteryIn", dl2);
                        jsonObject1.put("butteryOut", dl);
                    }
                    if (appClient.getLight()) {
                        jsonObject1.put("light", "开启");
                    } else {
                        jsonObject1.put("light", "关闭");
                    }
                    jsonObject1.put("kt", appClient.getKt());
                    jsonArray.put(jsonObject1);
                    jsonObject.put("ROWS_DETAIL", jsonArray);
                    jsonObject.put("RESULT", "S");
                    return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, jsonObject.toString());
                case "/set_factory_air":
                    file = session.getParms();
                    appClient.setKt(file.get("air"));
                    return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, allJson.toString());
                case "/set_factory_light":
                    file = session.getParms();
                    if ("开启".equals(file.get("light"))){
                        appClient.setLight(true);
                    }else if("关闭".equals(file.get("light"))) {
                        appClient.setLight(false);
                    }else {
                        return null;
                    }
                    return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, allJson.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
