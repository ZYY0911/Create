package com.example.create.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.create.AppClient;
import com.example.create.R;
import com.example.create.activity2.Z_GYSActivity;
import com.example.create.activity3.Z_YLGLActivity;
import com.example.create.activity4.Z_YGXXActivity;
import com.example.create.bean.Q_YHZC_SQL;
import com.example.create.http.Z_MyService;
import com.example.create.net.VolleyLo;
import com.example.create.net.VolleyTo;
import com.example.create.util.SimpData;

import org.json.JSONArray;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.io.IOException;
import java.util.Date;


/**
 * Create by 张瀛煜 on 2020-01-15
 */
public class Z_ZYActivity extends AppCompatActivity {


    private LinearLayout bgImage;
    private TextView outWd;
    private TextView inWd;
    private TextView carAir;
    private TextView butteryIn;
    private TextView butteryOut;
    private TextView nowTime;
    private TextView nowDay;
    private TextView farmLight;
    private LinearLayout layoutGys;
    private LinearLayout layoutRcsc;
    private LinearLayout layoutYlkc;
    private LinearLayout layoutYgxx;
    private LinearLayout layoutWxcj;
    private LinearLayout layoutClkc;
    private TextView timg1;
    private TextView timg2;
    private TextView timg3;
    private TextView timg4;
    private TextView timg5;
    private boolean isLoop = true;
    private AppClient appClient;
    private Z_MyService z_myService;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0) {
                nowTime.setText(SimpData.Simp("HH:mm", new Date()));
            }
            return false;
        }
    });
    private TextView userId;
    private TextView userName;
    private TextView userMoney;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zy_layout);
        initView();
        initClick();
        initData();
        initVolley();
        z_myService = new Z_MyService(3333, appClient);
        try {
            z_myService.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_factory_info")
                .setLoop(true)
                .setTime(5000)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                        carAir.setText(jsonObject1.optString("kt"));
                        outWd.setText(jsonObject1.optString("intWd") + "˚C");
                        inWd.setText(jsonObject1.optString("outWd") + "˚C");
                        butteryIn.setText(jsonObject1.optString("butteryIn") + "kw/h");
                        butteryOut.setText(jsonObject1.optString("butteryOut") + "kw/h");
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (z_myService != null) {
            z_myService.stop();
        }
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (isLoop);
            }
        }).start();
        userName.setText(AppClient.getName());
        Q_YHZC_SQL q_yhzc_sql = LitePal.where("yhm=?",AppClient.getName()).find(Q_YHZC_SQL.class).get(0);
        userId.setText(q_yhzc_sql.getId()+"");
        userMoney.setText(q_yhzc_sql.getJine()+"元");

    }

    private void initClick() {
        ImageBg(1, timg1);
        ImageBg(2, timg2);
        ImageBg(3, timg3);
        ImageBg(4, timg4);
        ImageBg(5, timg5);
        layoutRcsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Z_ZYActivity.this, Z_RCSCActivity.class));
            }
        });
        layoutGys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Z_ZYActivity.this, Z_GYSActivity.class));
            }
        });
        layoutYlkc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Z_ZYActivity.this, Z_YLGLActivity.class));
            }
        });
        layoutYgxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Z_ZYActivity.this, Z_YGXXActivity.class));
            }
        });

    }

    private void ImageBg(final int index, TextView textView) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (index) {
                    case 1:
                        bgImage.setBackgroundResource(R.drawable.timg4);
                        break;
                    case 2:
                        bgImage.setBackgroundResource(R.drawable.timg8);
                        break;
                    case 3:
                        bgImage.setBackgroundResource(R.drawable.timg6);
                        break;
                    case 4:
                        bgImage.setBackgroundResource(R.drawable.timg9);
                        break;
                    case 5:
                        bgImage.setBackgroundResource(R.drawable.timg2);
                        break;
                }
            }
        });
    }

    private void initView() {
        appClient = (AppClient) getApplication();
        bgImage = findViewById(R.id.bg_image);
        outWd = findViewById(R.id.out_wd);
        inWd = findViewById(R.id.in_wd);
        carAir = findViewById(R.id.car_air);
        butteryIn = findViewById(R.id.buttery_in);
        butteryOut = findViewById(R.id.buttery_out);
        nowTime = findViewById(R.id.now_time);
        nowDay = findViewById(R.id.now_day);
        farmLight = findViewById(R.id.farm_light);
        layoutGys = findViewById(R.id.layout_gys);
        layoutRcsc = findViewById(R.id.layout_rcsc);
        layoutYlkc = findViewById(R.id.layout_ylkc);
        layoutYgxx = findViewById(R.id.layout_ygxx);
        layoutWxcj = findViewById(R.id.layout_wxcj);
        layoutClkc = findViewById(R.id.layout_clkc);
        timg1 = findViewById(R.id.timg1);
        timg2 = findViewById(R.id.timg2);
        timg3 = findViewById(R.id.timg3);
        timg4 = findViewById(R.id.timg4);
        timg5 = findViewById(R.id.timg5);
        userId = findViewById(R.id.userId);
        userName = findViewById(R.id.user_name);
        userMoney = findViewById(R.id.user_money);
    }
}
