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
import com.google.gson.JsonArray;

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
                case "/send_notifi_info":
                    file = session.getParms();
                    if (file.size() == 1) {
                        TZ_SQL sql = new TZ_SQL(1, file.get("msg"), SimpData.Simp("yyyy-MM-dd", new Date()));
                        sql.save();
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
                    return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, jsonObject5.toString());
                case "/set_gys_info":
                    file = session.getParms();
                    try {
                        if (file.size() == 8) {
                            GYS gys1 = new GYS();
                            gys1.setGysCity(file.get("city"));
                            gys1.setGysLocation(file.get("location"));
                            gys1.setGysLaw(file.get("low"));
                            gys1.setGysTel(file.get("tel"));
                            gys1.setGysPeople(file.get("people"));
                            gys1.setGysRange(file.get("range"));
                            gys1.setGysPhoto(file.get("photo"));
                            gys1.updateAll("gysnum=?", file.get("num"));
                            return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                        } else {
                            return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }
                case "/delete_gys_info":
                    file = session.getParms();
                    if (file.size() == 1) {
                        LitePal.deleteAll(GYS.class, "num=?", file.get("num"));
                        LitePal.deleteAll(GYSP.class, "gysNum=?", file.get("num"));
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }
                case "/add_gys_sp":
                    file = session.getParms();
                    if (file.size() == 5) {
                        GYSP gysp = new GYSP();
                        gysp.setGysNum(Integer.parseInt(file.get("gys_num")));
                        gysp.setYlName(file.get("sp_name"));
                        gysp.setYlNum(file.get("sp_num"));
                        gysp.setYlPhoto(file.get("sp_photo"));
                        gysp.setYlPrice(Integer.parseInt(file.get("sp_price")));
                        gysp.save();
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }
                case "/delete_gys_sp":
                    file = session.getParms();
                    if (file.size() == 1) {
                        LitePal.deleteAll("ylNum=?", file.get("yl_num"));
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }
                case "/update_gys_sp":
                    file = session.getParms();
                    if (file.size() == 3) {
                        GYSP gysp = new GYSP();
                        gysp.setYlPrice(Integer.parseInt(file.get("price")));
                        gysp.setYlName(file.get("yl_name"));
                        gysp.updateAll("ylNum=?", file.get("yl_num"));
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }
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
                    return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, jsonObject6.toString());
                case "/get_yl_jl":
                    file = session.getParms();
                    List<JYSJ> jysjs;
                    if (file.size() == 1) {
                        jysjs = LitePal.where("name=?", file.get("name")).find(JYSJ.class);
                    } else {
                        jysjs = LitePal.findAll(JYSJ.class);
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
                    return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, jsonObject8.toString());
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
                    return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, jsonObject9.toString());
                case "/add_rk_info":
                    file = session.getParms();
                    if (file.size() == 12) {
                        RK rk = new RK();
                        rk.setYlmc(file.get("ylmc"));
                        rk.setXh(file.get("xh"));
                        rk.setGys(file.get("gys"));
                        rk.setNum(Integer.parseInt(file.get("num")));
                        rk.setPrice(Integer.parseInt(file.get("price")));
                        rk.setLocation(file.get("location"));
                        rk.setTime(SimpData.Simp("yyyy-MM-dd", new Date()));
                        rk.setKrname(file.get("rkr"));
                        rk.setCountId(file.get("countId"));
                        rk.setShop(file.get("shop"));
                        rk.setContacts(file.get("contacts"));
                        rk.setFp(file.get("fp"));
                        rk.save();
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }
                case "/add_ck_info":
                    file = session.getParms();
                    if (file.size() == 10) {
                        CK ck = new CK();
                        ck.setYlmc(file.get("lymc"));
                        ck.setXh(file.get("xh"));
                        ck.setGys(file.get("gys"));
                        ck.setCkr(file.get("ckr"));//出库人
                        ck.setScx(file.get("scx"));//生产线
                        ck.setTime(SimpData.Simp("yyyy-MM-dd", new Date()));
                        ck.setJsr(file.get("jsr"));//接受人
                        ck.setNum(Integer.parseInt(file.get("num")));
                        ck.setKcwz(file.get("kcwz"));
                        ck.setPrice(Integer.parseInt(file.get("price")));
                        ck.save();
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
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
                    return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, jsonObject10.toString());
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
                    return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, jsonObject7.toString());
                case "/set_yz":
                    file = session.getParms();
                    if (file.size() == 6) {
                        YZ yz1 = new YZ();
                        yz1.setApple(Integer.parseInt(file.get("apple")));
                        yz1.setBanban(Integer.parseInt(file.get("banana")));
                        yz1.setCherry(Integer.parseInt(file.get("cherry")));
                        yz1.setGrape(Integer.parseInt(file.get("grape")));
                        yz1.setMango(Integer.parseInt(file.get("mango")));
                        yz1.setOrange(Integer.parseInt(file.get("orange")));
                        yz1.updateAll("id=?", "1");
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }
                case "/add_gyxx_info":
                    file = session.getParms();
                    if (file.size() == 8) {
                        YGXX ygxx = new YGXX();
                        ygxx.setName(file.get("name"));
                        ygxx.setSex(file.get("sex"));
                        ygxx.setBirth(file.get("birth"));
                        ygxx.setTel(file.get("tel"));
                        ygxx.setScx(file.get("scx"));
                        ygxx.setZw(file.get("zw"));
                        ygxx.setEmail(file.get("email"));
                        ygxx.setAddress(file.get("address"));
                        ygxx.save();
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
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
                    return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, jsonObject11.toString());
                case "/get_scx_info":
                    file = session.getParms();
                    JSONArray jsonArray10 = new JSONArray();
                    List<SCX> scxes;
                    scxes = LitePal.where("scx=?", file.get("scx")).find(SCX.class);
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
                    return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, jsonObject12.toString());
                case "/add_scx_info":
                    file = session.getParms();
                    if (file.size() == 5) {
                        SCX scx = new SCX();
                        scx.setCj(Integer.parseInt(file.get("cj")));
                        scx.setTs(file.get("ts"));
                        scx.setState(file.get("state"));
                        scx.setHj(file.get("hj"));
                        scx.setScx(file.get("scx"));
                        scx.save();
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, yseJson.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }
                case "/get_cjxx_info":
                    file = session.getParms();
                    if (file.size() == 1) {
                        JSONObject jsonObject13 = new JSONObject();
                        CJXX cjxx = LitePal.where("num", file.get("num")).find(CJXX.class).get(0);
                        jsonObject13.put("num", cjxx.getNum());
                        jsonObject13.put("dl", cjxx.getDl());
                        jsonObject13.put("gz", cjxx.getGz());
                        jsonObject13.put("qcrl", cjxx.getQcrl());
                        jsonObject13.put("wd", cjxx.getWd());
                        jsonObject13.put("ylrl", cjxx.getYlrl());
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, jsonObject13.toString());
                    } else {
                        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
                    }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, noJson.toString());
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
