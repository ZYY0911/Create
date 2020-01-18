package com.example.create.activity;

import android.content.Intent;
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
import com.example.create.bean.JBXX;
import com.example.create.bean.JLFS;
import com.example.create.bean.Q_YPRY_bean;
import com.example.create.dialog.JL_dailog;
import com.example.create.dialog.YPXX_Dialog;

import org.litepal.LitePal;

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
    private YPXX_Dialog dialog;
    private JL_dailog dailog1;
    private List<JLFS> jlfs;
    private List<JBXX> jbxxes, jbxxes1;
    private int gsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q__ypry);
        ButterKnife.bind(this);
        gsId = getIntent().getIntExtra("name", 1);
        jlfs = new ArrayList<>();
        jlfs = LitePal.findAll(JLFS.class);
        jbxxes = LitePal.findAll(JBXX.class);
        jbxxes1 = new ArrayList<>();
        for (int i = 0; i < jlfs.size(); i++) {
            JLFS jlf = jlfs.get(i);
            if (jlf.getGsId() == gsId) {
                Log.i("aaaa", "onCreate: "+jlf.getGsId()+"===="+gsId);
                for (int j = 0; j < jbxxes.size(); j++) {
                    JBXX jbxx = jbxxes.get(j);
                    if (jlf.getUser().equals(jbxx.getName())) {
                        int image;
                        if ("男".equals(jbxx.getSex())) {
                            image = R.drawable.sex;
                        } else {
                            image = R.drawable.touxiang_1;
                        }
                        list.add(new Q_YPRY_bean(image, jbxx.getName(), jbxx.getBirth(), jbxx.getXl(), jbxx.getGzjl()));
                        jbxxes1.add(jbxx);
                    }
                }
            }
        }
        Zrstv.setText("应聘总人数:" + list.size() + "人");
        for (int i = 0; i < list.size(); i++) {
            mCheckedList.add(false);
            Log.i("cccccccccccc", "onCreate: " + mCheckedList.size());
        }
        setAdapter();
    }

    private void setAdapter() {
        adapter = new Q_LV_adapter(this, list);
        lv1.setAdapter(adapter);
        adapter.setOnClick(new Q_LV_adapter.onClick() {
            @Override
            public void onClick(int position) {
                if (position == 0) {
                    dailog1 = new JL_dailog(jbxxes1.get(position));
                    dailog1.show(getSupportFragmentManager(), "");
                }
            }
        });


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
                startActivity(new Intent(this, Z_TBXXActivity.class));

                break;
            case R.id.bu_fx:
                //TODO: add click handling
                if (adapter.isIs()) {
                    adapter.setIs(false);
                } else {
                    adapter.setIs(true);
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.bt_fs:
                //TODO: add click handling
                setDialog();
                break;
        }
    }

    private void setDialog() {
        dialog = new YPXX_Dialog();
        dialog.show(getSupportFragmentManager(), "");
        dialog.setCancelable(true);
    }
}
