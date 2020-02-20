package com.example.create.fragment5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.create.R;
import com.example.create.bean5.SCX;
import com.example.create.util.ShowDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-02-07 ：）
 */
@SuppressLint("ValidFragment")
public class SCXFragment2 extends Fragment {
    @BindView(R.id.et_hj)
    EditText etHj;
    @BindView(R.id.spinnet_zt)
    Spinner spinnetZt;
    @BindView(R.id.et_syts)
    EditText etSyts;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.bt_exit)
    Button btExit;
    Unbinder unbinder;
    @BindView(R.id.spinnet_scx)
    Spinner spinnetScx;
    @BindView(R.id.et_scx)
    EditText etScx;
    @BindView(R.id.input_layout)
    LinearLayout inputLayout;
    private int cj;

    public SCXFragment2(int cj) {
        this.cj = cj;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scx_fragment2, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inputLayout.setVisibility(View.GONE);
        spinnetScx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnetScx.getSelectedItem().toString().equals("其他")) {
                    inputLayout.setVisibility(View.VISIBLE);
                } else {
                    inputLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_submit, R.id.bt_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                String hj = etHj.getText().toString().trim();
                String zt = spinnetZt.getSelectedItem().toString();
                String ts = etSyts.getText().toString().trim();
                String sc;
                if (inputLayout.getVisibility() == View.VISIBLE) {
                    sc = etScx.getText().toString();
                } else {
                    sc = spinnetScx.getSelectedItem().toString();
                }
                if ("".equals(hj)) {
                    ShowDialog.Show("请输入生产环节", getContext());
                    return;
                }
                if ("".equals(ts)) {
                    ShowDialog.Show("请输入生产天数", getContext());
                    return;
                }
                SCX scx = new SCX();
                scx.setHj(hj);
                scx.setCj(cj);
                scx.setScx(sc);
                scx.setState(zt);
                scx.setTs(ts);
                scx.save();
                ShowDialog.Show("保存成功", getContext());
                etHj.setText("");
                spinnetZt.setSelection(0);
                etSyts.setText("");
                break;
            case R.id.bt_exit:
                etHj.setText("");
                spinnetZt.setSelection(0);
                etSyts.setText("");
                break;
        }
    }
}
