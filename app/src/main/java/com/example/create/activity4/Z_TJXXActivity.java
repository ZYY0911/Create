package com.example.create.activity4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.create.R;
import com.example.create.bean4.YGXX;
import com.example.create.util.ShowDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-04 ：）
 */
public class Z_TJXXActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.change1)
    ImageView change1;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.radio_1)
    RadioButton radio1;
    @BindView(R.id.radio_2)
    RadioButton radio2;
    @BindView(R.id.et_birth)
    EditText etBirth;
    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.et_scx)
    Spinner etScx;
    @BindView(R.id.et_zw)
    Spinner etZw;
    @BindView(R.id.tv_add_more)
    TextView tvAddMore;
    @BindView(R.id.layout_1)
    LinearLayout layout1;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.layout_2)
    LinearLayout layout2;
    @BindView(R.id.tv_remove_more)
    TextView tvRemoveMore;
    @BindView(R.id.birth_layout)
    LinearLayout birthLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tjxx_layout);
        ButterKnife.bind(this);
        title.setText("新建员工信息");
    }


    @OnClick({R.id.change, R.id.change1, R.id.et_birth, R.id.tv_add_more, R.id.tv_remove_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.change1:
                String name = etName.getText().toString().trim();
                String tel = etTel.getText().toString().trim();
                String birth = etBirth.getText().toString().trim();
                String scx = etScx.getSelectedItem().toString();
                String zw = etZw.getSelectedItem().toString();
                String email = etEmail.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String sex = "";
                if (radio1.isChecked()) {
                    sex = "男";
                } else if (radio2.isChecked()) {
                    sex = "女";
                } else {
                    sex = "";
                }
                if ("".equals(name)) {
                    ShowDialog.Show("请输入姓名", this);
                    return;
                }
                if ("".equals(tel)) {
                    ShowDialog.Show("请输入电话", this);
                    return;
                }
                if ("".equals(birth)) {
                    ShowDialog.Show("请输选择出生日期", this);
                    return;
                }
                if ("".equals(sex)) {
                    ShowDialog.Show("请输选择性别", this);
                    return;
                }
                if ("".equals(zw)) {
                    ShowDialog.Show("请输输入职务", this);
                    return;
                }
                YGXX ygxx = new YGXX();
                ygxx.setName(name);
                ygxx.setSex(sex);
                ygxx.setTel(tel);
                ygxx.setBirth(birth);
                ygxx.setZw(zw);
                ygxx.setScx(scx);
                ygxx.setEmail(email);
                ygxx.setAddress(address);
                ygxx.save();
                Intent intent = new Intent();
                intent.putExtra("data", 1);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.et_birth:
                Log.i("eee", "onViewClicked: ");
                TimePickerView timePickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String time = format.format(date);
                        etBirth.setText(time);
                        etBirth.setSelection(time.length());
                    }
                }).isDialog(true).build();
                timePickerView.show();
                break;
            case R.id.tv_add_more:
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_remove_more:
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
                break;
        }
    }
}
