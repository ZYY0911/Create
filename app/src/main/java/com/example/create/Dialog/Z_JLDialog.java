package com.example.create.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.example.create.AppClient;
import com.example.create.R;
import com.example.create.adapter.JLLBAdapter;
import com.example.create.adapter.JLLBAdapter2;
import com.example.create.bean.JLFS;
import com.example.create.bean.JLLB;
import com.example.create.util.SimpData;

import org.litepal.LitePal;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-01-17
 */
@SuppressLint("ValidFragment")
public class Z_JLDialog extends DialogFragment {
    @BindView(R.id.list_view)
    ListView listView;
    Unbinder unbinder;
    private List<JLLB> jllbs;
    private JLLBAdapter2 adapter;
    private int gs;
    public Z_JLDialog (int gs){
        this. gs = gs;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.jllb_dialogfragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        jllbs = LitePal.where("user=?",AppClient.getName()).find(JLLB.class);
        Log.i("jjjj", "initData: " + jllbs.size());
        Collections.sort(jllbs, new Comparator<JLLB>() {
            @Override
            public int compare(JLLB o1, JLLB o2) {
                return o2.getJlTime() .compareTo(o1.getJlTime());
            }
        });
        adapter = new JLLBAdapter2(getContext(), R.layout.jllb_item2, jllbs);
        listView.setAdapter(adapter);
        adapter.setMyClick(new JLLBAdapter2.MyClick() {
            @Override
            public void Click(int position) {
                JLLB jllb = jllbs.get(position);
                JLFS jlfs = new JLFS();
                jlfs.setJlFile(jllb.getJlFile());
                jlfs.setJlInfo(jllb.getJlInfo());
                jlfs.setJlName(jllb.getJlName());
                jlfs.setJlTime(jllb.getJlTime());
                jlfs.setSendTime(SimpData.Simp("yyyy-MM-dd HH:mm",new Date()));
                jlfs.setGsId(gs);
                jlfs.setUser(jllb.getUser());
                jlfs.save();
                Toast.makeText(getContext(), "投递成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
