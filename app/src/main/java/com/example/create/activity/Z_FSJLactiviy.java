package com.example.create.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.create.AppClient;
import com.example.create.R;
import com.example.create.adapter.FSJLAdapter;
import com.example.create.bean.JLFS;
import com.example.create.bean.QYZP;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-17
 */
public class Z_FSJLactiviy extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    private GridLayoutManager gridLayoutManager;
    private List<JLFS> jlfs;
    private List<QYZP> qyzps;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fsjl_laout);
        ButterKnife.bind(this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recycle.setLayoutManager(gridLayoutManager);
        title.setText("人才市场--简历");
        jlfs = LitePal.where("user=?", AppClient.getName()).find(JLFS.class);
        Log.i("jljljl", "onCreate: "+jlfs.size());
        qyzps = new ArrayList<>();
        for (int i = 0; i < jlfs.size(); i++) {
            qyzps.add(LitePal.where("id=?",jlfs.get(i).getGsId()+"").find(QYZP.class).get(0));
        }
        recycle.setAdapter(new FSJLAdapter(jlfs,qyzps));
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
