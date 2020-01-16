package com.example.create.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.adapter.ZPXXAdapter;
import com.example.create.bean.ZPXX;

import org.litepal.LitePal;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-16
 */
public class Z_ZPXXActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.start_image)
    ImageView startImage;
    @BindView(R.id.zhaoping_image)
    ImageView zhaopingImage;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.image_find)
    ImageView imageFind;
    private List<ZPXX> zpxxes;
    private GridLayoutManager gridLayoutManager;
    private ZPXXAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zpxx_layout);
        ButterKnife.bind(this);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        title.setText("人才市场--招聘信息");
        gridLayoutManager = new GridLayoutManager(this, 2);
        recycleView.setLayoutManager(gridLayoutManager);
    }

    private void initData() {
        zpxxes = LitePal.findAll(ZPXX.class);
        Collections.sort(zpxxes, new Comparator<ZPXX>() {
            @Override
            public int compare(ZPXX o1, ZPXX o2) {
                return o2.getTime().compareTo(o1.getTime());
            }
        });
        adapter = new ZPXXAdapter(zpxxes);
        recycleView.setAdapter(adapter);


    }

    private void initListener() {
        etInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO) {
                    InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (inputMethodManager.isActive()) {
                        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });
    }


    @OnClick({R.id.change, R.id.start_image, R.id.zhaoping_image,R.id.image_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.start_image:
                break;
            case R.id.zhaoping_image:
                break;
            case R.id.image_find:
                startActivity(new Intent(this,ZPXXActivity2.class));
                break;
        }
    }
}
