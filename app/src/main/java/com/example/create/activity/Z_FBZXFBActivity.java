package com.example.create.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.create.AppClient;
import com.example.create.R;
import com.example.create.bean.QYZP;
import com.example.create.util.SimpData;

import java.text.DecimalFormat;
import java.util.Date;

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
    private AppClient appClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fbzpfb_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("发布招聘信息");
        appClient = (AppClient) getApplication();
    }

    @OnClick({R.id.change, R.id.bt_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.bt_save:
                QYZP qyzp = new QYZP();
                qyzp.setState(1);
                qyzp.setQymc(etName.getText().toString());
                qyzp.setSzd(etProvince.getText().toString());
                qyzp.setGsdz(etAddress.getText().toString());
                qyzp.setTel(etTel.getText().toString());
                qyzp.setEmail(etEmail.getText().toString());
                qyzp.setGw(etGw.getText().toString());
                qyzp.setXz(etJg.getText().toString());
                qyzp.setXlyq(etXl.getText().toString());
                qyzp.setZyyq(etMajor.getText().toString());
                qyzp.setGzjl(etGzjl.getText().toString());
                qyzp.setGwyq(etGwyq.getText().toString());
                qyzp.setUser(AppClient.getUser());
                qyzp.setTime(SimpData.Simp("yyyy-MM-dd HH:mm",new Date()));
                String [] infos = appClient.getID().split(",");
                if (infos[0].equals(SimpData.Simp("d",new Date()))){
                    setNum(qyzp);
                }else {
                    appClient.setID(SimpData.Simp("d",new Date())+",1");
                    setNum(qyzp);
                }
                qyzp.save();
                Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setNum(QYZP qyzp){
        String [] infos = appClient.getID().split(",");
        int indes = Integer.parseInt(infos[1]);
        String str = String.format("%04d", indes);
        qyzp.setNum(SimpData.Simp("yyyyMMdd",new Date())+str);
        appClient.setID(infos[0]+","+(indes+1));
    }
}

