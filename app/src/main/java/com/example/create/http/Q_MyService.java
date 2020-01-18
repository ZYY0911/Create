package com.example.create.http;

import android.util.Log;

import com.example.create.AppClient;
import com.example.create.bean.JBXX;
import com.example.create.bean.JLLB;
import com.example.create.bean.Q_YHZC_SQL;
import com.example.create.util.SimpData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fi.iki.elonen.NanoHTTPD;

/**
 * Create by 张瀛煜 on 2020-01-16
 */
public class Q_MyService extends NanoHTTPD {
    public static final int PORT = 3333;
    private AppClient appClient;

    public Q_MyService(int port, AppClient appClient) {
        super(3333);
        this.appClient = appClient;
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        Map<String, String> file;
        JSONObject yseJson = new JSONObject();
        JSONObject noJson = new JSONObject();
        try {
            yseJson.put("RESULT", "S");
            noJson.put("RESULT", "F");
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
                    return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                case "/set_factory_light":
                    file = session.getParms();
                    if ("开启".equals(file.get("light"))) {
                        appClient.setLight(true);
                    } else if ("关闭".equals(file.get("light"))) {
                        appClient.setLight(false);
                    } else {
                        return null;
                    }
                    return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                case "/get_root_info":
                    file = session.getParms();
                    List<JBXX> jbxx = LitePal.where("name=?", String.valueOf(file.get("UserName"))).find(JBXX.class);
                    if (jbxx.size() == 0) {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    } else {
                        JSONArray jsonArray1 = new JSONArray();
                        for (int i = 0; i < jbxx.size(); i++) {
                            JBXX jbxx1 = jbxx.get(i);
                            JSONObject jsonObject2 = new JSONObject();
                            jsonObject2.put("id", jbxx1.getId());
                            jsonObject2.put("name", jbxx1.getName());
                            jsonObject2.put("sex", jbxx1.getSex());
                            jsonObject2.put("province", jbxx1.getProvince());
                            jsonObject2.put("major", jbxx1.getMajor());
                            jsonObject2.put("school", jbxx1.getSchool());
                            jsonObject2.put("xl", jbxx1.getXl());
                            jsonObject2.put("gzjl", jbxx1.getGzjl());
                            jsonObject2.put("jyyx", jbxx1.getJyyx());
                            jsonObject2.put("email", jbxx1.getYx());
                            jsonObject2.put("photo", jbxx1.getPhoto());
                            jsonArray1.put(jsonObject2);
                        }
                        JSONObject jsonObject2 = new JSONObject();
                        jsonObject2.put("RESULT", "S");
                        jsonObject2.put("ROWS_DETAIL", jsonArray1);
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, jsonObject2.toString());
                    }
                case "/update_root_info":
                    file = session.getParms();
                    Log.e("eee", "serve: " + file.size());
                    if (file.size() == 10) {
                        JBXX jbxx1 = new JBXX();
                        jbxx1.setSex(file.get("sex"));
                        jbxx1.setProvince(file.get("province"));
                        jbxx1.setMajor(file.get("major"));
                        jbxx1.setSchool(file.get("school"));
                        jbxx1.setXl(file.get("xl"));
                        jbxx1.setGzjl(file.get("gzjl"));
                        jbxx1.setJyyx(file.get("jyyx"));
                        jbxx1.setYx(file.get("email"));
                        jbxx1.setPhoto(file.get("photo"));
                        jbxx1.updateAll("name=?", file.get("UserName"));
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }

                case "/get_jl_list":
                    file = session.getParms();
                    List<JLLB> jllbs = LitePal.where("user=?", String.valueOf(file.get("UserName"))).find(JLLB.class);
                    if (jllbs.size() == 0) {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    } else {
                        JSONArray jsonArray1 = new JSONArray();
                        for (int i = 0; i < jllbs.size(); i++) {
                            JLLB jllb = jllbs.get(i);
                            JSONObject jsonObject2 = new JSONObject();
                            jsonObject2.put("id", jllb.getId());
                            jsonObject2.put("jlName", jllb.getJlName());
                            jsonObject2.put("jlBz", jllb.getJlInfo());
                            jsonObject2.put("sendTime", jllb.getJlTime());
                            jsonArray1.put(jsonObject2);
                        }
                        JSONObject jsonObject2 = new JSONObject();
                        jsonObject2.put("RESULT", "S");
                        jsonObject2.put("ROWS_DETAIL", jsonArray1);
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, jsonObject2.toString());
                    }
                case "/add_jl":
                    file = session.getParms();
                    try {
                        JLLB jllb = new JLLB();
                        jllb.setJlTime(SimpData.Simp("yyyy-MM-dd HH:mm", new Date()));
                        jllb.setJlFile(file.get("file"));
                        jllb.setUser(file.get("UserName"));
                        jllb.setJlName(file.get("jlName"));
                        jllb.setJlInfo(file.get("jlInfo"));
                        jllb.save();
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } catch (Exception e) {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }
                case "/send_zp_info":
                    break;
                case "/get_username":
                    file = session.getParms();
                    List<Q_YHZC_SQL> sqls=LitePal.where("name?= ", String.valueOf(file.get("UserName"))).find(Q_YHZC_SQL.class);

                    break;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;


    }

}
