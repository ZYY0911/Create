package com.example.create.activity5;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.fragment5.CJFragment1;
import com.example.create.fragment5.CJFragment2;
import com.example.create.fragment5.CJFragment3;
import com.example.create.fragment5.CJFragment4;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-05 ：）
 */
public class Z_CJMKActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.bottom_nav)
    BottomNavigationView bottomNav;
    private List<Fragment> fragments;
    private int index =0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cjmk_layout);
        ButterKnife.bind(this);
        initView();
        initData();
        initClick();
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new CJFragment1());
        fragments.add(new CJFragment2());
        fragments.add(new CJFragment3());
        fragments.add(new CJFragment4());
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Log.i("onPageSelected", "onPageSelected: "+i);
                switch (i){
                    case 0:
                        bottomNav.setSelectedItemId(R.id.cj_1);
                        title.setText("维修车间-1");
                        break;
                    case 1:
                        bottomNav.setSelectedItemId(R.id.cj_2);
                        title.setText("维修车间-2");
                        break;
                    case 2:
                        bottomNav.setSelectedItemId(R.id.cj_3);
                        title.setText("维修车间-3");
                        break;
                    case 3:
                        bottomNav.setSelectedItemId(R.id.cj_4);
                        title.setText("维修车间-4");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setCurrentItem(index);
    }

    private void initClick() {
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.cj_1:
                        viewPager.setCurrentItem(0);
                        title.setText("维修车间-1");
                        break;
                    case R.id.cj_2:
                        viewPager.setCurrentItem(1);
                        title.setText("维修车间-2");
                        break;
                    case R.id.cj_3:
                        viewPager.setCurrentItem(2);
                        title.setText("维修车间-3");
                        break;
                    case R.id.cj_4:
                        viewPager.setCurrentItem(3);
                        title.setText("维修车间-4");
                        break;
                }
                return true;
            }
        });
    }

    private void initView() {
        index = getIntent().getIntExtra("index",0);
        bottomNav.setLabelVisibilityMode(1);
        title.setText("维修车间-1");
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }

    class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
