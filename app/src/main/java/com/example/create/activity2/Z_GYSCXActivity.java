package com.example.create.activity2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.adapter2.GYSCXAdapter;
import com.example.create.adapter2.SelectAdapter;
import com.example.create.bean2.GYS;
import com.example.create.bean2.GYSCXBean;
import com.example.create.bean2.GYSP;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-20
 */
public class Z_GYSCXActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.image_find)
    ImageView imageFind;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    private List<String> list;
    private SelectAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyscx_layout);
        ButterKnife.bind(this);
        title.setText("供应商--供应商查询");
        initView();
        initData();
    }

    private void initData() {
        adapter = new SelectAdapter(this, R.layout.gyscx_left_item, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setIndex(position);
                listView.smoothScrollToPositionFromTop(position - 1, 0, 500);
                listClick(position);
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void listClick(int position) {
        List<GYS> gys = LitePal.findAll(GYS.class);
        List<GYSP> gysps = LitePal.findAll(GYSP.class);
        List<GYSCXBean> gyscxBeans = new ArrayList<>();
        switch (list.get(position)) {
            case "地区":
                for (int i = 0; i < gys.size(); i++) {
                    int image;
                    GYS gys1 = gys.get(i);
                    switch (gys1.getGysCity()) {
                        case "北京":
                            image = R.drawable.city1;
                            break;
                        case "上海":
                            image = R.drawable.city2;
                            break;
                        case "天津":
                            image = R.drawable.city3;
                            break;
                        case "山东":
                            image = R.drawable.city4;
                            break;
                        default:
                            image = R.drawable.city5;
                            break;
                    }
                    gyscxBeans.add(new GYSCXBean(gys1.getGysCity(), image));
                }
                recycleView.setAdapter(new GYSCXAdapter(gyscxBeans));
                break;
            case "业务范围":
                break;
            case "原料名称":
                break;
            case "价格":
                break;
            case "名称":
                break;
            case "联系人":
                break;
        }
    }

    private void initView() {
        list = new ArrayList<>();
        list.add("地区");
        list.add("业务范围");
        list.add("原料名称");
        list.add("价格");
        list.add("名称");
        list.add("联系人");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recycleView.setLayoutManager(gridLayoutManager);
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
