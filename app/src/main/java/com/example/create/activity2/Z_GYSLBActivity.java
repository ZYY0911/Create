package com.example.create.activity2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.create.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-19
 */
public class Z_GYSLBActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.myList)
    ListView myList;
    @BindView(R.id.title2)
    ImageView title2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyslb_layout);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        title.setText("供应商--供应商列表");
    }


    @OnClick({R.id.change, R.id.title2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.title2:
                break;
        }
    }
}
