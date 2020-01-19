package com.example.create.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.adapter.YPRYAdapter;
import com.example.create.bean.JLFS;
import com.example.create.bean.QYZP;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-18
 */
public class Z_YPRYLBActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    private List<QYZP> qyzps;
    private List<JLFS> jlfs;
    private List<Integer> integers;
    private GridLayoutManager gridLayoutManager;
    private YPRYAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yprylb_layout);
        ButterKnife.bind(this);
        initView();
        initData();
        title.setText("人才市场--应聘人员");
    }

    private void initData() {
        qyzps = LitePal.where("state=?", "2").find(QYZP.class);
        jlfs = LitePal.findAll(JLFS.class);
        integers = new ArrayList<>();
        Collections.sort(qyzps, new Comparator<QYZP>() {
            @Override
            public int compare(QYZP o1, QYZP o2) {
                return o2.getTime().compareTo(o1.getTime());
            }
        });
        for (int i = 0; i < qyzps.size(); i++) {
            int infes = 0;
            QYZP qyzp = qyzps.get(i);
            for (int j = 0; j < jlfs.size(); j++) {
                JLFS jlf = jlfs.get(j);
                if (qyzp.getId() == jlf.getGsId()) {
                    infes++;
                }
            }
            integers.add(infes);
        }
        adapter = new YPRYAdapter(qyzps, integers);
        recycleView.setLayoutManager(gridLayoutManager);
        recycleView.setAdapter(adapter);
    }

    private void initView() {
        change = findViewById(R.id.change);
        title = findViewById(R.id.title);
        recycleView = findViewById(R.id.recycle_view);
        gridLayoutManager = new GridLayoutManager(this, 1);
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
