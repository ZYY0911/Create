package com.example.create.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.create.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-16
 */
public class Z_FBZXFBActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_province)
    EditText etProvince;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_gw)
    EditText etGw;
    @BindView(R.id.et_jg)
    EditText etJg;
    @BindView(R.id.et_gzjl)
    EditText etGzjl;
    @BindView(R.id.et_xl)
    EditText etXl;
    @BindView(R.id.et_major)
    EditText etMajor;
    @BindView(R.id.et_gwyq)
    EditText etGwyq;
    @BindView(R.id.bt_save)
    Button btSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fbzpfb_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("发布招聘信息");
    }

    @OnClick({R.id.change, R.id.bt_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.bt_save:
                break;
        }
    }
}

