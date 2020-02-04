package com.example.create.activity3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.adapter3.YZSZAdapter2;
import com.example.create.bean2.GSCP;
import com.example.create.bean3.KCL;
import com.example.create.bean3.YZ;
import com.example.create.util.ShowDialog;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-03 ：）
 */
public class Z_YZSZActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.my_list)
    ListView myList;
    @BindView(R.id.save)
    Button save;
    private List<KCL> kcls;
    private YZ yzs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yzsz_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("原料库存管理--设置阈值");
        kcls = new ArrayList<>();
        yzs = LitePal.findAll(YZ.class).get(0);
        List<GSCP> gscps = LitePal.findAll(GSCP.class);
        for (int i = 0; i < gscps.size(); i++) {
            GSCP gscp = gscps.get(i);
            int yz = 0;
            switch (gscp.getName()) {
                case "苹果":
                    yz = yzs.getApple();
                    break;
                case "葡萄":
                    yz = yzs.getGrape();
                    break;
                case "香蕉":
                    yz = yzs.getBanban();
                    break;
                case "樱桃":
                    yz = yzs.getCherry();
                    break;
                case "芒果":
                    yz = yzs.getMango();
                    break;
                case "橙子":
                    yz = yzs.getOrange();
                    break;
            }
            kcls.add(new KCL(gscp.getName(), gscp.getXh(), yz + ""));
        }
        final YZSZAdapter2 adapter2 = new YZSZAdapter2(this, R.layout.szyz_item, kcls);
        myList.setAdapter(adapter2);
        adapter2.setClick(new YZSZAdapter2.MyClick() {
            @Override
            public void myClick(int position, int lx, int num) {
                KCL kcl = kcls.get(position);
                int count = Integer.parseInt(kcl.getKc());
                if (lx == 1) {
                    count = count + 100;
                } else if (lx == 2) {
                    count = count - 100;
                    if (count <= 0) {
                        count = 0;
                    }
                } else {
                    count = num;
                }
                kcl.setKc(count + "");
                kcls.set(position, kcl);
                adapter2.notifyDataSetChanged();
            }
        });
    }


    @OnClick({R.id.change, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.save:
                YZ yz = new YZ();
                yz.setApple(getYz("苹果"));
                yz.setBanban(getYz("香蕉"));
                yz.setCherry(getYz("樱桃"));
                yz.setGrape(getYz("葡萄"));
                yz.setMango(getYz("芒果"));
                yz.setOrange(getYz("橙子"));
                yz.updateAll("id=?", "1");
                ShowDialog.Show("保存成功", this);
                break;
        }
    }

    private int getYz(String name) {
        for (int i = 0; i < kcls.size(); i++) {
            KCL kcl = kcls.get(i);
            if (name.equals(kcl.getYlm())) {
                return Integer.parseInt(kcl.getKc());
            }
        }
        return 100;
    }
}
