package com.example.create.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.create.AppClient;
import com.example.create.R;
import com.example.create.bean.JBXX;

import org.litepal.LitePal;

import java.util.List;

/**
 * Create by 张瀛煜 on 2020-01-15
 */
public class Z_RCSCActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ImageView change;
    private TextView title;
    private NavigationView navView;
    private List<JBXX> jbxx;
    private AppClient appClient;
    private String imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rcsc_layout);
        initView();
        initClick();
        initData();
    }

    private void initData() {
        if (jbxx != null) jbxx.clear();
        jbxx = LitePal.where("name=?", String.valueOf(appClient.getName())).find(JBXX.class);
        if (jbxx.size()>0){
            imageUrl = jbxx.get(0).getPhoto();
        }
    }


    private void initClick() {
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
                ImageView icon_photo = navView.findViewById(R.id.icon_photo);
                Glide.with(Z_RCSCActivity.this).load(imageUrl).into(icon_photo);
            }
        });
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Class myClass = null;
                switch (menuItem.getItemId()){
                    case R.id.first:
                        myClass = Z_WDJLActivity.class;
                        break;
                    case R.id.second:
                        myClass = Z_ZPXXActivity.class;
                        break;
                    case R.id.third:
                        myClass = Z_FBZPGLActivity.class;
                        break;
                }
                startActivity(new Intent(Z_RCSCActivity.this,myClass));
                return true;
            }
        });
    }

    private void initView() {
        drawerLayout = findViewById(R.id.drawer_layout);
        change = findViewById(R.id.change);
        title = findViewById(R.id.title);
        navView = findViewById(R.id.nav_view);
        title.setText("人才市场");
        appClient = (AppClient) getApplication();
    }
}

