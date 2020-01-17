package com.example.create.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.create.AppClient;
import com.example.create.R;
import com.example.create.adapter.ZPXXAdapter;
import com.example.create.adapter.ZPXXListAdapter;
import com.example.create.bean.QYZP;
import com.example.create.dialog.Z_JLDialog;
import com.example.create.util.ShowDialog;
import com.example.create.util.SimpData;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
    @BindView(R.id.myList)
    ListView myList;
    private List<QYZP> qyzps;
    private ZPXXListAdapter zpxxListAdapter;
    private String[] sc;

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
    }

    private void initData() {
        qyzps = LitePal.findAll(QYZP.class);
        Collections.sort(qyzps, new Comparator<QYZP>() {
            @Override
            public int compare(QYZP o1, QYZP o2) {
                return o2.getTime().compareTo(o1.getTime());
            }
        });
        String jl = AppClient.getJlSc();
        final List<Integer> list = new ArrayList<>();
        if (!"".equals(jl)) {
            sc = jl.split(",");
            for (int i = 0; i < sc.length; i++) {
                list.add(Integer.valueOf(sc[i].split("/")[0]));
            }
        }
        zpxxListAdapter = new ZPXXListAdapter(this, R.layout.zpxx_item, qyzps, list);
        myList.setAdapter(zpxxListAdapter);
        zpxxListAdapter.SetData(new ZPXXListAdapter.SetData() {
            @Override
            public void setdata(int position, int lx, boolean sc) {
                QYZP qyzp = qyzps.get(position);
                if (lx == 1) {
                    zpxxListAdapter.setIndex(position);
                } else if (lx == 2) {
                    String arr = AppClient.getJlSc();
                    if (arr.equals("")) {
                        arr += qyzp.getId() + "/" + SimpData.Simp("yyyy-MM-dd HH:mm", new Date());
                    } else {
                        arr += "," + qyzp.getId() + "/" + SimpData.Simp("yyyy-MM-dd HH:mm", new Date());
                    }
                    Log.i("sss", "setdata: " + arr);
                    AppClient.setJlSc(arr);
                    list.add(qyzp.getId());
                } else if (lx == 3) {
                    Z_JLDialog dialog = new Z_JLDialog(qyzp.getId());
                    dialog.show(getSupportFragmentManager(), "aaa");

                }
                zpxxListAdapter.notifyDataSetChanged();
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    switch (data.getIntExtra("lx", 1)) {
                        case 1:

                            break;
                    }

                }
                break;
        }
    }

    @OnClick({R.id.change, R.id.start_image, R.id.zhaoping_image, R.id.image_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.start_image:
                startActivity(new Intent(this, Z_ZPSCActivity.class));
                break;
            case R.id.zhaoping_image:
                startActivity(new Intent(this, Z_FSJLactiviy.class));

                break;
            case R.id.image_find:
                if ("".equals(etInput.getText().toString().trim())) {
                    ShowDialog.Show("请输入查找公司", this);
                } else {
                    Intent intent = new Intent(this, ZPXXActivity2.class);
                    intent.putExtra("Gs", etInput.getText().toString().trim());
                    startActivityForResult(intent, 1);
                }
                break;
        }
    }
}
