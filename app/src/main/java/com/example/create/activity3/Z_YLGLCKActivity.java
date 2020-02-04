package com.example.create.activity3;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.create.AppClient;
import com.example.create.R;
import com.example.create.adapter3.YLGLCKAdapter;
import com.example.create.bean3.CK;
import com.example.create.bean3.CK_CG;
import com.example.create.bean3.RK;
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
 * Create by 张瀛煜 on 2020-02-02
 */
public class Z_YLGLCKActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_ylmc)
    TextView tvYlmc;
    @BindView(R.id.tv_xh)
    TextView tvXh;
    @BindView(R.id.tv_chr)
    TextView tvChr;
    @BindView(R.id.et_jsr)
    EditText etJsr;
    @BindView(R.id.et_chl)
    EditText etChl;
    @BindView(R.id.bt_sg)
    Button btSg;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.layout_first)
    LinearLayout layoutFirst;
    @BindView(R.id.layout_second)
    LinearLayout layoutSecond;
    @BindView(R.id.layout_third)
    LinearLayout layoutThird;
    @BindView(R.id.layout_forth)
    LinearLayout layoutForth;
    @BindView(R.id.bu_sure)
    Button buSure;
    @BindView(R.id.text_scx1)
    TextView textScx1;
    @BindView(R.id.text_scx2)
    TextView textScx2;
    @BindView(R.id.text_scx3)
    TextView textScx3;
    @BindView(R.id.text_scx4)
    TextView textScx4;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.toall)
    TextView toall;
    private RK rk;
    private String scx = "";
    private TextView tvDialogYlmc;
    private TextView tvDialogYlxh;
    private TextView tvDialogChsl;
    private TextView tvDialogSj;
    private TextView tvDialogCkr;
    private TextView tvDialogJsr;
    private TextView tvDialogQx;
    private Button btDialogSubmit;
    private List<CK_CG> ck_cgs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ylglck_layout);
        ButterKnife.bind(this);
        rk = (RK) getIntent().getSerializableExtra("data");
        initView();
        initListener();
    }

    private void initListener() {
        etChl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!"".equals(s.toString())) {
                    int count = Integer.parseInt(s.toString());
                    List<RK> rks = LitePal.where("ylmc=? and xh=? and num>?", rk.getYlmc(), rk.getXh(), "0").find(RK.class);
                    Collections.sort(rks, new Comparator<RK>() {
                        @Override
                        public int compare(RK o1, RK o2) {
                            return o1.getTime().compareTo(o2.getTime());
                        }
                    });
                    ck_cgs = new ArrayList<>();
                    for (int i = 0; i < rks.size(); i++) {
                        RK rk = rks.get(i);
                        int yl = rk.getNum();
                        ck_cgs.add(new CK_CG(rk.getId(), rk.getGys(), (yl >= count) ? count : yl, (yl >= count) ? (yl - count) : 0, rk.getPrice()));
                        if (yl > count) {
                        } else {
                            count = count - yl;
                        }
                        if (yl >= Integer.parseInt(s.toString())) {
                            break;
                        }
                    }
                    int all = 0,tootal=0;
                    for (int i = 0; i < ck_cgs.size(); i++) {
                        all += ck_cgs.get(i).getCgl();
                        tootal += ck_cgs.get(i).getPrice()*ck_cgs.get(i).getCgl();
                    }
                    toall.setText("总计:"+tootal+"元");
                    if (all != Integer.parseInt(s.toString())) {
                        ShowDialog.Show("最大出货量" + all + "个", Z_YLGLCKActivity.this);
                        etChl.setText(all + "");
                        etChl.setSelection((all + "").length());
                    }
                    listView.setAdapter(new YLGLCKAdapter(Z_YLGLCKActivity.this, R.layout.ylglck_item, ck_cgs, true));
                }
            }
        });
    }

    private void initView() {
        title.setText("原料库存管理--出库");
        tvYlmc.setText(rk.getYlmc());
        tvXh.setText(rk.getXh());
        tvChr.setText(rk.getKrname());
    }

    @OnClick({R.id.change, R.id.bt_sg, R.id.layout_first, R.id.layout_second, R.id.layout_third, R.id.layout_forth, R.id.bu_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.bt_sg:
                break;
            case R.id.layout_first:
                scx = "生产线一";
                textScx1.setTextColor(Color.WHITE);
                textScx2.setTextColor(Color.BLACK);
                textScx3.setTextColor(Color.BLACK);
                textScx4.setTextColor(Color.BLACK);
                break;
            case R.id.layout_second:
                scx = "生产线二";
                textScx1.setTextColor(Color.BLACK);
                textScx2.setTextColor(Color.WHITE);
                textScx3.setTextColor(Color.BLACK);
                textScx4.setTextColor(Color.BLACK);
                break;
            case R.id.layout_third:
                scx = "生产线三";
                textScx1.setTextColor(Color.BLACK);
                textScx2.setTextColor(Color.BLACK);
                textScx3.setTextColor(Color.WHITE);
                textScx4.setTextColor(Color.BLACK);
                break;
            case R.id.layout_forth:
                scx = "生产线四";
                textScx1.setTextColor(Color.BLACK);
                textScx2.setTextColor(Color.BLACK);
                textScx3.setTextColor(Color.BLACK);
                textScx4.setTextColor(Color.WHITE);
                break;
            case R.id.bu_sure:
                if ("".equals(scx)) {
                    ShowDialog.Show("请选择生产线", this);
                    return;
                }
                if ("".equals(etJsr.getText())) {
                    ShowDialog.Show("请输入接收人", this);
                    return;
                }
                AlertDialog builder = new AlertDialog.Builder(this).create();
                View view1 = LayoutInflater.from(this).inflate(R.layout.chxxd_dialog, null);
                builder.setView(view1);
                initView(view1, builder);
                builder.show();
                break;
        }
    }

    private void initView(View view1, final AlertDialog builder) {
        tvDialogYlmc = view1.findViewById(R.id.tv_dialog_ylmc);
        tvDialogYlxh = view1.findViewById(R.id.tv_dialog_ylxh);
        tvDialogChsl = view1.findViewById(R.id.tv_dialog_chsl);
        tvDialogSj = view1.findViewById(R.id.tv_dialog_sj);
        tvDialogCkr = view1.findViewById(R.id.tv_dialog_ckr);
        tvDialogJsr = view1.findViewById(R.id.tv_dialog_jsr);
        tvDialogQx = view1.findViewById(R.id.tv_dialog_qx);
        btDialogSubmit = view1.findViewById(R.id.bt_dialog_submit);
        tvDialogYlmc.setText(rk.getYlmc());
        tvDialogYlxh.setText(rk.getXh());
        tvDialogChsl.setText(etChl.getText());
        tvDialogSj.setText(SimpData.Simp("yyyy-MM-dd", new Date()));
        tvDialogCkr.setText(AppClient.getName());
        tvDialogJsr.setText(etJsr.getText());
        tvDialogQx.setText(scx);
        btDialogSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < ck_cgs.size(); i++) {
                    CK_CG ck_cg = ck_cgs.get(i);
                    CK ck = new CK();
                    ck.setGys(rk.getGys());
                    ck.setYlmc(rk.getYlmc());
                    ck.setCkr(AppClient.getName());
                    ck.setJsr(etJsr.getText().toString());
                    ck.setNum(ck_cg.getCgl());
                    ck.setXh(rk.getXh());
                    ck.setTime(tvDialogSj.getText().toString());
                    ck.setScx(scx);
                    ck.setPrice(ck_cg.getPrice());
                    ck.save();
                    RK rk = new RK();
                    if (ck_cg.getYl() == 0) {
                        rk.setNum(-1);
                    } else {
                        rk.setNum(ck_cg.getYl());
                    }
                    Log.e("eee", "onClick: " + ck_cg.getYl());
                    rk.updateAll("id=?", ck_cg.getId() + "");
                    builder.dismiss();
                    android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(Z_YLGLCKActivity.this);
                    builder1.setTitle("提示");
                    builder1.setMessage("出库成功");
                    builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder1.show();
                }
            }
        });
    }
}
