package com.example.create.activity;

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
 * Create by 张瀛煜 on 2020-01-16
 */
public class Z_FBZPGLActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.layout_fb)
    LinearLayout layoutFb;
    @BindView(R.id.layout_sh)
    LinearLayout layoutSh;
    @BindView(R.id.layout_ls)
    LinearLayout layoutLs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fbzpgl_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.change, R.id.layout_fb, R.id.layout_sh, R.id.layout_ls})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.layout_fb:
                break;
            case R.id.layout_sh:
                break;
            case R.id.layout_ls:
                break;
        }
    }
}
