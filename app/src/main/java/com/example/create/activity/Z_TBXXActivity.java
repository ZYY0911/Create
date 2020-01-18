package com.example.create.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.fragment.TBFXFragemnt2;
import com.example.create.fragment.TBFXFragment1;
import com.example.create.fragment.TBFXFragment3;
import com.example.create.fragment.TBFXFragment4;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-17
 */
public class Z_TBXXActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.layout)
    LinearLayout layout;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tbxl_layout);
        ButterKnife.bind(this);
        initView();
        setData();
    }

    private void setData() {
        fragments = new ArrayList<>();
        fragments.add(new TBFXFragment1());
        fragments.add(new TBFXFragemnt2());
        fragments.add(new TBFXFragment3());
        fragments.add(new TBFXFragment4());
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                for (int j = 0; j < layout.getChildCount(); j++) {
                    ImageView imageView = (ImageView) layout.getChildAt(j);
                    if (j == i) {
                        imageView.setImageResource(R.drawable.page_indicator_focused);
                    } else {
                        imageView.setImageResource(R.drawable.page_indicator_unfocused);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        for (int i = 0; i < fragments.size(); i++) {
            ImageView imageView = new ImageView(this);
            if (i == 0) {
                imageView.setImageResource(R.drawable.page_indicator_focused);
            } else {
                imageView.setImageResource(R.drawable.page_indicator_unfocused);
            }
            imageView.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
            layout.addView(imageView);
        }
    }

    private void initView() {
        title.setText("人才市场--图表分析");
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
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

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
