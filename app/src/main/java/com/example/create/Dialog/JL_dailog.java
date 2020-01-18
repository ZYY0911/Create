package com.example.create.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.create.R;
import com.example.create.bean.JBXX;
import com.example.create.bean.Q_YHZC_SQL;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class JL_dailog extends DialogFragment {
    @BindView(R.id.tv_xm)
    TextView Xmtv;
    @BindView(R.id.tv_xb)
    TextView Xbtv;
    @BindView(R.id.tv_zy)
    TextView Zytv;
    @BindView(R.id.tv_byyx)
    TextView Byyxtv;
    @BindView(R.id.tv_xl)
    TextView Xltv;
    @BindView(R.id.tv_gzjl)
    TextView Gzjltv;
    @BindView(R.id.iv_tx)
    ImageView Txiv;
    @BindView(R.id.tv_csny)
    TextView Csnytv;
    @BindView(R.id.tv_hj)
    TextView Hjtv;
    @BindView(R.id.tv_yj)
    TextView Yjtv;
    @BindView(R.id.tv_dh)
    TextView Dhtv;
    @BindView(R.id.tv_tcah)
    TextView Tcahtv;
    @BindView(R.id.tv_jg)
    TextView Jgtv;
    @BindView(R.id.bt_xz)
    Button Xzbt;
    private View view;
    private Unbinder mUnbinder;
    private JBXX jbxx;

    public JL_dailog(JBXX jbxx) {
        this.jbxx = jbxx;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.jl_item, null);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setSQL();
    }

    private void setSQL() {
        Xmtv.setText(jbxx.getName());
        Xbtv.setText(jbxx.getSex());
        Zytv.setText(jbxx.getMajor());
        Byyxtv.setText(jbxx.getSchool());
        Xltv.setText(jbxx.getXl());
        Gzjltv.setText(jbxx.getGzjl());
        Csnytv.setText(jbxx.getBirth());
        Hjtv.setText(jbxx.getJx());
        Yjtv.setText(jbxx.getYx());
        Dhtv.setText(jbxx.getTel());
        Glide.with(this).load(jbxx.getPhoto()).into(Txiv);
        Tcahtv.setText("篮球");
        Jgtv.setText(jbxx.getProvince());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
