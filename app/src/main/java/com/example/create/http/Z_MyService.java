package com.example.create.http;

import android.app.Notification;
import android.util.Log;
import android.widget.ListView;

import com.example.create.AppClient;
import com.example.create.bean.JBXX;
import com.example.create.bean.JLFS;
import com.example.create.bean.JLLB;
import com.example.create.bean.QYZP;
import com.example.create.bean.Q_YHZC_SQL;
import com.example.create.bean.TZ_SQL;
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
                            jsonObject2.put("love", jbxx1.getLove());
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
                    if (file.size() == 11) {
                        JBXX jbxx1 = new JBXX();
                        jbxx1.setSex(file.get("sex"));
                        jbxx1.setProvince(file.get("province"));
                        jbxx1.setMajor(file.get("major"));
                        jbxx1.setSchool(file.get("school"));
                        jbxx1.setXl(file.get("xl"));
                        jbxx1.setLove(file.get("love"));
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
                case "/get_zp_infos":
                    List<QYZP> qyzps = LitePal.where("state=?", "2").find(QYZP.class);
                    JSONArray jsonArray1 = new JSONArray();
                    for (int i = 0; i < qyzps.size(); i++) {
                        QYZP qyzp = qyzps.get(i);
                        JSONObject jsonObject2 = new JSONObject();
                        jsonObject2.put("gsmc", qyzp.getQymc());
                        jsonObject2.put("hylx", qyzp.getHylx());
                        jsonObject2.put("gwmc", qyzp.getGw());
                        jsonObject2.put("major", qyzp.getZyyq());
                        jsonObject2.put("szcs", qyzp.getSzd());
                        jsonObject2.put("xlyq", qyzp.getXlyq());
                        jsonObject2.put("zxfw", qyzp.getXz());
                        jsonObject2.put("email", qyzp.getEmail());
                        jsonObject2.put("tel", qyzp.getTel());
                        jsonObject2.put("time", qyzp.getTime());
                        jsonArray1.put(jsonObject2);
                    }
                    JSONObject jsonObject2 = new JSONObject();
                    jsonObject2.put("RESULT", "S");
                    jsonObject2.put("ROWS_DETAIL", jsonArray1);
                    return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, jsonObject2.toString());
                case "/sc_zp_infos":
                    file = session.getParms();
                    if (!"".equals(file.get("id"))) {
                        String arr = AppClient.getJlSc();
                        if (arr.equals("")) {
                            arr += file.get("id") + "/" + SimpData.Simp("yyyy-MM-dd HH:mm", new Date());
                        } else {
                            arr += "," + file.get("id") + "/" + SimpData.Simp("yyyy-MM-dd HH:mm", new Date());
                        }
                        AppClient.setJlSc(arr);
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }
                case "/send_jl_infos":
                    file = session.getParms();
                    if (file.size() == 6) {
                        JLFS jlfs = new JLFS();
                        jlfs.setJlFile(file.get("jlFile"));
                        jlfs.setJlInfo(file.get("jlInfo"));
                        jlfs.setJlName(file.get("jlName"));
                        jlfs.setJlTime(file.get("jlTime"));
                        jlfs.setSendTime(SimpData.Simp("yyyy-MM-dd HH:mm", new Date()));
                        jlfs.setGsId(Integer.parseInt(file.get("gs")));
                        jlfs.setUser(file.get("jlUser"));
                        jlfs.save();
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }
                case "/send_zp_infos":
                    file = session.getParms();
                    if (file.size() == 11) {
                        QYZP qyzp = new QYZP();
                        qyzp.setState(1);
                        qyzp.setQymc(file.get("UserName"));
                        qyzp.setSzd(file.get("province"));
                        qyzp.setGsdz(file.get("address"));
                        qyzp.setTel(file.get("tel"));
                        qyzp.setEmail(file.get("email"));
                        qyzp.setGw(file.get("gw"));
                        qyzp.setXz(file.get("xz"));
                        qyzp.setXlyq(file.get("xl"));
                        qyzp.setZyyq(file.get("major"));
                        qyzp.setGzjl(file.get("gzjl"));
                        qyzp.setGwyq(file.get("gwyq"));
                        qyzp.setUser(AppClient.getUser());
                        qyzp.setTime(SimpData.Simp("yyyy-MM-dd HH:mm", new Date()));
                        String[] infos = appClient.getID().split(",");
                        if (infos[0].equals(SimpData.Simp("d", new Date()))) {
                            setNum(qyzp);
                        } else {
                            appClient.setID(SimpData.Simp("d", new Date()) + ",1");
                            setNum(qyzp);
                        }
                        qyzp.save();
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }
                case "/set_zp_infos":
                    file = session.getParms();
                    if (file.size() == 1) {
                        QYZP qyzp = new QYZP();
                        qyzp.setState(2);
                        qyzp.setShtime(SimpData.Simp("yyyy-MM-dd HH:mm", new Date()));
                        qyzp.updateAll("id=?", file.get("id"));
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }
                case "/get_zp_history":
                    List<QYZP> qyzps1 = LitePal.where("state=?", "2").find(QYZP.class);
                    JSONArray jsonArray2 = new JSONArray();
                    for (int i = 0; i < qyzps1.size(); i++) {
                        QYZP qyzp = qyzps1.get(i);
                        JSONObject jsonObject3 = new JSONObject();
                        jsonObject3.put("gsmc", qyzp.getQymc());
                        jsonObject3.put("hylx", qyzp.getHylx());
                        jsonObject3.put("gwmc", qyzp.getGw());
                        jsonObject3.put("major", qyzp.getZyyq());
                        jsonObject3.put("szcs", qyzp.getSzd());
                        jsonObject3.put("xlyq", qyzp.getXlyq());
                        jsonObject3.put("zxfw", qyzp.getXz());
                        jsonObject3.put("email", qyzp.getEmail());
                        jsonObject3.put("tel", qyzp.getTel());
                        jsonObject3.put("time", qyzp.getTime());
                        jsonObject3.put("shTime", qyzp.getShtime());
                        jsonArray2.put(jsonObject3);
                    }
                    JSONObject jsonObject3 = new JSONObject();
                    jsonObject3.put("RESULT", "S");
                    jsonObject3.put("ROWS_DETAIL", jsonArray2);
                    return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, jsonObject3.toString());
                case "/log_in":
                    file = session.getParms();
                    List<Q_YHZC_SQL> q_yhzc_sqls = LitePal.where("yhm=? and mima=?", file.get("UserName"), file.get("password")).find(Q_YHZC_SQL.class);
                    if (q_yhzc_sqls.size() != 0) {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }
                case "/get_notifi_info":
                    List<TZ_SQL> tz_sqls = LitePal.where("is=?", "1").find(TZ_SQL.class);
                    JSONArray jsonArray3 = new JSONArray();
                    for (int i = 0; i < tz_sqls.size(); i++) {
                        TZ_SQL tz_sql = tz_sqls.get(i);
                        JSONObject jsonObject21 = new JSONObject();
                        jsonObject21.put("id", tz_sql.getId());
                        jsonObject21.put("nr", tz_sql.getNeirong());
                        jsonObject21.put("time", tz_sql.getTime());
                        jsonArray3.put(jsonObject21);
                    }
                    JSONObject jsonObject4 = new JSONObject();
                    jsonObject4.put("RESULT", "S");
                    jsonObject4.put("ROWS_DETAIL", jsonArray3);
                    return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, jsonObject4.toString());
                case "/request_notif_info":
                    file = session.getParms();
                    if (file.size() == 2) {
                        TZ_SQL tz_sql = new TZ_SQL();
                        tz_sql.setIs(2);
                        tz_sql.setRequestInfo(file.get("request"));
                        tz_sql.updateAll("id=?", file.get("id"));
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setNum(QYZP qyzp) {
        String[] infos = appClient.getID().split(",");
        int indes = Integer.parseInt(infos[1]);
        String str = String.format("%04d", indes);
        qyzp.setNum(SimpData.Simp("yyyyMMdd", new Date()) + str);
        appClient.setID(infos[0] + "," + (indes + 1));
    }
}
