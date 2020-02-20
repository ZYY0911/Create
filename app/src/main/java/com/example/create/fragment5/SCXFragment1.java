package com.example.create.fragment5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.adapter5.SCX_Adapter;
import com.example.create.bean5.SCX;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-02-07 ：）
 */
@SuppressLint("ValidFragment")
public class SCXFragment1 extends Fragment {
    @BindView(R.id.tv_syts)
    TextView tvSyts;
    @BindView(R.id.scx_listview)
    ListView scxListview;
    Unbinder unbinder;
    private int cj, ts;
    private List<SCX> scxes;
    private String scx;


    public SCXFragment1(int cj, String scx) {
        this.cj = cj;
        this.scx = scx;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scx_fragment1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if ("".equals(scx)) {
            scxes = LitePal.where("cj=?", cj + "").find(SCX.class);
        } else {
            scxes = LitePal.where("cj=? and scx=?", cj + "", scx).find(SCX.class);
        }
        for (int i = 0; i < scxes.size(); i++) {
            ts += Integer.parseInt(scxes.get(i).getTs());
        }
        tvSyts.setText(ts + "天");
        scxListview.setAdapter(new SCX_Adapter(getContext(),1,scxes));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
