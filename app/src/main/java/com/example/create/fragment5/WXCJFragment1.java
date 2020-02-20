package com.example.create.fragment5;

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
import android.widget.Spinner;

import com.example.create.R;
import com.example.create.bean5.WXCL;
import com.example.create.util.ShowDialog;
import com.example.create.util.SimpData;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-02-06 ：）
 */
public class WXCJFragment1 extends Fragment {
    @BindView(R.id.spinner_bh)
    Spinner spinnerBh;
    @BindView(R.id.spinner_xh)
    Spinner spinnerXh;
    @BindView(R.id.spinner_question)
    Spinner spinnerQuestion;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.bt_exit)
    Button btExit;
    Unbinder unbinder;
    private boolean question;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wxcj_fragment1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        etInput.setVisibility(View.GONE);
        spinnerQuestion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ("其他".equals(spinnerQuestion.getSelectedItem().toString())) {
                    question = false;
                    etInput.setVisibility(View.VISIBLE);
                } else {
                    question = true;
                    etInput.setVisibility(View.GONE);
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
                WXCL wxcl = new WXCL();
                wxcl.setClnum(spinnerBh.getSelectedItem().toString());
                wxcl.setClxh(spinnerXh.getSelectedItem().toString());
                if (!question) {
                    if ("".equals(etInput.getText().toString())) {
                        ShowDialog.Show("请输入您的问题", getContext());
                        return;
                    } else {
                        wxcl.setQuestion(etInput.getText().toString());
                    }
                } else {
                    wxcl.setQuestion(spinnerQuestion.getSelectedItem().toString());
                }
                wxcl.setState("未修");
                wxcl.setWxtime("");
                wxcl.setTime(SimpData.Simp("yyyy-MM-dd", new Date()));
                wxcl.save();
                ShowDialog.Show("提交成功", getContext());
                upShow();
                break;
            case R.id.bt_exit:
                upShow();
                break;
        }
    }

    private void upShow() {
        spinnerBh.setSelection(0);
        spinnerXh.setSelection(0);
        spinnerQuestion.setSelection(0);

    }
}
