package com.example.create.fragment3;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.create.AppClient;
import com.example.create.R;
import com.example.create.activity3.Z_RKCXActivity;
import com.example.create.bean2.GSCP;
import com.example.create.bean2.GYS;
import com.example.create.bean3.RK;
import com.example.create.util.ShowDialog;
import com.example.create.util.SimpData;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-02-02
 */
@SuppressLint("ValidFragment")
public class Z_RKXXFragment extends Fragment {
    @BindView(R.id.find_history)
    Button findHistory;
    @BindView(R.id.et_ylmc)
    Spinner etYlmc;
    @BindView(R.id.et_gys)
    Spinner etGys;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_kcwz)
    Spinner etKcwz;
    @BindView(R.id.et_cgy)
    EditText etCgy;
    @BindView(R.id.et_lxr)
    EditText etLxr;
    @BindView(R.id.et_dfzh)
    EditText etDfzh;
    @BindView(R.id.et_fp)
    EditText etFp;
    @BindView(R.id.bt_load)
    Button btLoad;
    Unbinder unbinder;
    @BindView(R.id.bt_save)
    Button btSave;
    private Context context;

    public void setTest(String url) {
        etFp.setText(url);
    }

    public Z_RKXXFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ylrk_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        List<String> strings = new ArrayList<>();
        List<GSCP> gscps = LitePal.findAll(GSCP.class);
        for (int i = 0; i < gscps.size(); i++) {
            GSCP gscp = gscps.get(i);
            strings.add(gscp.getName() + "型号" + gscp.getXh());
        }
        etYlmc.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, strings));
        List<GYS> gys = LitePal.findAll(GYS.class);
        List<String> strings1 = new ArrayList<>();
        for (int i = 0; i < gys.size(); i++) {
            strings1.add(gys.get(i).getGysName());
        }
        etGys.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, strings1));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.find_history, R.id.bt_load})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.find_history:
                getActivity().startActivity(new Intent(context, Z_RKCXActivity.class));
                break;
            case R.id.bt_load:
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                getActivity().startActivityForResult(intent, 2);
                break;
        }
    }

    private void clearAll() {
        etNum.setText("");
        etPrice.setText("");
        etKcwz.setSelection(0);
        etYlmc.setSelection(0);
        etGys.setSelection(0);
        etCgy.setText("");
        etLxr.setText("");
        etDfzh.setText("");
        etFp.setText("");
    }

    @OnClick(R.id.bt_save)
    public void onViewClicked() {
        if (etNum.getText().toString().equals("")) {
            ShowDialog.Show("请输入数量", context);
            return;
        }
        if (etPrice.getText().toString().equals("")) {
            ShowDialog.Show("请输入单价", context);
            return;
        }
        if (etCgy.getText().toString().equals("")) {
            ShowDialog.Show("请输采购员", context);
            return;
        }
        if (etLxr.getText().toString().equals("")) {
            ShowDialog.Show("请输联系人", context);
            return;
        }
        if (etDfzh.getText().toString().equals("")) {
            ShowDialog.Show("请输对方账号", context);
            return;
        }
        final RK rk = new RK();
        rk.setYlmc(etYlmc.getSelectedItem().toString().split("型号")[0]);
        rk.setXh(etYlmc.getSelectedItem().toString().split("型号")[1]);
        rk.setGys(etGys.getSelectedItem().toString());
        rk.setNum(Integer.parseInt(etNum.getText().toString()));
        rk.setPrice(Integer.parseInt(etPrice.getText().toString()));
        rk.setLocation(etKcwz.getSelectedItem().toString());
        rk.setShop(etCgy.getText().toString());
        rk.setContacts(etLxr.getText().toString());
        rk.setCountId(etDfzh.getText().toString());
        rk.setKrname(AppClient.getName());
        rk.setTime(SimpData.Simp("yyyy-MM-dd", new Date()));
        rk.setFp(etFp.getText().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("是否保存");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rk.save();
                dialog.dismiss();
                ShowDialog.Show("保存成功", context);
                clearAll();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearAll();
            }
        });
        builder.show();
    }
}
