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
import com.example.create.bean5.SCX;
import com.example.create.fragment5.SCXFragment1;
import com.example.create.fragment5.SCXFragment2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-07 ：）
 */
public class Z_SCXActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.layout_fragment)
    FrameLayout layoutFragment;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private int cj, lx;
    private String scx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scx_layout);
        ButterKnife.bind(this);
        initView();
        initClick();
    }

    private void initView() {
        cj = getIntent().getIntExtra("cj", 1);
        lx = getIntent().getIntExtra("lx", 1);
        title.setText(cj + "号车间");
        if (lx == 1) {
            scx = getIntent().getStringExtra("scx");
            replace(new SCXFragment1(cj, scx));
            navigation.setSelectedItemId(R.id.look_scx);
        } else if (lx == 2) {
            scx = "";
            replace(new SCXFragment2(cj));
            navigation.setSelectedItemId(R.id.setting_scx);
        }

    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_fragment, fragment).commit();
    }

    private void initClick() {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.look_scx:
                        replace(new SCXFragment1(cj,scx));
                        break;
                    case R.id.setting_scx:
                        replace(new SCXFragment2(cj));
                        break;
                }
                return true;
            }
        });
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
