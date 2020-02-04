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
import android.widget.Button;
import android.widget.ListView;

import com.example.create.R;
import com.example.create.activity3.Z_CKCXActivity;
import com.example.create.activity3.Z_YLGLCKActivity;
import com.example.create.adapter3.RKCXAdapter1;
import com.example.create.bean3.RK;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-02-02
 */
@SuppressLint("ValidFragment")
public class Z_CKXXFragment extends Fragment {
    @BindView(R.id.list_view)
    ListView listView;
    Unbinder unbinder;
    @BindView(R.id.find_history)
    Button findHistory;
    private Context context;
    private List<RK> rks;

    public Z_CKXXFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ckxx_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rks = LitePal.where("num>?", "0").find(RK.class);
        final RKCXAdapter1 adapter1 = new RKCXAdapter1(getContext(), R.layout.ckjl_item, rks);
        listView.setAdapter(adapter1);
        adapter1.setData(new RKCXAdapter1.SetData() {
            @Override
            public void setdata(int position, int lx, boolean sc) {
                switch (lx) {
                    case 1:
                        adapter1.setIndex(position);
                        break;
                    case 2:
                        Intent intent = new Intent(context, Z_YLGLCKActivity.class);
                        intent.putExtra("data", rks.get(position));
                        getActivity().startActivity(intent);
                        break;
                }
                adapter1.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.find_history)
    public void onViewClicked() {
        startActivity(new Intent(context, Z_CKCXActivity.class));
    }
}
