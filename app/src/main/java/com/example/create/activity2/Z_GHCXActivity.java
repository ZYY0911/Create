package com.example.create.activity2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.fragment2.GHCX_Fragemnt1;
import com.example.create.fragment2.GHCX_Fragemnt2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-28
 */
public class Z_GHCXActivity extends AppCompatActivity {


    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.frame_layout2)
    FrameLayout frameLayout2;
    @BindView(R.id.left_text)
    TextView leftText;
    @BindView(R.id.right_text)
    TextView rightText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ghcx_layout);
        ButterKnife.bind(this);
        initView();
        repalce(new GHCX_Fragemnt1());
    }

    private void repalce(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout2, fragment).commit();
    }


    private void initView() {
        title.setText("供货商--供货查询");
        leftText.setTextSize(35);
        rightText.setTextSize(30);
    }


    @OnClick({R.id.change, R.id.left_text, R.id.right_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.left_text:
                repalce(new GHCX_Fragemnt1());
                leftText.setTextSize(35);
                rightText.setTextSize(30);
                break;
            case R.id.right_text:
                repalce(new GHCX_Fragemnt2());
                leftText.setTextSize(30);
                rightText.setTextSize(35);
                break;
        }
    }
}
