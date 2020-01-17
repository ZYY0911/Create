package com.example.create.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.adapter.ZPXXListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-16
 */
public class ZPXXActivity2 extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.image_find)
    ImageView imageFind;
    @BindView(R.id.my_list)
    ListView myList;
    @BindView(R.id.tv_dis)
    TextView tvDis;
    private List<String> list;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zpxx_layout2);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        etInput.setText(getIntent().getStringExtra("Gs"));
        if (list == null) {
            list = new ArrayList<>();
        } else {
            list.clear();
        }
        list.add("全部招聘信息");
        list.add("按公司名称查询");
        list.add("按行业查询");
        list.add("按岗位查询");
        list.add("按专业查询");
        list.add("按所在地查询");
        list.add("按学历查询");
        list.add("按薪资查询");
        list.add("按发布时间查询");
        myList.setAdapter(new ZPXXListViewAdapter(this,R.layout.zpxx_list_item,list));
        intent = new Intent();
    }

    @OnClick({R.id.change, R.id.image_find,R.id.tv_dis})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.image_find:
                break;
            case R.id.tv_dis:
                intent.putExtra("lx",1);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}

