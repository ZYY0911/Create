package com.example.create.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.create.AppClient;
import com.example.create.R;
import com.example.create.adapter.JLLBAdapter;
import com.example.create.bean.JBXX;
import com.example.create.bean.JLLB;
import com.example.create.dialog.Z_SCJLDialog;

import org.litepal.LitePal;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Create by 张瀛煜 on 2020-01-15
 */
@SuppressLint("ValidFragment")
public class Z_JLLBFragment extends Fragment {
    private ListView listView;
    private ImageView addJl;
    private Z_SCJLDialog dialog;
    private List<JLLB> jllbs;
    private AppClient appClient;
    private JLLBAdapter adapter;

    public Z_JLLBFragment(Z_SCJLDialog dialog) {
        this.dialog = dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.jllb_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initClick();
        initData();

    }

    private void initData() {
        jllbs = LitePal.where("user=?", String.valueOf(appClient.getName())).find(JLLB.class);
        Log.i("jjjj", "initData: " + jllbs.size());
        Collections.sort(jllbs, new Comparator<JLLB>() {
            @Override
            public int compare(JLLB o1, JLLB o2) {
                return o2.getJlTime() .compareTo(o1.getJlTime());
            }
        });
        adapter = new JLLBAdapter(getContext(), R.layout.jllb_item, jllbs);
        listView.setAdapter(adapter);
    }

    private void initClick() {
        addJl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show(getChildFragmentManager(), "aaa");
                dialog.setFlash(new Z_SCJLDialog.Flash() {
                    @Override
                    public void myFlash() {
                        initData();
                    }
                });
            }
        });
    }

    private void initView() {
        appClient = (AppClient) getActivity().getApplication();
        listView = getView().findViewById(R.id.list_view);
        addJl = getView().findViewById(R.id.add_jl);
    }
}

