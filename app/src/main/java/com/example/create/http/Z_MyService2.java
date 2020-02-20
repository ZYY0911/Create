package com.example.create.http;

import android.util.Log;

import com.example.create.AppClient;
import com.example.create.bean.JBXX;
import com.example.create.bean.JLFS;
import com.example.create.bean.JLLB;
import com.example.create.bean.QYZP;
import com.example.create.bean.Q_YHZC_SQL;
import com.example.create.bean.TZ_SQL;
import com.example.create.bean2.GSCP;
import com.example.create.bean2.GYS;
import com.example.create.bean2.GYSP;
import com.example.create.bean2.JYSJ;
import com.example.create.bean3.CK;
import com.example.create.bean3.RK;
import com.example.create.bean3.YZ;
import com.example.create.bean4.YGXX;
import com.example.create.bean5.CJXX;
import com.example.create.bean5.SCX;
import com.example.create.util.SimpData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fi.iki.elonen.NanoHTTPD;

/**
 * Create by 张瀛煜 on 2020-01-16
 */
public class Z_MyService2 extends NanoHTTPD {
    public static final int PORT = 3333;
    private AppClient appClient;

    public Z_MyService2(int port, AppClient appClient) {
        super(3333);
        this.appClient = appClient;
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        Map<String, String> map = new HashMap<>();
        JSONObject bodyJson;
        String body;
        JSONObject yesJson = new JSONObject();
        JSONObject noJson = new JSONObject();
        try {
            yesJson.put("RESULT", "S");
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
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject.toString());
                case "/set_factory_air":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    appClient.setKt(bodyJson.optString("air"));
                    return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                case "/set_factory_light":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    if ("开启".equals(bodyJson.optString("light"))) {
                        appClient.setLight(true);
                    } else if ("关闭".equals(bodyJson.optString("light"))) {
                        appClient.setLight(false);
                    } else {
                        return null;
                    }
                    return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                case "/get_root_info":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    List<JBXX> jbxx = LitePal.where("name=?", String.valueOf(bodyJson.optString("UserName"))).find(JBXX.class);
                    if (jbxx.size() == 0) {
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
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
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject2.toString());
                    }
                case "/update_root_info":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    try {
                        JBXX jbxx1 = new JBXX();
                        jbxx1.setSex(bodyJson.optString("sex"));
                        jbxx1.setProvince(bodyJson.optString("province"));
                        jbxx1.setMajor(bodyJson.optString("major"));
                        jbxx1.setSchool(bodyJson.optString("school"));
                        jbxx1.setXl(bodyJson.optString("xl"));
                        jbxx1.setLove(bodyJson.optString("love"));
                        jbxx1.setGzjl(bodyJson.optString("gzjl"));
                        jbxx1.setJyyx(bodyJson.optString("jyyx"));
                        jbxx1.setYx(bodyJson.optString("email"));
                        jbxx1.setPhoto(bodyJson.optString("photo"));
                        jbxx1.updateAll("name=?", bodyJson.optString("UserName"));
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    }


