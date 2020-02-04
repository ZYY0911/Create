package com.example.create.fragment3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.create.R;
import com.example.create.activity3.Z_KCXQActivity;
import com.example.create.adapter3.YLAdapter;
import com.example.create.bean3.RK;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-02-02
 */
@SuppressLint("ValidFragment")
public class Z_KCFragment extends Fragment {
    @BindView(R.id.yl_list)
    ListView ylList;
    Unbinder unbinder;
    private Context context;
    private List<RK> rks;


    public Z_KCFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kc_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initClick();
    }

    private void initClick() {
        ylList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Z_KCXQActivity.class);
                intent.putExtra("data",rks.get(position));
                getActivity().startActivity(intent);
            }
        });
    }

    private void initData() {
        rks = LitePal.where("num>?", "0").find(RK.class);
        YLAdapter adapter = new YLAdapter(getContext(), R.layout.kc_item, rks);
        ylList.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
