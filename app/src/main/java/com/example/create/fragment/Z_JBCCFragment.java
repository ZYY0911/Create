package com.example.create.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.create.AppClient;
import com.example.create.MainActivity;
import com.example.create.R;
import com.example.create.activity.Z_WDJLActivity;
import com.example.create.bean.JBXX;
import com.example.create.util.ShowDialog;

import org.litepal.LitePal;

import java.io.FileNotFoundException;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Create by 张瀛煜 on 2020-01-15
 */
@SuppressLint("ValidFragment")
public class Z_JBCCFragment extends Fragment {
    private Button btEdit;
    private EditText etName;
    private EditText etProvince;
    private EditText etSex;
    public ImageView imagePhoto;
    private Button addPhoto;
    private EditText etTel;
    private EditText etBirth;
    private EditText etMajor;
    private EditText etSchool;
    private EditText etXl;
    private EditText etGzjl;
    private EditText etJyyx;
    private EditText etJx;
    private EditText etYx;
    private Button btSave;
    private AppClient appClient;
    private List<JBXX> jbxx;
    private Context context;
    public String imageUrl;

    public Z_JBCCFragment(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.jbxx_fragment, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initClick();
        etName.setText(AppClient.getName());
    }

    private void initClick() {
        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("编辑".equals(btEdit.getText())) {
                    setEnable(true);
                } else {
                    setEnable(false);
                    initData();
                }
            }
        });
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jbxx != null) jbxx.clear();
                jbxx = LitePal.where("name=?", String.valueOf(etName.getText().toString().trim())).find(JBXX.class);
                JBXX jbxx1 = new JBXX();
                jbxx1.setBirth(etBirth.getText().toString().trim());
                jbxx1.setGzjl(etGzjl.getText().toString().trim());
                jbxx1.setJx(etJx.getText().toString().trim());
                jbxx1.setJyyx(etJyyx.getText().toString().trim());
                jbxx1.setMajor(etMajor.getText().toString().trim());
                jbxx1.setProvince(etProvince.getText().toString().trim());
                jbxx1.setSchool(etSchool.getText().toString().trim());
                jbxx1.setSex(etSex.getText().toString().trim());
                jbxx1.setXl(etXl.getText().toString().trim());
                jbxx1.setTel(etTel.getText().toString().trim());
                jbxx1.setYx(etYx.getText().toString().trim());
                Log.i("aaa", "onClick: "+imageUrl);
                jbxx1.setPhoto(imageUrl);
                if (jbxx.size() == 0) {
                    jbxx1.setName(etName.getText().toString().trim());
                    jbxx1.save();
                    ShowDialog.Show("保存成功", getContext());
                } else {
                    jbxx1.updateAll("name=?", etName.getText().toString().trim());
                    ShowDialog.Show("修改成功", getContext());
                    initData();
                }
                setEnable(false);
            }
        });
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
            }
        });

    }
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        getActivity().startActivityForResult(intent, Z_WDJLActivity.CHOOSE_PHOTO);
    }

    private void initData() {
        if (jbxx != null) jbxx.clear();
        jbxx = LitePal.where("name=?", String.valueOf(appClient.getName())).find(JBXX.class);
        if (jbxx.size() > 0) {
            JBXX jbxx1 = jbxx.get(0);
            etName.setText(jbxx1.getName());
            etBirth.setText(jbxx1.getBirth());
            etGzjl.setText(jbxx1.getGzjl());
            etJx.setText(jbxx1.getJx());
            etJyyx.setText(jbxx1.getJyyx());
            etMajor.setText(jbxx1.getMajor());
            etProvince.setText(jbxx1.getProvince());
            etSchool.setText(jbxx1.getSchool());
            etSex.setText(jbxx1.getSex());
            etXl.setText(jbxx1.getXl());
            etTel.setText(jbxx1.getTel());
            etYx.setText(jbxx1.getYx());
            Glide.with(getView()).load(jbxx1.getPhoto()).into(imagePhoto);
            setEnable(false);
        }
    }

    private void setEnable(boolean is) {
        etName.setEnabled(is);
        etBirth.setEnabled(is);
        etGzjl.setEnabled(is);
        etJx.setEnabled(is);
        etJyyx.setEnabled(is);
        etMajor.setEnabled(is);
        etProvince.setEnabled(is);
        etSchool.setEnabled(is);
        etSex.setEnabled(is);
        etXl.setEnabled(is);
        etTel.setEnabled(is);
        etYx.setEnabled(is);
        addPhoto.setEnabled(is);
        btSave.setEnabled(is);
        if (is) {
            btEdit.setText("取消编辑");
        } else {
            btEdit.setText("编辑");
        }
    }

    private void initView() {
        appClient = (AppClient) getActivity().getApplication();
        btEdit = getView().findViewById(R.id.bt_edit);
        etName = getView().findViewById(R.id.et_name);
        etProvince = getView().findViewById(R.id.et_province);
        etSex = getView().findViewById(R.id.et_sex);
        imagePhoto = getView().findViewById(R.id.image_photo);
        addPhoto = getView().findViewById(R.id.add_photo);
        etTel = getView().findViewById(R.id.et_tel);
        etBirth = getView().findViewById(R.id.et_birth);
        etMajor = getView().findViewById(R.id.et_major);
        etSchool = getView().findViewById(R.id.et_school);
        etXl = getView().findViewById(R.id.et_xl);
        etGzjl = getView().findViewById(R.id.et_gzjl);
        etJyyx = getView().findViewById(R.id.et_jyyx);
        etJx = getView().findViewById(R.id.et_jx);
        etYx = getView().findViewById(R.id.et_yx);
        btSave = getView().findViewById(R.id.bt_save);
    }
}

