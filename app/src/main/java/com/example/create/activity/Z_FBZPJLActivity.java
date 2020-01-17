package com.example.create.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.adapter.FBZPJLAdapter;
import com.example.create.bean.QYZP;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-17
 */
public class Z_FBZPJLActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.my_list)
    ListView myList;
    private List<QYZP> qyzps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fbzpjl_layout);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        qyzps = LitePal.where("state=?","2").find(QYZP.class);
        myList.setAdapter(new FBZPJLAdapter(this,R.layout.fbzpjl_item,qyzps));
    }

    private void initView() {
        title.setText("审核记录");
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
