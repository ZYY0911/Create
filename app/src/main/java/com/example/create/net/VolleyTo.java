package com.example.create.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.create.AppClient;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Create by 张瀛煜 on 2020-01-11
 */
public class VolleyTo extends Thread {
    private String Url = "http://192.168.2.157:3333/";
    private boolean isLoop;
    private int time;
    private JSONObject jsonObject = new JSONObject();
    private VolleyLo volleyLo;
    private ProgressDialog dialog;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            return false;
        }
    });

    public VolleyTo setVolleyLo(VolleyLo volleyLo) {
        this.volleyLo = volleyLo;
        return this;
    }

    public VolleyTo setUrl(String url) {
        Url += url;
        return this;
    }

    public VolleyTo setDialog(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setTitle("提示");
        dialog.setMessage("网络请求中");
        dialog.show();
        return this;
    }

    public VolleyTo setLoop(boolean loop) {
        isLoop = loop;
        return this;
    }

    public VolleyTo setTime(int time) {
        this.time = time;
        return this;
    }

    public VolleyTo setJsonObject(String k, Object v) {
        try {
            jsonObject.put(k, v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public void run() {
        super.run();
        do {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET
                    ,Url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    volleyLo.onResponse(jsonObject);
                    handler.sendEmptyMessageDelayed(0, 1500);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyLo.onErrorResponse(volleyError);
                    handler.sendEmptyMessageDelayed(0, 1500);
                }
            });
            AppClient.add(jsonObjectRequest);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (isLoop);
    }
}
