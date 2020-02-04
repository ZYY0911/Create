package com.example.create.activity2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean2.GSCP;
import com.example.create.bean2.GYS;
import com.example.create.bean2.GYSP;
import com.example.create.bean2.JYSJ;
import com.example.create.bean3.RK;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-20
 */
public class Z_TJXQActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.expanded_menu)
    ExpandableListView expandedMenu;
    private int lx;
    private List<GYS> gys;
    private Map<String, List<String>> map;
    private List<String> strings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tjxq_layout);
        ButterKnife.bind(this);
        title.setText("供应商--统计详情");
        lx = getIntent().getIntExtra("lx", 1);
        switch (lx) {
            case 1:
                initCs();
                break;
            case 2:
                initSl();
                break;
            case 3:
                initYw();
                break;
            case 4:
                initJy();
                break;
        }
    }

    private void initJy() {
        List<GSCP> gscps = LitePal.findAll(GSCP.class);
        Map<String, Integer> maps = new HashMap<>();
        strings = new ArrayList<>();
        List<String> strings1 = new ArrayList<>();
        for (int i = 0; i < gscps.size(); i++) {
            GSCP gscp = gscps.get(i);
            List<RK> jysjs = LitePal.where("ylmc=?", gscp.getName()).find(RK.class);
            int money = (maps.get(gscp.getName()) == null) ? 0 : maps.get(gscp.getName());
            for (int j = 0; j < jysjs.size(); j++) {
                money +=(jysjs.get(j).getNum()*jysjs.get(j).getPrice());
            }
            maps.put(gscp.getName(), money);
            strings1.add(gscp.getName());
        }
        map = new HashMap<>();
        for (int i = 0; i < strings1.size(); i++) {
            List<RK> jysjs = LitePal.where("ylmc=?", strings1.get(i)).find(RK.class);
            if (jysjs.size() == 0) {
                map.put(strings1.get(i) + "\n     交易总金额:" + maps.get(strings1.get(i)), new ArrayList<String>());
            } else {
                List<String> stringList = new ArrayList<>();
                for (int j = 0; j < jysjs.size(); j++) {
                    stringList.add("交易时间:" + jysjs.get(j).getTime() + "     交易金额:" +(jysjs.get(j).getNum()*jysjs.get(j).getPrice()));
                }
                map.put(strings1.get(i) + "\n     交易总金额:" + maps.get(strings1.get(i)), stringList);
            }
            strings.add(strings1.get(i) + "\n     交易总金额:" + maps.get(strings1.get(i)));
        }
        expandedMenu.setAdapter(new MyAdapter());
    }
    private void initYw() {
        strings = new ArrayList<>();
        strings.add("存在业务");
        strings.add("不存在业务");
        map = new HashMap<>();
        List<GSCP> gscps = LitePal.findAll(GSCP.class);
        for (int i = 0; i < gscps.size(); i++) {
            GSCP gscp = gscps.get(i);
            List<RK> jysjs = LitePal.where("ylmc=?", gscp.getName()).find(RK.class);
            if (jysjs.size() == 0) {
                List<String> count = map.get(strings.get(1));
                if (count == null) count = new ArrayList<>();
                count.add(gscp.getName());
                map.put(strings.get(1), count);
            } else {
                List<String> count = map.get(strings.get(0));
                if (count == null) count = new ArrayList<>();
                count.add(gscp.getName());
                map.put(strings.get(0), count);
            }
        }
        expandedMenu.setAdapter(new MyAdapter());
    }

    private void initSl() {
        strings = new ArrayList<>();
        map = new HashMap<>();
        gys = new ArrayList<>();
        List<GYSP> gysps = LitePal.findAll(GYSP.class);
        for (int i = 0; i < gysps.size(); i++) {
            strings.add(gysps.get(i).getYlName());
            List<GYS> gys1 = LitePal.where("gysNum=?", gysps.get(i).getGysNum() + "").find(GYS.class);
            if (gys1.size() != 0) {
                List<String> count = map.get(gysps.get(i).getYlName());
                if (count == null) count = new ArrayList<>();
                for (int j = 0; j < gys1.size(); j++) {
                    count.add(gys1.get(j).getGysName());
                }
                map.put(gysps.get(i).getYlName(), count);
            } else {
                map.put(gysps.get(i).getYlName(), new ArrayList<String>());
            }
            for (int k = 0; k < strings.size(); k++) {
                for (int j = strings.size() - 1; j > k; j--) {
                    if (strings.get(k).equals(strings.get(j))) {
                        strings.remove(j);
                    }
                }
            }
        }
        expandedMenu.setAdapter(new MyAdapter());
    }

    private void initCs() {
        gys = LitePal.findAll(GYS.class);
        strings = new ArrayList<>();
        map = new HashMap<>();
        for (int i = 0; i < gys.size(); i++) {
            String city = gys.get(i).getGysCity();
            strings.add(city);
            List<String> count = map.get(city);
            if (count == null) count = new ArrayList<>();
            count.add(gys.get(i).getGysName());
            map.put(city, count);
            for (int k = 0; k < strings.size(); k++) {
                for (int j = strings.size() - 1; j > k; j--) {
                    if (strings.get(k).equals(strings.get(j))) {
                        strings.remove(j);
                    }
                }
            }
        }
        expandedMenu.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return map.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            Log.i("ffff", "getChildrenCount: " + map.get(strings.get(groupPosition)).size());
            return map.get(strings.get(groupPosition)).size();
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.textview_item, parent, false);
            TextView textView = view.findViewById(R.id.fu_item);
            textView.setText(strings.get(groupPosition));
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.textview_item_zi, parent, false);
            TextView textView = view.findViewById(R.id.zi_item);
            textView.setText(map.get(strings.get(groupPosition)).get(childPosition));
            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
