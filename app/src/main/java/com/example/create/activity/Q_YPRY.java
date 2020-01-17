package com.example.create.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.create.R;
import com.example.create.adapter.Q_LV_adapter;
import com.example.create.bean.Q_YPRY_bean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Q_YPRY extends AppCompatActivity {
    @BindView(R.id.tv_zrs)
    TextView Zrstv;
    @BindView(R.id.iv_cd)
    ImageView Cdiv;
    @BindView(R.id.lv_1)
    ListView lv1;
    @BindView(R.id.bu_fx)
    Button Fxbu;
    @BindView(R.id.bt_fs)
    Button Fsbt;
    private List<Q_YPRY_bean> list = new ArrayList<>();
    private Q_LV_adapter adapter;
    private List<Boolean> mCheckedList = new ArrayList<>();
    private List<Integer> integers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q__ypry);
        ButterKnife.bind(this);
        list.add(new Q_YPRY_bean(R.drawable.sex, "张三", "2000-1-1", "大学", "无"));
        list.add(new Q_YPRY_bean(R.drawable.sex, "张三", "2000-1-1", "大学", "无"));
        int x = list.size();
        Zrstv.setText("应聘总人数:" + x + "人");
        for (int i = 0; i < list.size(); i++) {
            mCheckedList.add(false);
            Log.i("cccccccccccc", "onCreate: " + mCheckedList.size());
        }
        setAdapter();
    }

    private void setAdapter() {
        adapter = new Q_LV_adapter(this, list);
        lv1.setAdapter(adapter);
        adapter.setClick(new Q_LV_adapter.Click() {
            @Override
            public void Click(int position, boolean is) {
                if (is) {

                } else {

                }
            }
        });
    }

    @OnClick({R.id.iv_cd, R.id.bu_fx, R.id.bt_fs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cd:
                //TODO: add click handling
                if (adapter.isIs()) {
                    adapter.setIs(false);
                    list.clear();
                } else {
                    adapter.setIs(true);
                }
                break;
            case R.id.bu_fx:
                //TODO: add click handling
                break;
            case R.id.bt_fs:
                //TODO: add click handling
                break;
        }
    }
}
