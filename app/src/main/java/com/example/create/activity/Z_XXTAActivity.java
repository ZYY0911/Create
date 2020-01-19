package com.example.create.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.adapter.TZ_Adapter;
import com.example.create.bean.TZ_SQL;
import com.example.create.dialog.RequestDailog;

import org.litepal.LitePal;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-18
 */
public class Z_XXTAActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.my_list)
    ListView myList;
    private List<TZ_SQL> tz_sqls;
    private TZ_Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xxtz_layout);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        tz_sqls = LitePal.where("is=?", "1").find(TZ_SQL.class);
        Collections.sort(tz_sqls, new Comparator<TZ_SQL>() {
            @Override
            public int compare(TZ_SQL o1, TZ_SQL o2) {
                return o2.getTime().compareTo(o1.getTime());
            }
        });
        adapter = new TZ_Adapter(this, R.layout.tz_item, tz_sqls);
        myList.setAdapter(adapter);
        adapter.setMyClick(new TZ_Adapter.MyClick() {
            @Override
            public void clcik(int psoiton) {
                RequestDailog dailog = new RequestDailog(tz_sqls.get(psoiton).getId());
                dailog.show(getSupportFragmentManager(), "aaa");
                dailog.setMyClick(new RequestDailog.MyClick() {
                    @Override
                    public void clcik() {
                        initData();
                    }
                });
            }
        });
    }

    private void initView() {
        title.setText("人才市场--消息通知");

    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
