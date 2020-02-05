package com.example.create.activity2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.adapter2.GYSCXAdapter;
import com.example.create.adapter2.SelectAdapter;
import com.example.create.adapter2.SortAdapter;
import com.example.create.bean2.GYS;
import com.example.create.bean2.GYSCXBean;
import com.example.create.bean2.GYSCXBean2;
import com.example.create.bean2.GYSP;
import com.example.create.util.PasePing;
import com.example.create.util.SideBar;

import net.sourceforge.pinyin4j.PinyinHelper;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-20
 */
public class Z_GYSCXActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.image_find)
    ImageView imageFind;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.first_layout)
    LinearLayout firstLayout;
    @BindView(R.id.second_layout)
    RelativeLayout secondLayout;
    @BindView(R.id.side_bar)
    SideBar sideBar;
    @BindView(R.id.listView2)
    ListView listView2;
    private List<String> list;
    private SelectAdapter adapter;
    private int image[] = {R.drawable.city1, R.drawable.city2, R.drawable.city3, R.drawable.city4, R.drawable.city5};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyscx_layout);
        ButterKnife.bind(this);
        title.setText("供应商--供应商查询");
        initView();
        initData();
        secondLayout.setVisibility(View.GONE);
        List<GYS> gys = LitePal.findAll(GYS.class);
        fisrt(new ArrayList<String>(), gys, new ArrayList<GYSCXBean>());
    }

    private void initLisent(final List<GYSCXBean2> list) {
        sideBar.setScaleItemCount(1);
        sideBar.setOnStrSelectCallBack(new SideBar.ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                for (int i = 0; i < list.size(); i++) {
                    if (selectStr.equalsIgnoreCase(list.get(i).getFirst())) {
                        listView2.setSelection(i); // 选择到首字母出现的位置
                        return;
                    }
                }
            }
        });
    }

    private void initData() {
        adapter = new SelectAdapter(this, R.layout.gyscx_left_item, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setIndex(position);
                listView.smoothScrollToPositionFromTop(position - 1, 0, 500);
                listClick(position);
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void fisrt(List<String> strings, List<GYS> gys, List<GYSCXBean> gyscxBeans) {
        firstLayout.setVisibility(View.VISIBLE);
        secondLayout.setVisibility(View.GONE);
        strings.clear();
        for (int i = 0; i < gys.size(); i++) {
            GYS gys1 = gys.get(i);
            strings.add(gys1.getGysCity());
            for (int k = 0; k < strings.size(); k++) {
                for (int j = strings.size() - 1; j > k; j--) {
                    if (strings.get(k).equals(strings.get(j))) {
                        strings.remove(j);
                        Log.i("eee", "listClick: ");
                    }
                }
            }
        }
        for (int i = 0, j = 0; i < strings.size(); i++, j++) {
            if (j == image.length) {
                j = 0;
            }
            gyscxBeans.add(new GYSCXBean(strings.get(i), image[j]));
        }
        recycleView.setAdapter(new GYSCXAdapter(gyscxBeans, 1));
    }

    private void listClick(int position) {
        List<GYS> gys = LitePal.findAll(GYS.class);
        List<GYSP> gysps = LitePal.findAll(GYSP.class);
        List<GYSCXBean> gyscxBeans = new ArrayList<>();
        final List<GYSCXBean2> gyscxBeans2 = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        switch (list.get(position)) {
            case "地区":
                fisrt(strings, gys, gyscxBeans);
                break;
            case "业务范围":
                firstLayout.setVisibility(View.VISIBLE);
                secondLayout.setVisibility(View.GONE);
                strings.clear();
                for (int i = 0; i < gys.size(); i++) {
                    GYS gys1 = gys.get(i);
                    strings.add(gys1.getGysRange());
                    for (int k = 0; k < strings.size(); k++) {
                        for (int j = strings.size() - 1; j > k; j--) {
                            if (strings.get(k).equals(strings.get(j))) {
                                strings.remove(j);
                            }
                        }
                    }
                }
                for (int i = 0, j = 0; i < strings.size(); i++, j++) {
                    if (j == image.length) {
                        j = 0;
                    }
                    gyscxBeans.add(new GYSCXBean(strings.get(i), image[j]));
                }
                recycleView.setAdapter(new GYSCXAdapter(gyscxBeans, 2));
                break;
            case "原料名称":
                firstLayout.setVisibility(View.VISIBLE);
                secondLayout.setVisibility(View.GONE);
                strings.clear();
                for (int i = 0; i < gysps.size(); i++) {
                    GYSP gys1 = gysps.get(i);
                    strings.add(gys1.getYlName());
                    for (int k = 0; k < strings.size(); k++) {
                        for (int j = strings.size() - 1; j > k; j--) {
                            if (strings.get(k).equals(strings.get(j))) {
                                strings.remove(j);
                            }
                        }
                    }
                }
                for (int i = 0, j = 0; i < strings.size(); i++, j++) {
                    if (j == image.length) {
                        j = 0;
                    }
                    gyscxBeans.add(new GYSCXBean(strings.get(i), image[j]));
                }
                recycleView.setAdapter(new GYSCXAdapter(gyscxBeans, 3));
                break;
            case "价格":
                firstLayout.setVisibility(View.VISIBLE);
                secondLayout.setVisibility(View.GONE);
               /* int a = 0, b = 0, c = 0, d = 0;
                for (int i = 0; i < gysps.size(); i++) {
                    int price = Integer.parseInt(gysps.get(i).getYlPrice());
                    if (price < 5) {
                        a++;
                    } else if (price > 5 && price < 10) {
                        b++;
                    } else if (price > 10 && price < 15) {
                        c++;
                    } else if (price > 15) {
                        d++;
                    }
                }*/
                gyscxBeans.add(new GYSCXBean("5元以下",R.drawable.city1));
                gyscxBeans.add(new GYSCXBean("5元～10元",R.drawable.city2));
                gyscxBeans.add(new GYSCXBean("10元～15元",R.drawable.city3));
                gyscxBeans.add(new GYSCXBean("15元以上",R.drawable.city4));
                recycleView.setAdapter(new GYSCXAdapter(gyscxBeans,4));
                break;
            case "名称":
                firstLayout.setVisibility(View.GONE);
                secondLayout.setVisibility(View.VISIBLE);
                for (int i = 0; i < gys.size(); i++) {
                    GYS gys1 = gys.get(i);
                    String pingying = PasePing.getPinYinFirstLetter(gys1.getGysName());
                    if (!pingying.matches("[A-Z]")) {
                        pingying = "#";
                    }
                    gyscxBeans2.add(new GYSCXBean2(gys1.getGysName(), gys1.getGysPhoto(), pingying));
                    Collections.sort(gyscxBeans2, new Comparator<GYSCXBean2>() {
                        @Override
                        public int compare(GYSCXBean2 o1, GYSCXBean2 o2) {
                            if (o1.getFirst().equals("#") && !o2.getFirst().equals("#")) {
                                return 1;
                            } else if (!o1.getFirst().equals("#") && o2.getFirst().equals("#")) {
                                return -1;
                            } else {
                                return o1.getFirst().compareToIgnoreCase(o2.getFirst());
                            }
                        }
                    });
                }
                listView2.setAdapter(new SortAdapter(this, R.layout.sort_item, gyscxBeans2, 1));
                initLisent(gyscxBeans2);
                break;
            case "联系人":
                firstLayout.setVisibility(View.GONE);
                secondLayout.setVisibility(View.VISIBLE);
                for (int i = 0; i < gys.size(); i++) {
                    GYS gys1 = gys.get(i);
                    String pingying = PasePing.getPinYinFirstLetter(gys1.getGysPeople());
                    if (!pingying.matches("[A-Z]")) {
                        pingying = "#";
                    }
                    gyscxBeans2.add(new GYSCXBean2(gys1.getGysPeople(), gys1.getGysPhoto(), pingying));
                    Collections.sort(gyscxBeans2, new Comparator<GYSCXBean2>() {
                        @Override
                        public int compare(GYSCXBean2 o1, GYSCXBean2 o2) {
                            if (o1.getFirst().equals("#") && !o2.getFirst().equals("#")) {
                                return 1;
                            } else if (!o1.getFirst().equals("#") && o2.getFirst().equals("#")) {
                                return -1;
                            } else {
                                return o1.getFirst().compareToIgnoreCase(o2.getFirst());
                            }
                        }
                    });
                }
                listView2.setAdapter(new SortAdapter(this, R.layout.sort_item, gyscxBeans2, 2));
                initLisent(gyscxBeans2);
                break;
        }
    }

    private void initView() {
        list = new ArrayList<>();
        list.add("地区");
        list.add("业务范围");
        list.add("原料名称");
        list.add("价格");
        list.add("名称");
        list.add("联系人");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recycleView.setLayoutManager(gridLayoutManager);
    }



    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }
}
