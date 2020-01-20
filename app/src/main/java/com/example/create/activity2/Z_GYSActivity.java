package com.example.create.activity2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.create.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-19
 */
public class Z_GYSActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.layout_create1)
    LinearLayout layoutCreate1;
    @BindView(R.id.layout_create2)
    LinearLayout layoutCreate2;
    @BindView(R.id.layout_create3)
    LinearLayout layoutCreate3;
    @BindView(R.id.layout_create4)
    LinearLayout layoutCreate4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gys_layout);
        ButterKnife.bind(this);
        title.setText("供应商");
    }

    @OnClick({R.id.change, R.id.layout_create1, R.id.layout_create2, R.id.layout_create3, R.id.layout_create4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.layout_create1:
                startActivity(new Intent(this,Z_GYSLBActivity.class));
                break;
            case R.id.layout_create2:
                startActivity(new Intent(this,Z_GYSCXActivity.class));
                break;
            case R.id.layout_create3:
                break;
            case R.id.layout_create4:
                startActivity(new Intent(this,Z_GYSTJActivity.class));
                break;
        }
    }
}