                case "/get_jl_list":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    List<JLLB> jllbs = LitePal.where("user=?", String.valueOf(bodyJson.optString("UserName"))).find(JLLB.class);
                    if (jllbs.size() == 0) {
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
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
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject2.toString());
                    }
                case "/add_jl":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    try {
                        JLLB jllb = new JLLB();
                        jllb.setJlTime(SimpData.Simp("yyyy-MM-dd HH:mm", new Date()));
                        jllb.setJlFile(bodyJson.optString("file"));
                        jllb.setUser(bodyJson.optString("UserName"));
                        jllb.setJlName(bodyJson.optString("jlName"));
                        jllb.setJlInfo(bodyJson.optString("jlInfo"));
                        jllb.save();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } catch (Exception e) {
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
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
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject2.toString());
                case "/sc_zp_infos":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    if (!"".equals(bodyJson.optString("id"))) {
                        String arr = AppClient.getJlSc();
                        if (arr.equals("")) {
                            arr += bodyJson.optString("id") + "/" + SimpData.Simp("yyyy-MM-dd HH:mm", new Date());
                        } else {
                            arr += "," + bodyJson.optString("id") + "/" + SimpData.Simp("yyyy-MM-dd HH:mm", new Date());
                        }
                        AppClient.setJlSc(arr);
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    }
                case "/send_jl_infos":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);

                    try {
                        JLFS jlfs = new JLFS();
                        jlfs.setJlFile(bodyJson.optString("jlFile"));
                        jlfs.setJlInfo(bodyJson.optString("jlInfo"));
                        jlfs.setJlName(bodyJson.optString("jlName"));
                        jlfs.setJlTime(bodyJson.optString("jlTime"));
                        jlfs.setSendTime(SimpData.Simp("yyyy-MM-dd HH:mm", new Date()));
                        jlfs.setGsId(Integer.parseInt(bodyJson.optString("gs")));
                        jlfs.setUser(bodyJson.optString("jlUser"));
                        jlfs.save();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    }
                case "/send_zp_infos":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    try {
                        QYZP qyzp = new QYZP();
                        qyzp.setState(1);
                        qyzp.setQymc(bodyJson.optString("qymc"));
                        qyzp.setSzd(bodyJson.optString("province"));
                        qyzp.setGsdz(bodyJson.optString("address"));
                        qyzp.setTel(bodyJson.optString("tel"));
                        qyzp.setEmail(bodyJson.optString("email"));
                        qyzp.setGw(bodyJson.optString("gw"));
                        qyzp.setXz(bodyJson.optString("xz"));
                        qyzp.setXlyq(bodyJson.optString("xl"));
                        qyzp.setZyyq(bodyJson.optString("major"));
                        qyzp.setGzjl(bodyJson.optString("gzjl"));
                        qyzp.setGwyq(bodyJson.optString("gwyq"));
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
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    }
                case "/set_zp_infos":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    try {
                        QYZP qyzp = new QYZP();
                        qyzp.setState(2);
                        qyzp.setShtime(SimpData.Simp("yyyy-MM-dd HH:mm", new Date()));
                        qyzp.updateAll("id=?", bodyJson.optString("id"));
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
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
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject3.toString());
                case "/log_in":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    List<Q_YHZC_SQL> q_yhzc_sqls = LitePal.where("yhm=? and mima=?", bodyJson.optString("UserName"), bodyJson.optString("password")).find(Q_YHZC_SQL.class);
                    if (q_yhzc_sqls.size() != 0) {
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    }
                case "/send_notifi_info":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    TZ_SQL sql = new TZ_SQL(1, bodyJson.optString("msg"), SimpData.Simp("yyyy-MM-dd", new Date()));
                    sql.save();
                    return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
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
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject4.toString());
                case "/request_notif_info":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    TZ_SQL tz_sql = new TZ_SQL();
                    tz_sql.setIs(2);
                    tz_sql.setRequestInfo(bodyJson.optString("request"));
                    tz_sql.updateAll("id=?", bodyJson.optString("id"));
                    return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());

                case "/get_gys_info":
                    List<GYS> gys = LitePal.findAll(GYS.class);
                    JSONArray jsonArray4 = new JSONArray();
                    for (int i = 0; i < gys.size(); i++) {
                        GYS gys1 = gys.get(i);
                        JSONObject jsonObject21 = new JSONObject();
                        jsonObject21.put("id", gys1.getId());
                        jsonObject21.put("city", gys1.getGysCity());
                        jsonObject21.put("num", gys1.getGysNum());
                        jsonObject21.put("location", gys1.getGysLocation());
                        jsonObject21.put("low", gys1.getGysLaw());
                        jsonObject21.put("tel", gys1.getGysTel());
                        jsonObject21.put("people", gys1.getGysPeople());
                        jsonObject21.put("range", gys1.getGysRange());
                        jsonObject21.put("photo", gys1.getGysPhoto());
                        jsonArray4.put(jsonObject21);
                    }
                    JSONObject jsonObject5 = new JSONObject();
                    jsonObject5.put("RESULT", "S");
                    jsonObject5.put("ROWS_DETAIL", jsonArray4);
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject5.toString());
                case "/set_gys_info":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    GYS gys1 = new GYS();
                    gys1.setGysCity(bodyJson.optString("city"));
                    gys1.setGysLocation(bodyJson.optString("location"));
                    gys1.setGysLaw(bodyJson.optString("low"));
                    gys1.setGysTel(bodyJson.optString("tel"));
                    gys1.setGysPeople(bodyJson.optString("people"));
                    gys1.setGysRange(bodyJson.optString("range"));
                    gys1.setGysPhoto(bodyJson.optString("photo"));
                    gys1.updateAll("gysnum=?", bodyJson.optString("num"));
                    return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());

                case "/delete_gys_info":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    try {
                        LitePal.deleteAll(GYS.class, "num=?", bodyJson.optString("num"));
                        LitePal.deleteAll(GYSP.class, "gysNum=?", bodyJson.optString("num"));
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    }

                case "/add_gys_sp":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    try {
                        GYSP gysp = new GYSP();
                        gysp.setGysNum(Integer.parseInt(bodyJson.getString("gys_num")));
                        gysp.setYlName(bodyJson.getString("sp_name"));
                        gysp.setYlNum(bodyJson.getString("sp_num"));
                        gysp.setYlPhoto(bodyJson.getString("sp_photo"));
                        gysp.setYlPrice(Integer.parseInt(bodyJson.getString("sp_price")));
                        gysp.save();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    }
                case "/delete_gys_sp":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    LitePal.deleteAll("ylNum=?", bodyJson.optString("yl_num"));
                    return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                case "/update_gys_sp":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    GYSP gysp = new GYSP();
                    gysp.setYlPrice(Integer.parseInt(bodyJson.optString("price")));
                    gysp.setYlName(bodyJson.optString("yl_name"));
                    gysp.updateAll("ylNum=?", bodyJson.optString("yl_num"));
                    return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                case "/get_yw_yl":
                    List<GSCP> gscps = LitePal.findAll(GSCP.class);
                    JSONArray jsonArray5 = new JSONArray();
                    for (int i = 0; i < gscps.size(); i++) {
                        GSCP gscp = gscps.get(i);
                        JSONObject jsonObject21 = new JSONObject();
                        jsonObject21.put("id", gscp.getId());
                        jsonObject21.put("name", gscp.getName());
                        jsonObject21.put("photo", gscp.getPhoto());
                        jsonObject21.put("xh", gscp.getXh());
                        jsonArray5.put(jsonObject21);
                    }
                    JSONObject jsonObject6 = new JSONObject();
                    jsonObject6.put("RESULT", "S");
                    jsonObject6.put("ROWS_DETAIL", jsonArray5);
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject6.toString());
                case "/get_yl_jl":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    List<JYSJ> jysjs;
                    if ("".equals(bodyJson.getString("name"))) {
                        jysjs = LitePal.findAll(JYSJ.class);
                    } else {
                        jysjs = LitePal.where("name=?", bodyJson.getString("name")).find(JYSJ.class);
                    }
                    JSONArray jsonArray6 = new JSONArray();
                    for (int i = 0; i < jysjs.size(); i++) {
                        JYSJ jysj = jysjs.get(i);
                        JSONObject jsonObject7 = new JSONObject();
                        jsonObject7.put("id", jysj.getId());
                        jsonObject7.put("contacts", jysj.getContacts());
                        jsonObject7.put("contactId", jysj.getCountId());
                        jsonObject7.put("fp", jysj.getFp());
                        jsonObject7.put("name", jysj.getName());
                        jsonObject7.put("shop", jysj.getShop());
                        jsonObject7.put("time", jysj.getTime());
                        jsonObject7.put("num", jysj.getNum());
                        jsonObject7.put("price", jysj.getPrice());
                        jsonObject7.put("money", jysj.getMoney());
                        jsonArray6.put(jsonObject7);
                    }
                    JSONObject jsonObject8 = new JSONObject();
                    jsonObject8.put("RESULT", "S");
                    jsonObject8.put("ROWS_DETAIL", jsonArray6);
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject8.toString());
                case "/get_rk_info":
                    List<RK> rks = LitePal.findAll(RK.class);
                    JSONArray jsonArray7 = new JSONArray();
                    for (int i = 0; i < rks.size(); i++) {
                        RK rk = rks.get(i);
                        JSONObject jsonObject7 = new JSONObject();
                        jsonObject7.put("id", rk.getId());
                        jsonObject7.put("ylmc", rk.getYlmc());
                        jsonObject7.put("xh", rk.getXh());
                        jsonObject7.put("gys", rk.getGys());
                        jsonObject7.put("num", rk.getNum());
                        jsonObject7.put("price", rk.getPrice());
                        jsonObject7.put("location", rk.getLocation());
                        jsonObject7.put("time", rk.getTime());
                        jsonObject7.put("rkr", rk.getKrname());
                        jsonObject7.put("countId", rk.getCountId());
                        jsonObject7.put("shop", rk.getShop());
                        jsonObject7.put("contacts", rk.getContacts());
                        jsonObject7.put("fp", rk.getFp());
                        jsonArray7.put(jsonObject7);
                    }
                    JSONObject jsonObject9 = new JSONObject();
                    jsonObject9.put("RESULT", "S");
                    jsonObject9.put("ROWS_DETAIL", jsonArray7);
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject9.toString());
                case "/add_rk_info":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);

                    try {
                        RK rk = new RK();
                        rk.setYlmc(bodyJson.optString("ylmc"));
                        rk.setXh(bodyJson.optString("xh"));
                        rk.setGys(bodyJson.optString("gys"));
                        rk.setNum(Integer.parseInt(bodyJson.optString("num")));
                        rk.setPrice(Integer.parseInt(bodyJson.optString("price")));
                        rk.setLocation(bodyJson.optString("location"));
                        rk.setTime(SimpData.Simp("yyyy-MM-dd", new Date()));
                        rk.setKrname(bodyJson.optString("rkr"));
                        rk.setCountId(bodyJson.optString("countId"));
                        rk.setShop(bodyJson.optString("shop"));
                        rk.setContacts(bodyJson.optString("contacts"));
                        rk.setFp(bodyJson.optString("fp"));
                        rk.save();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    }

                case "/add_ck_info":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    try {
                        CK ck = new CK();
                        ck.setYlmc(bodyJson.optString("lymc"));
                        ck.setXh(bodyJson.optString("xh"));
                        ck.setGys(bodyJson.optString("gys"));
                        ck.setCkr(bodyJson.optString("ckr"));//出库人
                        ck.setScx(bodyJson.optString("scx"));//生产线
                        ck.setTime(SimpData.Simp("yyyy-MM-dd", new Date()));
                        ck.setJsr(bodyJson.optString("jsr"));//接受人
                        ck.setNum(Integer.parseInt(bodyJson.optString("num")));
                        ck.setKcwz(bodyJson.optString("kcwz"));
                        ck.setPrice(Integer.parseInt(bodyJson.optString("price")));
                        ck.save();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    }

                case "/get_ck_info":
                    List<CK> cks = LitePal.findAll(CK.class);
                    JSONArray jsonArray8 = new JSONArray();
                    for (int i = 0; i < cks.size(); i++) {
                        CK rk = cks.get(i);
                        JSONObject jsonObject7 = new JSONObject();
                        jsonObject7.put("id", rk.getId());
                        jsonObject7.put("ylmc", rk.getYlmc());
                        jsonObject7.put("xh", rk.getXh());
                        jsonObject7.put("gys", rk.getGys());
                        jsonObject7.put("num", rk.getNum());
                        jsonObject7.put("price", rk.getPrice());
                        jsonObject7.put("scx", rk.getScx());
                        jsonObject7.put("time", rk.getTime());
                        jsonObject7.put("ckr", rk.getCkr());
                        jsonObject7.put("jsr", rk.getJsr());
                        jsonObject7.put("kcwz", rk.getKcwz());
                        jsonArray8.put(jsonObject7);
                    }
                    JSONObject jsonObject10 = new JSONObject();
                    jsonObject10.put("RESULT", "S");
                    jsonObject10.put("ROWS_DETAIL", jsonArray8);
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject10.toString());
                case "/get_yz":
                    YZ yz = LitePal.findAll(YZ.class).get(0);
                    JSONObject jsonObject7 = new JSONObject();
                    jsonObject7.put("RESULT", "S");
                    jsonObject7.put("apple", yz.getApple());
                    jsonObject7.put("banana", yz.getBanban());
                    jsonObject7.put("cherry", yz.getCherry());
                    jsonObject7.put("grape", yz.getGrape());
                    jsonObject7.put("mango", yz.getMango());
                    jsonObject7.put("orange", yz.getOrange());
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject7.toString());
                case "/set_yz":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    try {
                        YZ yz1 = new YZ();
                        yz1.setApple(Integer.parseInt(bodyJson.optString("apple")));
                        yz1.setGrape(Integer.parseInt(bodyJson.optString("grape")));
                        yz1.setMango(Integer.parseInt(bodyJson.optString("mango")));
                        yz1.setBanban(Integer.parseInt(bodyJson.optString("banana")));
                        yz1.setCherry(Integer.parseInt(bodyJson.optString("cherry")));
                        yz1.setOrange(Integer.parseInt(bodyJson.optString("orange")));
                        yz1.updateAll("id=?", "1");
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    }

                case "/add_gyxx_info":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    try {
                        YGXX ygxx = new YGXX();
                        ygxx.setName(bodyJson.optString("name"));
                        ygxx.setSex(bodyJson.optString("sex"));
                        ygxx.setBirth(bodyJson.optString("birth"));
                        ygxx.setTel(bodyJson.optString("tel"));
                        ygxx.setScx(bodyJson.optString("scx"));
                        ygxx.setZw(bodyJson.optString("zw"));
                        ygxx.setEmail(bodyJson.optString("email"));
                        ygxx.setAddress(bodyJson.optString("address"));
                        ygxx.save();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    }

                case "/get_ygxx_info":
                    List<YGXX> ygxxes = LitePal.findAll(YGXX.class);
                    JSONArray jsonArray9 = new JSONArray();
                    for (int i = 0; i < ygxxes.size(); i++) {
                        YGXX ygxx = ygxxes.get(i);
                        JSONObject jsonObject11 = new JSONObject();
                        jsonObject11.put("id", ygxx.getId());
                        jsonObject11.put("name", ygxx.getName());
                        jsonObject11.put("sex", ygxx.getSex());
                        jsonObject11.put("birth", ygxx.getBirth());
                        jsonObject11.put("tel", ygxx.getTel());
                        jsonObject11.put("scx", ygxx.getScx());
                        jsonObject11.put("zw", ygxx.getZw());
                        jsonObject11.put("email", ygxx.getEmail());
                        jsonObject11.put("address", ygxx.getAddress());
                        jsonArray9.put(jsonObject11);
                    }
                    JSONObject jsonObject11 = new JSONObject();
                    jsonObject11.put("RESULT", "S");
                    jsonObject11.put("ROWS_DETAIL", jsonArray9);
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject11.toString());
                case "/get_scx_info":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    JSONArray jsonArray10 = new JSONArray();
                    List<SCX> scxes;
                    scxes = LitePal.where("scx=?", bodyJson.optString("scx")).find(SCX.class);
                    for (int i = 0; i < scxes.size(); i++) {
                        SCX scx = scxes.get(i);
                        JSONObject jsonObject12 = new JSONObject();
                        jsonObject12.put("id", i + 1);
                        jsonObject12.put("cj", scx.getCj());
                        jsonObject12.put("hj", scx.getHj());
                        jsonObject12.put("scx", scx.getScx());
                        jsonObject12.put("state", scx.getState());
                        jsonObject12.put("ts", scx.getTs());
                        jsonArray10.put(jsonObject12);
                    }
                    JSONObject jsonObject12 = new JSONObject();
                    jsonObject12.put("RESULT", "S");
                    jsonObject12.put("ROWS_DETAIL", jsonArray10);
                    return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject12.toString());
                case "/add_scx_info":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    try {
                        SCX scx = new SCX();
                        scx.setCj(Integer.parseInt(bodyJson.optString("cj")));
                        scx.setTs(bodyJson.optString("ts"));
                        scx.setState(bodyJson.optString("state"));
                        scx.setHj(bodyJson.optString("hj"));
                        scx.setScx(bodyJson.optString("scx"));
                        scx.save();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    }

                case "/get_cjxx_info":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    try {
                        JSONObject jsonObject13 = new JSONObject();
                        CJXX cjxx = LitePal.where("num", bodyJson.getString("num")).find(CJXX.class).get(0);
                        jsonObject13.put("num", cjxx.getNum());
                        jsonObject13.put("dl", cjxx.getDl());
                        jsonObject13.put("gz", cjxx.getGz());
                        jsonObject13.put("qcrl", cjxx.getQcrl());
                        jsonObject13.put("wd", cjxx.getWd());
                        jsonObject13.put("ylrl", cjxx.getYlrl());
                        return newFixedLengthResponse(Response.Status.OK, "application/json", jsonObject13.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    }
                case "/set_gc_ligth":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    try {
                        int gc = Integer.parseInt(bodyJson.getString("gc"));
                        int index = Integer.parseInt(bodyJson.getString("light"));
                        if (index == 0) {
                            switch (gc) {
                                case 1:
                                    appClient.setLight_1(false);
                                    break;
                                case 2:
                                    appClient.setLight_2(false);
                                    break;
                                case 3:
                                    appClient.setLight_3(false);
                                    break;
                                case 4:
                                    appClient.setLight_4(false);
                                    break;
                            }
                        } else {
                            switch (gc) {
                                case 1:
                                    appClient.setLight_1(true);
                                    break;
                                case 2:
                                    appClient.setLight_2(true);
                                    break;
                                case 3:
                                    appClient.setLight_3(true);
                                    break;
                                case 4:
                                    appClient.setLight_4(true);
                                    break;
                            }
                        }
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    }
                case "/set_gc_air":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    try {
                        int gc = Integer.parseInt(bodyJson.getString("gc"));
                        int index = Integer.parseInt(bodyJson.getString("air"));
                        if (index == 0) {
                            switch (gc) {
                                case 1:
                                    appClient.setAir_1(false);
                                    break;
                                case 2:
                                    appClient.setAir_2(false);
                                    break;
                                case 3:
                                    appClient.setAir_3(false);
                                    break;
                                case 4:
                                    appClient.setAir_4(false);
                                    break;
                            }
                        } else {
                            switch (gc) {
                                case 1:
                                    appClient.setAir_1(true);
                                    break;
                                case 2:
                                    appClient.setAir_2(true);
                                    break;
                                case 3:
                                    appClient.setAir_3(true);
                                    break;
                                case 4:
                                    appClient.setAir_4(true);
                                    break;
                            }
                        }
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    }
                case "/set_gc_wd":
                    session.parseBody(map);
                    body = map.get("postData");
                    bodyJson = new JSONObject(body);
                    try {
                        int gc = Integer.parseInt(bodyJson.getString("gc"));
                        int index = Integer.parseInt(bodyJson.getString("wd"));
                        switch (gc) {
                            case 1:
                                appClient.setWd_1(index);
                                break;
                            case 2:appClient.setWd_2(index);
                                break;
                            case 3:appClient.setWd_3(index);
                                break;
                            case 4:appClient.setWd_4(index);
                                break;
                        }
                        return newFixedLengthResponse(Response.Status.OK, "application/json", yesJson.toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
                    }
                case "/get_gw":
                    
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return newFixedLengthResponse(Response.Status.OK, "application/json", noJson.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResponseException e) {
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
