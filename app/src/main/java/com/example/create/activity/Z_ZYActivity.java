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

import com.example.create.R;
import com.example.create.util.SimpData;

import java.util.Date;
import java.util.Random;


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
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what==0){
                Random random = new Random();
                nowTime.setText(SimpData.Simp("HH:mm",new Date()));
                int wd = random.nextInt(40);
                int dl = random.nextInt(150);
                int kt = random.nextInt(2);
                switch (kt){
                    case 0:
                        carAir.setText("热风");
                        break;
                    case 1:
                        carAir.setText("冷风");
                        break;
                }
                outWd.setText(wd+"˚C");
                inWd.setText((wd-5)+"˚C");
                butteryIn.setText(dl+"kw/h");
                butteryOut.setText((dl-44)+"kw/h");

            }
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zy_layout);
        initView();
        initClick();
        initData();
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
                }while (isLoop);
            }
        }).start();

    }

    private void initClick() {
        ImageBg(1,timg1);
        ImageBg(2,timg2);
        ImageBg(3,timg3);
        ImageBg(4,timg4);
        ImageBg(5,timg5);
        layoutRcsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Z_ZYActivity.this,Z_RCSCActivity.class));
            }
        });

    }

    private void ImageBg(final int index, TextView textView){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (index){
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
    }
}
