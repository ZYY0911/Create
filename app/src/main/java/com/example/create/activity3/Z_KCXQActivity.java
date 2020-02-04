package com.example.create.activity3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.adapter3.KCXQAdapter;
import com.example.create.bean3.RK;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-03
 */
public class Z_KCXQActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image_tp)
    ImageView imageTp;
    @BindView(R.id.yumc_tv)
    TextView yumcTv;
    @BindView(R.id.yumc_xh)
    TextView yumcXh;
    @BindView(R.id.yumc_kc)
    TextView yumcKc;
    @BindView(R.id.yumc_wz)
    TextView yumcWz;
    @BindView(R.id.right_list)
    ListView rightList;
    @BindView(R.id.text_yl)
    TextView textYl;
    private RK rk;
    private List<RK> rks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kcxq_layout);
        ButterKnife.bind(this);
        initView();
        initRight();
    }

    private void initRight() {
        rks = LitePal.where("ylmc=? and num>?", rk.getYlmc(), "0").find(RK.class);
        int yl = 0;
        for (int i = 0; i < rks.size(); i++) {
            RK rk = rks.get(i);
            yl += rk.getNum();
        }
        textYl.setText("余量:" + yl);
        rightList.setAdapter(new KCXQAdapter(this,R.layout.kcxq_item,rks));

    }

    private void initView() {
        title.setText("原料库存管理--库存详情");
        rk = (RK) getIntent().getSerializableExtra("data");
        switch (rk.getYlmc()) {
            case "苹果":
                imageTp.setImageResource(R.mipmap.apple);
                break;
            case "香蕉":
                imageTp.setImageResource(R.mipmap.banana);
                break;
            case "樱桃":
                imageTp.setImageResource(R.mipmap.cherry);
                break;
            case "葡萄":
                imageTp.setImageResource(R.mipmap.grape);
                break;
            case "芒果":
                imageTp.setImageResource(R.mipmap.mango);
                break;
            case "橙子":
                imageTp.setImageResource(R.mipmap.orange);
                break;
        }
        yumcTv.setText(rk.getYlmc());
        yumcXh.setText(rk.getXh());
        yumcKc.setText(rk.getNum() + "");
        yumcWz.setText(rk.getLocation());
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
