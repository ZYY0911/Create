package com.example.create.fragment2;

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
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.create.R;
import com.example.create.adapter2.GHCF_LeftAdapter2;
import com.example.create.adapter2.GHCX_LeftAdapter1;
import com.example.create.bean2.GSCP;
import com.example.create.bean2.JYSJ;
import com.example.create.bean3.CK;
import com.example.create.bean3.RK;

import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-01-28
 */
public class GHCX_Fragemnt1 extends Fragment {
    @BindView(R.id.left_list_view)
    ListView leftListView;
    @BindView(R.id.left_ghcs)
    TextView leftGhcs;
    @BindView(R.id.left_je)
    TextView leftJe;
    @BindView(R.id.left_text_start)
    TextView leftTextStart;
    @BindView(R.id.left_layout_start)
    LinearLayout leftLayoutStart;
    @BindView(R.id.left_text_end)
    TextView leftTextEnd;
    @BindView(R.id.left_layout_end)
    LinearLayout leftLayoutEnd;
    @BindView(R.id.left_clear)
    Button leftClear;
    @BindView(R.id.left_list_view2)
    ListView leftListView2;
    @BindView(R.id.left_)
    LinearLayout left;
    Unbinder unbinder;
    @BindView(R.id.left_jy_text)
    TextView leftJyText;
    private List<GSCP> gscps;
    private TimePickerView timePickerView;
    private List<JYSJ> jysjs;
    private List<RK> cks;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ghcx_fragment1, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLeft();
        initRight(gscps.get(0).getName());
        initClick();
    }

    private void initClick() {
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                initRight(gscps.get(position).getName());
            }
        });
    }

    private void initRight(String name) {
        cks = LitePal.where("ylmc=? and num>?", name,"0").find(RK.class);
        Collections.sort(cks, new Comparator<RK>() {
            @Override
            public int compare(RK o1, RK o2) {
                return o2.getTime().compareTo(o1.getTime());
            }
        });
        setTieiel(cks);
        if (cks.size() == 0) {
            leftJyText.setVisibility(View.VISIBLE);
            leftListView2.setVisibility(View.GONE);
        } else {
            leftJyText.setVisibility(View.GONE);
            leftListView2.setVisibility(View.VISIBLE);
            GHCF_LeftAdapter2 adapter2 = new GHCF_LeftAdapter2(getContext(), R.layout.ghcx_item2, cks);
            leftListView2.setAdapter(adapter2);
        }
    }

  /*  private void setTieiel(List<JYSJ> jysjs2) {
        leftGhcs.setText(jysjs2.size() + "次");
        int money = 0;
        for (int i = 0; i < jysjs2.size(); i++) {
            money += jysjs2.get(i).getMoney();
        }
        leftJe.setText(money + "元");
    }*/

    private void setTieiel(List<RK> cks) {
        leftGhcs.setText(cks.size() + "次");
        int money = 0;
        for (int i = 0; i < cks.size(); i++) {
            money += cks.get(i).getPrice()*cks.get(i).getNum();
        }
        leftJe.setText(money + "元");
    }


    private void showTime(final TextView textView) {
        timePickerView = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format
                        = new SimpleDateFormat("yyyy-MM-dd");
                textView.setText(format.format(date));
                if (leftTextStart.getText().length() != 0 && leftTextEnd.getText().length() != 0) {
                    List<RK> jysj = new ArrayList<>();
                    for (int i = 0; i < cks.size(); i++) {
                        RK jysj1 = cks.get(i);
                        try {
                            Date start = format.parse(leftTextStart.getText().toString());
                            Date end = format.parse(leftTextEnd.getText().toString());
                            Date date1 = format.parse(jysj1.getTime());
                            if (date1.equals(start)) {
                                jysj.add(jysj1);
                            }
                            if (date1.after(start) && date1.before(end)) {
                                jysj.add(jysj1);
                            }
                            if (date1.equals(end)) {
                                jysj.add(jysj1);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    GHCF_LeftAdapter2 adapter2 = new GHCF_LeftAdapter2(getContext(), R.layout.ghcx_item2, jysj);
                    leftListView2.setAdapter(adapter2);
                    setTieiel(jysj);
                }
            }
        }).isDialog(true).build();
        timePickerView.show();
    }

    private void initLeft() {
        gscps = LitePal.findAll(GSCP.class);
        GHCX_LeftAdapter1 adapter1 = new GHCX_LeftAdapter1(getActivity(), R.layout.ghcx_item1, gscps);
        leftListView.setAdapter(adapter1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.left_layout_start, R.id.left_layout_end, R.id.left_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_layout_start:
                showTime(leftTextStart);
                break;
            case R.id.left_layout_end:
                showTime(leftTextEnd);
                break;
            case R.id.left_clear:
                leftTextEnd.setText("");
                leftTextStart.setText("");
                initRight(gscps.get(0).getName());
                break;
        }
    }
}
