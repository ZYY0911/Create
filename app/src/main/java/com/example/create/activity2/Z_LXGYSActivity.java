package com.example.create.activity2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.adapter2.LXGYSAdapter;
import com.example.create.bean2.GYS;
import com.example.create.bean2.GYSP;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-20
 */
public class Z_LXGYSActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.my_list)
    ListView myList;
    private int lx;
    private String name;
    private List<GYS> gys;
    private LXGYSAdapter adapter;
    private int index;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lxgys_layout);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        lx = getIntent().getIntExtra("lx", 1);
        name = getIntent().getStringExtra("name");
        switch (lx) {
            case 1:
                initDq();
                break;
            case 2:
                initFw();
                break;
            case 3:
                initYl();
                break;
            case 4:
                initJg();
                break;
            case 5:
                initMc();
                break;
            case 6:
                initLx();
                break;
        }

    }

    private void initJg() {
        List<GYSP> gysps = new ArrayList<>();
        index = getIntent().getIntExtra("index", 0);
        switch (index) {
            case 0:
                gysps = LitePal.where("ylPrice < ?", "5").find(GYSP.class);
                break;
            case 1:
                gysps = LitePal.where("ylPrice>=? and ylPrice<?", "5", "10").find(GYSP.class);
                break;
            case 2:
                gysps = LitePal.where("ylPrice>=? and ylPrice<?", "10", "15").find(GYSP.class);
                break;
            case 3:
                gysps = LitePal.where("ylPrice > ?", "15").find(GYSP.class);
                break;
        }
        gys = new ArrayList<>();
        for (int i = 0; i < gysps.size(); i++) {
            List<GYS> gys1 = LitePal.where("gysNum=?", gysps.get(i).getGysNum() + "").find(GYS.class);
            for (int j = 0; j < gys1.size(); j++) {
                gys.add(gys1.get(j));
            }
        }
        setList();
    }

    private void initLx() {
        gys = LitePal.where("gysPeople=?", name).find(GYS.class);
        setList();
    }

    private void initMc() {
        gys = LitePal.where("gysName=?", name).find(GYS.class);
        setList();

    }

    private void initYl() {
        List<GYSP> gysps = LitePal.where("ylName=?", name).find(GYSP.class);
        gys = new ArrayList<>();
        for (int i = 0; i < gysps.size(); i++) {
            List<GYS> gys1 = LitePal.where("gysNum=?", gysps.get(i).getGysNum() + "").find(GYS.class);
            for (int j = 0; j < gys1.size(); j++) {
                gys.add(gys1.get(j));
            }
        }
        setList();
    }

    private void initFw() {
        gys = LitePal.where("gysRange=?", name).find(GYS.class);
        setList();
    }

    private void initDq() {
        gys = LitePal.where("gysCity=?", name).find(GYS.class);
        setList();
    }

    private void setList() {
        adapter = new LXGYSAdapter(this, R.layout.lxgys_item, gys);
        myList.setAdapter(adapter);
        adapter.setMyclick(new LXGYSAdapter.Myclick() {
            @Override
            public void mtCccc(int index) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + gys.get(index).getGysTel()));
                startActivity(intent);
            }
        });
    }


    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
