package com.example.create.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.create.AppClient;
import com.example.create.R;
import com.example.create.activity.Z_WDJLActivity;
import com.example.create.bean.JLLB;
import com.example.create.util.SimpData;

import java.util.Date;

/**
 * Create by 张瀛煜 on 2020-01-15
 */
public class Z_SCJLDialog extends DialogFragment {
    private ImageView change;
    private TextView title;
    public EditText etWj;
    private Button btLook;
    private EditText etMc;
    private EditText etBz;
    private Button btSubmit;
    private Button btExit;
    private AlertDialog.Builder builder;
    private AppClient appClient;

    public interface Flash{
        void myFlash();
    }

    private Flash flash;

    public void setFlash(Flash flash) {
        this.flash = flash;
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    flash.myFlash();
                    getDialog().dismiss();
                }
            });
            builder.show();
            return false;
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.scjl_dialgfragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initClick();
    }

    private void initClick() {
        btLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                getActivity().startActivityForResult(intent, Z_WDJLActivity.REQUEST_CODE);
            }
        });
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JLLB jllb = new JLLB();
                jllb.setUser(appClient.getName());
                jllb.setJlFile(etWj.getText().toString().trim());
                jllb.setJlName(etMc.getText().toString().trim());
                jllb.setJlInfo(etBz.getText().toString().trim());
                jllb.setJlTime(SimpData.Simp("yyyy-MM-dd HH:mm", new Date()));
                jllb.save();
                handler.sendEmptyMessage(0);
            }
        });
    }

    private void initView() {
        appClient= (AppClient) getActivity().getApplication();
        change = getView().findViewById(R.id.change);
        change.setVisibility(View.GONE);
        title = getView().findViewById(R.id.title);
        etWj = getView().findViewById(R.id.et_wj);
        btLook = getView().findViewById(R.id.bt_look);
        etMc = getView().findViewById(R.id.et_mc);
        etBz = getView().findViewById(R.id.et_bz);
        btSubmit = getView().findViewById(R.id.bt_submit);
        btExit = getView().findViewById(R.id.bt_exit);
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("提示");
        builder.setMessage("上传成功");
        title.setText("人才市场--我的简历");
    }
}

