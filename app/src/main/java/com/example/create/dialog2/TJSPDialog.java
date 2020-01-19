package com.example.create.dialog2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.create.R;
import com.example.create.activity.Z_TBXXActivity;
import com.example.create.activity2.Z_TJGYSActivity;
import com.example.create.bean2.GYSP;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-01-19
 */
@SuppressLint("ValidFragment")
public class TJSPDialog extends DialogFragment {
    @BindView(R.id.et_gys_num)
    EditText etGysNum;
    @BindView(R.id.et_gysp_name)
    EditText etGyspName;
    @BindView(R.id.et_gysp_num)
    EditText etGyspNum;
    @BindView(R.id.et_gysp_price)
    EditText etGyspPrice;
    @BindView(R.id.add_photo)
    Button addPhoto;
    @BindView(R.id.bt_save)
    Button btSave;
    @BindView(R.id.bt_exit)
    Button btExit;
    Unbinder unbinder;
    private Context context;
    public ImageView imagePhoto;
    private int gsId;
    public String imagURl;


    public TJSPDialog(Context context, int gsId) {
        this.context = context;
        this.gsId = gsId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.tjsp_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imagePhoto = getView().findViewById(R.id.image_photo);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.add_photo, R.id.bt_save, R.id.bt_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_photo:
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
                break;
            case R.id.bt_save:
                GYSP gysp = new GYSP();
                gysp.setGysNum(gsId);
                gysp.setYlName(etGyspName.getText().toString().trim());
                gysp.setYlNum(etGyspNum.getText().toString().trim());
                gysp.setYlPhoto(imagURl);
                gysp.setYlPrice(etGyspPrice.getText().toString().trim());
                gysp.save();
                Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
                getActivity().startActivityForResult(new Intent(), 1);
                getDialog().dismiss();
                break;
            case R.id.bt_exit:
                getDialog().dismiss();
                break;
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        getActivity().startActivityForResult(intent, 3);
    }
}
