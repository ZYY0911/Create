package com.example.create.activity3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.adapter2.SelectAdapter;
import com.example.create.adapter3.CKCXAdapter;
import com.example.create.adapter3.RKCXAdapter1;
import com.example.create.bean3.RK;
import com.example.create.dialog3.YearDialog;

import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-02-02
 */
public class Z_RKCXActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.left_text_start)
    TextView leftTextStart;
    @BindView(R.id.left_layout_start)
    LinearLayout leftLayoutStart;
    @BindView(R.id.left_text_end)
    TextView leftTextEnd;
    @BindView(R.id.left_layout_end)
    LinearLayout leftLayoutEnd;
    @BindView(R.id.bt_clear)
    Button btClear;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.expand_list)
    ExpandableListView expandList;
    @BindView(R.id.list_view2)
    ListView listView2;
    private List<String> strings;
    private List<String> list;
    private Map<String, List<RK>> map;
    private SelectAdapter adapter;
    private int lx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rkcx_layout);
        ButterKnife.bind(this);
        initView();
        listClick(0, LitePal.findAll(RK.class));
        initClick();
    }

    private void initClick() {

    }

    private void initView() {
        title.setText("原料库存管理--入库查询");
        strings = new ArrayList<>();
        strings.add("全部记录");
        strings.add("按原料查询");
        strings.add("按入库人查询");
        strings.add("按供应商查询");
        adapter = new SelectAdapter(this, R.layout.gyscx_left_item, strings);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setIndex(position);
                listView.smoothScrollToPositionFromTop(position - 1, 0, 500);
                listClick(position, LitePal.findAll(RK.class));
                leftTextEnd.setText("");
                leftTextStart.setText("");
                adapter.notifyDataSetChanged();
            }
        });
    }


    private void setAdapterList() {
        List<RK> rks = LitePal.findAll(RK.class);
        List<RK> rks1 = new ArrayList<>();
        SimpleDateFormat format
                = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < rks.size(); i++) {
            RK rk = rks.get(i);
            try {
                Date start = format.parse(leftTextStart.getText().toString());
                Date end = format.parse(leftTextEnd.getText().toString());
                Date date1 = format.parse(rk.getTime());
                if (date1.after(start) && date1.before(end)) {
                    rks1.add(rk);
                }
                if (date1.equals(start) || date1.equals(end)) {
                    rks1.add(rk);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        listClick(adapter.getIndex(), rks1);
    }


    private void listClick(int position, final List<RK> rks) {
        list = new ArrayList<>();
        map = new HashMap<>();
        switch (position) {
            case 0:
                listView2.setAdapter(new CKCXAdapter(this, R.layout.rkjl_item1, rks));
                listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) {
                        } else {
                            Intent intent = new Intent(Z_RKCXActivity.this, Z_KCXQActivity.class);
                            intent.putExtra("data", rks.get(position - 1));
                            startActivity(intent);
                        }
                    }
                });
                break;
            case 1:
                lx = 1;
                for (int i = 0; i < rks.size(); i++) {
                    RK rk = rks.get(i);
                    String name = rk.getYlmc();
                    list.add(name);
                    for (int k = 0; k < list.size(); k++) {
                        for (int j = list.size() - 1; j > k; j--) {
                            if (list.get(k).equals(list.get(j))) {
                                list.remove(j);
                            }
                        }
                    }
                    List<RK> rks1 = map.get(name);
                    if (rks1 == null) {
                        rks1 = new ArrayList<>();
                        rks1.add(rk);
                    } else {
                        rks1.add(rk);
                    }
                    map.put(name, rks1);
                }
                expandList.setAdapter(new ExpandListAdapter());
                break;
            case 2:
                lx = 2;
                for (int i = 0; i < rks.size(); i++) {
                    RK rk = rks.get(i);
                    String name = rk.getKrname();
                    list.add(name);
                    for (int k = 0; k < list.size(); k++) {
                        for (int j = list.size() - 1; j > k; j--) {
                            if (list.get(k).equals(list.get(j))) {
                                list.remove(j);
                            }
                        }
                    }
                    List<RK> rks1 = map.get(name);
                    if (rks1 == null) {
                        rks1 = new ArrayList<>();
                        rks1.add(rk);
                    } else {
                        rks1.add(rk);
                    }
                    map.put(name, rks1);
                }
                expandList.setAdapter(new ExpandListAdapter());
                break;
            case 3:
                lx = 3;
                for (int i = 0; i < rks.size(); i++) {
                    RK rk = rks.get(i);
                    String name = rk.getGys();
                    list.add(name);
                    for (int k = 0; k < list.size(); k++) {
                        for (int j = list.size() - 1; j > k; j--) {
                            if (list.get(k).equals(list.get(j))) {
                                list.remove(j);
                            }
                        }
                    }
                    List<RK> rks1 = map.get(name);
                    if (rks1 == null) {
                        rks1 = new ArrayList<>();
                        rks1.add(rk);
                    } else {
                        rks1.add(rk);
                    }
                    map.put(name, rks1);
                }
                expandList.setAdapter(new ExpandListAdapter());
                break;
        }
        if (position == 0) {
            listView2.setVisibility(View.VISIBLE);
            expandList.setVisibility(View.GONE);
        } else {
            listView2.setVisibility(View.GONE);
            expandList.setVisibility(View.VISIBLE);
        }
        expandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (childPosition == 0) {

                } else {
                    Intent intent = new Intent(Z_RKCXActivity.this, Z_KCXQActivity.class);
                    intent.putExtra("data", map.get(list.get(groupPosition)).get(childPosition - 1));
                    startActivity(intent);
                }
                return true;
            }
        });
    }

    class ExpandListAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return map.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            if (map.get(list.get(groupPosition)).size() == 0) {
                Log.i("aa", "getChildrenCount: " + 0);
                return 0;
            } else {
                Log.i("aa", "getChildrenCount: a" + map.get(list.get(groupPosition)).size() + 1);
                return map.get(list.get(groupPosition)).size() + 1;
            }
        }

        @Override
        public Object getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(Z_RKCXActivity.this).inflate(R.layout.textview_item, parent, false);
            TextView textView = view.findViewById(R.id.fu_item);
            textView.setText(list.get(groupPosition));
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            RK rk = null;
            View view = null;
            switch (lx) {
                case 1:
                    view = LayoutInflater.from(Z_RKCXActivity.this).inflate(R.layout.rkjl_item1_1, parent, false);
                    if (childPosition == 0) {

                    } else {
                        rk = map.get(list.get(groupPosition)).get(childPosition - 1);
                        TextView itemXh = view.findViewById(R.id.item_xh);
                        TextView itemSj = view.findViewById(R.id.item_sj);
                        TextView itemSl = view.findViewById(R.id.item_sl);
                        TextView itemGys = view.findViewById(R.id.item_gys);
                        TextView itemRkr = view.findViewById(R.id.item_rkr);
                        itemGys.setText(rk.getGys());
                        itemRkr.setText(rk.getKrname());
                        itemSj.setText(rk.getTime());
                        itemSl.setText(rk.getNum() + "");
                        itemXh.setText(rk.getXh());
                    }
                    break;
                case 2:
                    view = LayoutInflater.from(Z_RKCXActivity.this).inflate(R.layout.rkjl_item1_2, parent, false);
                    if (childPosition == 0) {

                    } else {
                        rk = map.get(list.get(groupPosition)).get(childPosition - 1);
                        TextView itemYl = view.findViewById(R.id.item_yl);
                        TextView itemXh = view.findViewById(R.id.item_xh);
                        TextView itemSj = view.findViewById(R.id.item_sj);
                        TextView itemSl = view.findViewById(R.id.item_sl);
                        TextView itemGys = view.findViewById(R.id.item_gys);
                        itemGys.setText(rk.getGys());
                        itemSj.setText(rk.getTime());
                        itemSl.setText(rk.getNum() + "");
                        itemXh.setText(rk.getXh());
                        itemYl.setText(rk.getYlmc());
                    }
                    break;
                case 3:
                    view = LayoutInflater.from(Z_RKCXActivity.this).inflate(R.layout.rkjl_item1_3, parent, false);
                    if (childPosition == 0) {

                    } else {
                        rk = map.get(list.get(groupPosition)).get(childPosition - 1);
                        TextView itemYl = view.findViewById(R.id.item_yl);
                        TextView itemXh = view.findViewById(R.id.item_xh);
                        TextView itemSj = view.findViewById(R.id.item_sj);
                        TextView itemSl = view.findViewById(R.id.item_sl);
                        TextView itemRkr = view.findViewById(R.id.item_rkr);
                        itemSj.setText(rk.getTime());
                        itemRkr.setText(rk.getKrname());
                        itemSl.setText(rk.getNum() + "");
                        itemXh.setText(rk.getXh());
                        itemYl.setText(rk.getYlmc());
                    }
                    break;
            }
            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    @OnClick({R.id.change, R.id.bt_clear, R.id.left_layout_start, R.id.left_layout_end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.bt_clear:
                leftTextEnd.setText("");
                leftTextStart.setText("");
                break;
            case R.id.left_layout_start:
                YearDialog dialog = new YearDialog();
                dialog.setData(new YearDialog.getDATA() {
                    @Override
                    public void getdata(String start, String end) {
                        if ("".equals(end)) {
                            leftTextStart.setText(start);
                            leftTextEnd.setText(start);
                        } else {
                            leftTextStart.setText(start);
                            leftTextEnd.setText(end);
                        }
                        setAdapterList();
                    }
                });
                dialog.show(getSupportFragmentManager(), "sss");
                break;
            case R.id.left_layout_end:
                YearDialog dialog2 = new YearDialog();
                dialog2.setData(new YearDialog.getDATA() {
                    @Override
                    public void getdata(String start, String end) {
                        if ("".equals(end)) {
                            leftTextStart.setText(start);
                            leftTextEnd.setText(start);
                        } else {
                            leftTextStart.setText(start);
                            leftTextEnd.setText(end);
                        }
                        setAdapterList();
                    }
                });
                dialog2.show(getSupportFragmentManager(), "sss");
                break;
        }
    }

}