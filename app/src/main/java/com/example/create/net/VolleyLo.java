package com.example.create.net;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Create by 张瀛煜 on 2020-01-11
 */
public interface VolleyLo {
    void onResponse(JSONObject jsonObject);

    void onErrorResponse(VolleyError volleyError);
}
