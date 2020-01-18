package com.example.create.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.adapter.FBZPSHAdapter;
import com.example.create.bean.QYZP;
import com.example.create.util.SimpData;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-16
 */
public class Z_FBZPSHActivity extends AppCompatActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.my_list)
    ListView myList;
    @BindView(R.id.submit_all)
    Button submitAll;
    private List<QYZP> qyzps;
    private List<Integer> integers;
    private FBZPSHAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fbzpsgh_layout);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        if (qyzps==null) qyzps = new ArrayList<>();
        else qyzps.clear();
        qyzps = LitePal.findAll(QYZP.class);
        for (int i = qyzps.size()-1; i >=0; i--) {
            if (qyzps.get(i).getState() != 1) {
                qyzps.remove(i);
            }
        }
        adapter = new FBZPSHAdapter(this, R.layout.fbzpsh_item, qyzps);
        myList.setAdapter(adapter);
        adapter.setClick(new FBZPSHAdapter.Click() {
            @Override
            public void Click(int position, boolean is) {
                if (is) {
                    integers.add(qyzps.get(position).getId());
                } else {
                    integers.remove(qyzps.get(position).getId());
                }
            }
        });
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("aa", "onItemClick: ");
            }
        });
    }

    private void initView() {
        integers = new ArrayList<>();
        title.setText("人才市场--招聘审核");

    }

    @OnClick({R.id.change, R.id.submit_all, R.id.submit_single})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.submit_single:
                for (int i = 0; i < integers.size(); i++) {
                    QYZP qyzp = new QYZP();
                    qyzp.setState(2);
                    qyzp.setShtime(SimpData.Simp("yyyy-MM-dd HH:mm", new Date()));
                    qyzp.updateAll("id=?", integers.get(i) + "");
                }
                initData();
                break;
            case R.id.submit_all:
                if (adapter.isIs()){
                    adapter.setIs(false);
                    integers.clear();
                }else {
                    adapter.setIs(true);
                    integers.clear();
                    for (int i = 0; i < qyzps.size(); i++) {
                        integers.add(qyzps.get(i).getId());
                    }
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }
}

