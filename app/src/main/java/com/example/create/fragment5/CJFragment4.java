package com.example.create.fragment5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.create.AppClient;
import com.example.create.R;
import com.example.create.activity5.Z_SCXActivity;
import com.example.create.adapter5.CJFragmentAdapter;
import com.example.create.bean5.CJXX;
import com.example.create.bean5.SCX;

import org.litepal.LitePal;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-02-05 ：）
 */
@SuppressLint("ValidFragment")
public class CJFragment4 extends Fragment {
    @BindView(R.id.layout_light)
    LinearLayout layoutLight;
    @BindView(R.id.layout_air)
    LinearLayout layoutAir;
    Unbinder unbinder;
    @BindView(R.id.image_light)
    ImageView imageLight;
    @BindView(R.id.image_air)
    ImageView imageAir;
    @BindView(R.id.air_add)
    ImageView airAdd;
    @BindView(R.id.air_wd)
    TextView airWd;
    @BindView(R.id.air_remove)
    ImageView airRemove;
    @BindView(R.id.tv_gz)
    TextView tvGz;
    @BindView(R.id.tv_wd)
    TextView tvWd;
    @BindView(R.id.tv_dl)
    TextView tvDl;
    @BindView(R.id.tv_dlxh)
    TextView tvDlxh;
    @BindView(R.id.tv_ylrl)
    TextView tvYlrl;
    @BindView(R.id.tv_qcrl)
    TextView tvQcrl;
    @BindView(R.id.grid_view)
    GridView gridView;
    @BindView(R.id.bt_add)
    Button btAdd;
    @BindView(R.id.add_layout)
    LinearLayout addLayout;
    private AppClient appClient;
    private CJXX cjxxe;
    private List<SCX> scxes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cj_fragment1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }
    @Override
    public void onResume() {
        super.onResume();
        initVolley();
    }

    private void initVolley() {
        scxes = LitePal.where("cj=?", "4").find(SCX.class);
        if (scxes.size() == 0) {
            addLayout.setVisibility(View.VISIBLE);
        } else {
            addLayout.setVisibility(View.GONE);
        }
        initClick();
    }

    private void initClick() {
        CJFragmentAdapter adapter = new CJFragmentAdapter(getContext(), 0, scxes);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Z_SCXActivity.class);
                intent.putExtra("lx", 1);
                intent.putExtra("cj", 4);
                intent.putExtra("scx", scxes.get(position).getScx());
                startActivity(intent);
               /* AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("生产线"+(position+1));
                SCX scx = scxes.get(position);
                builder.setMessage("当前生产环节"+scx.getHj()+"\n生产状态"+scx.getState()
                +"\n剩余天数"+scx.getTs());
                builder.setPositiveButton("确定",null);
                builder.show();*/
            }
        });
    }

    private void initData() {
        cjxxe = LitePal.where("num=?", "4").find(CJXX.class).get(0);
        tvGz.setText(cjxxe.getGz() + "");
        tvWd.setText(cjxxe.getWd() + "");
        tvDl.setText(cjxxe.getDl() + "");
        tvYlrl.setText(cjxxe.getYlrl() + "");
        tvQcrl.setText(cjxxe.getQcrl() + "");
        Random random = new Random();
        tvDlxh.setText(random.nextInt(cjxxe.getDl()) + "");
    }

    private void initView() {
        appClient = (AppClient) getActivity().getApplication();
        if (appClient.getLight_1()) {
            imageLight.setImageResource(R.mipmap.icon_light_open);
        } else {
            imageLight.setImageResource(R.mipmap.icon_light_close);
        }
        if (appClient.getAir_1()) {
            imageAir.setImageResource(R.mipmap.icon_air_open);
            airWd.setTextColor(Color.RED);
            airAdd.setEnabled(true);
            airRemove.setEnabled(true);
        } else {
            imageAir.setImageResource(R.mipmap.icon_air_close);
            airWd.setTextColor(Color.BLACK);
            airAdd.setEnabled(false);
            airRemove.setEnabled(false);
        }
        airWd.setText(appClient.getWd_1() + "˚C");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.layout_light, R.id.layout_air, R.id.air_add, R.id.air_remove, R.id.bt_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_light:
                if (appClient.getLight_1()) {
                    appClient.setLight_1(false);
                    imageLight.setImageResource(R.mipmap.icon_light_close);
                } else {
                    appClient.setLight_1(true);
                    imageLight.setImageResource(R.mipmap.icon_light_open);
                }
                break;
            case R.id.layout_air:
                if (appClient.getAir_1()) {
                    appClient.setAir_1(false);
                    imageAir.setImageResource(R.mipmap.icon_air_close);
                    airWd.setTextColor(Color.BLACK);
                    airAdd.setEnabled(false);
                    airRemove.setEnabled(false);
                } else {

                    appClient.setAir_1(true);
                    imageAir.setImageResource(R.mipmap.icon_air_open);
                    airWd.setTextColor(Color.RED);
                    airAdd.setEnabled(true);
                    airRemove.setEnabled(true);
                }
                break;
            case R.id.air_add:
                int wd = appClient.getWd_1();
                wd++;
                airWd.setText(wd + "˚C");
                appClient.setWd_1(wd);
                break;
            case R.id.air_remove:
                int wd1 = appClient.getWd_1();
                wd1--;
                if (wd1 < -10) {
                    wd1 = -10;
                }
                airWd.setText(wd1 + "˚C");
                appClient.setWd_1(wd1);

                break;
            case R.id.bt_add:
                Intent intent = new Intent(getActivity(), Z_SCXActivity.class);
                intent.putExtra("lx", 2);
                intent.putExtra("cj", 4);
                startActivity(intent);
                break;
        }
    }
}
