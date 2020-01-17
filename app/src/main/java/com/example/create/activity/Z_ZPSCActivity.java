package com.example.create.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.create.AppClient;
import com.example.create.R;
import com.example.create.adapter.ZPXXAdapter;
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
 * Create by 张瀛煜 on 2020-01-17
 */
public class Z_ZPSCActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    private List<QYZP> qyzps, qyzps1;
    private GridLayoutManager gridLayoutManager;
    private ZPXXAdapter adapter;
    private List<String> list, list1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zpsc_layout);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        qyzps = LitePal.findAll(QYZP.class);
        qyzps1 = new ArrayList<>();
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        Collections.sort(qyzps, new Comparator<QYZP>() {
            @Override
            public int compare(QYZP o1, QYZP o2) {
                return o2.getTime().compareTo(o1.getTime());
            }
        });
        if (!"".equals(AppClient.getJlSc())) {
            String arr[] = AppClient.getJlSc().split(",");
            for (int i = 0; i < arr.length; i++) {
                list.add(arr[i].split("/")[1]);
                list1.add(arr[i].split("/")[0]);
            }
            for (int i = 0; i < qyzps.size(); i++) {
                for (int j = 0; j < list1.size(); j++) {
                    if (qyzps.get(i).getId() == Integer.parseInt(list1.get(j))) {
                        qyzps1.add(qyzps.get(i));
                    }
                }
            }
            adapter = new ZPXXAdapter(qyzps1, list);
            recycleView.setAdapter(adapter);
        }
    }

    private void initView() {
        title.setText("人才市场--招聘收藏");
        gridLayoutManager = new GridLayoutManager(this, 2);
        recycleView.setLayoutManager(gridLayoutManager);
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}

