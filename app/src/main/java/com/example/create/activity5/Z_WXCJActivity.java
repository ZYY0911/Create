package com.example.create.activity5;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.fragment5.WXCJFragment1;
import com.example.create.fragment5.WXCJFragment2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-02-06 ：）
 */
public class Z_WXCJActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.bottom_nva)
    BottomNavigationView bottomNva;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wxcj_layout);
        ButterKnife.bind(this);
        initView();
        initClick();
        replace(new WXCJFragment1());
    }


    private void replace(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,fragment).commit();
    }
    private void initClick() {
        bottomNva.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bx_item:
                        replace(new WXCJFragment1());
                        break;
                    case R.id.log_item:
                        replace(new WXCJFragment2());
                        break;
                }
                return true;
            }
        });
    }

    private void initView() {
        title.setText("维修车间");

    }
}
