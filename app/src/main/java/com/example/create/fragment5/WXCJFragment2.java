package com.example.create.fragment5;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.adapter5.CLWXAdapter;
import com.example.create.bean5.WXCL;
import com.example.create.bean5.WXCLBean;
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
 * Create by 张瀛煜 on 2020-02-06 ：）
 */
public class WXCJFragment2 extends Fragment {


    @BindView(R.id.spinner_wx)
    Spinner spinnerWx;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.bt_exit)
    Button btExit;
    @BindView(R.id.end_layout)
    LinearLayout endLayout;
    Unbinder unbinder;
    @BindView(R.id.my_list)
    ListView myList;
    @BindView(R.id.text_wx)
    TextView textWx;
    private List<WXCL> wxcls;
    private List<WXCLBean> wxclBeans;
    private CLWXAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wxcj_fragment2, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData("全部", false);
        initLis();
    }

    private void initLis() {
        spinnerWx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = spinnerWx.getSelectedItem().toString();
                if (name.equals("未修")) {
                    initData(name, true);
                    textWx.setVisibility(View.VISIBLE);
                    endLayout.setVisibility(View.VISIBLE);
                } else {
                    initData(name, false);
                    textWx.setVisibility(View.GONE);
                    endLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initData(String type, boolean wx) {
        wxcls = LitePal.findAll(WXCL.class);
        wxclBeans = new ArrayList<>();
        switch (type) {
            case "全部":
                for (int i = 0; i < wxcls.size(); i++) {
                    WXCL wxcl = wxcls.get(i);
                    WXCLBean wxclBean = new WXCLBean(wxcl.getId(), wxcl.getClnum(), wxcl.getClxh(), wxcl.getQuestion()
                            , wxcl.getState(), wxcl.getTime(), wxcl.getWxtime());
                    wxclBean.setIs(false);
                    wxclBeans.add(wxclBean);
                }
                break;
            case "已修":
                for (int i = 0; i < wxcls.size(); i++) {
                    WXCL wxcl = wxcls.get(i);
                    if (wxcl.getState().equals("已修")) {
                        WXCLBean wxclBean = new WXCLBean(wxcl.getId(), wxcl.getClnum(), wxcl.getClxh(), wxcl.getQuestion()
                                , wxcl.getState(), wxcl.getTime(), wxcl.getWxtime());
                        wxclBean.setIs(false);
                        wxclBeans.add(wxclBean);
                    }
                }
                break;
            case "未修":
                for (int i = 0; i < wxcls.size(); i++) {
                    WXCL wxcl = wxcls.get(i);
                    if (wxcl.getState().equals("未修")) {
                        WXCLBean wxclBean = new WXCLBean(wxcl.getId(), wxcl.getClnum(), wxcl.getClxh(), wxcl.getQuestion()
                                , wxcl.getState(), wxcl.getTime(), wxcl.getWxtime());
                        wxclBean.setIs(false);
                        wxclBeans.add(wxclBean);
                    }
                }
                break;
        }
        adapter = new CLWXAdapter(getContext(), 0, wxclBeans);
        myList.setAdapter(adapter);
        adapter.setClick(new CLWXAdapter.Click() {
            @Override
            public void MyClick(int position, boolean xz) {
                WXCLBean wxclBean = wxclBeans.get(position);
                if (xz) {
                    wxclBean.setIs(true);
                } else {
                    wxclBean.setIs(false);
                }
                wxclBeans.set(position, wxclBean);
            }
        });
        adapter.setIs(wx);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_submit, R.id.bt_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                for (int i = 0; i < wxclBeans.size(); i++) {
                    WXCLBean wxclBean = wxclBeans.get(i);
                    if (wxclBean.isIs()) {
                        WXCL wxcl = new WXCL();
                        wxcl.setState("已修");
                        wxcl.setWxtime(SimpData.Simp("yyyy-MM-dd", new Date()));
                        wxcl.updateAll("id=?",wxclBean.getId()+"");
                    }
                }
                String name = spinnerWx.getSelectedItem().toString();
                if (name.equals("未修")) {
                    initData(name, true);
                    textWx.setVisibility(View.VISIBLE);
                    endLayout.setVisibility(View.VISIBLE);
                } else {
                    initData(name, false);
                    textWx.setVisibility(View.GONE);
                    endLayout.setVisibility(View.GONE);
                }
                break;
            case R.id.bt_exit:
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
