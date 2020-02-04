package com.example.create.fragment3;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.create.R;
import com.example.create.adapter3.YZSZAdapter;
import com.example.create.bean2.GSCP;
import com.example.create.bean3.KCL;
import com.example.create.bean3.RK;
import com.example.create.bean3.YZ;
import com.example.create.util.Flash;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-02-02
 */
@SuppressLint("ValidFragment")
public class Z_YZSZFragment extends Fragment {
    @BindView(R.id.flash)
    Flash flash;
    Unbinder unbinder;
    private Context context;
    private List<KCL> kcls;

    public Z_YZSZFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yzsz_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("i" + i);
        }
        flash.setReFlash(new Flash.ReFlash() {
            @Override
            public void reFlash() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "已刷新：）", Toast.LENGTH_SHORT).show();
                        initData();
                        flash.Conpty();
                    }
                }, 1500);
            }
        });
        initData();
    }

    private void initData() {
        kcls = new ArrayList<>();
        List<GSCP> gscps = LitePal.findAll(GSCP.class);
        for (int i = 0; i < gscps.size(); i++) {
            GSCP gscp = gscps.get(i);
            List<RK> rks = LitePal.where("ylmc=?", gscp.getName()).find(RK.class);
            int money = 0;
            for (int j = 0; j < rks.size(); j++) {
                RK rk = rks.get(j);
                if (rk.getNum() > 0) {
                    money += rk.getNum();
                }
            }
            kcls.add(new KCL(gscp.getName(), gscp.getXh(), money + ""));
        }
        flash.setAdapter(new YZSZAdapter(getContext(), R.layout.yzsz_item, kcls, LitePal.findAll(YZ.class).get(0)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
