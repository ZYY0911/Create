package com.example.create.activity2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.create.R;
import com.example.create.bean2.GSCP;
import com.example.create.bean2.GYS;
import com.example.create.bean2.GYSP;
import com.example.create.bean2.JYSJ;
import com.example.create.bean3.RK;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.litepal.LitePal;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-01-20
 */
public class Z_GYSTJActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.bar_chart_left_top)
    BarChart barChartLeftTop;
    @BindView(R.id.pie_chart_right_top)
    PieChart pieChartRightTop;
    @BindView(R.id.pie_chart_left_bottom)
    PieChart pieChartLeftBottom;
    @BindView(R.id.bar_chart_right_bottom)
    BarChart barChartRightBottom;
    private List<GYS> gys;
    private List<GYSP> gysps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gystj_layout);
        ButterKnife.bind(this);
        title.setText("供应商--供应商统计");
        initLeftTop();
        initRightTop();
        initLeftBottom();
        initRightBottom();
    }

    private void initRightBottom() {
        List<GSCP> gscps = LitePal.findAll(GSCP.class);
        List<Integer> colors = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < gscps.size(); i++) {
            GSCP gscp = gscps.get(i);
            List<RK> jysjs = LitePal.where("ylmc=?", gscp.getName()).find(RK.class);
            int money = (map.get(gscp.getName())==null)? 0:map.get(gscp.getName());
            for (int j = 0; j < jysjs.size(); j++) {
                money += (jysjs.get(j).getNum()*jysjs.get(j).getPrice());
            }
            map.put(gscp.getName(), money);
            colors.add(getColor());
            strings.add(gscp.getName());
        }
        List<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            barEntries.add(new BarEntry(i, map.get(strings.get(i))));
        }
        BarDataSet dataSet = new BarDataSet(barEntries, "");
        dataSet.setColors(colors);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("0.0");
                return format.format(value) + "元";
            }
        });
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.3f);
        barChartRightBottom.setData(data);
        XAxis xAxis = barChartRightBottom.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        YAxis yAxis = barChartRightBottom.getAxisRight();
        yAxis.setStartAtZero(true);
        yAxis.setEnabled(false);
        YAxis yAxis1 = barChartRightBottom.getAxisLeft();
        yAxis1.setStartAtZero(true);
        barChartRightBottom.getLegend().setEnabled(false);
        barChartRightBottom.getDescription().setText("所有供应商交易总金额统计图");
        barChartRightBottom.getDescription().setTextSize(15);
        barChartRightBottom.setDoubleTapToZoomEnabled(false);
        barChartRightBottom.invalidate();
    }

    private void initLeftBottom() {
        List<GSCP> gscps = LitePal.findAll(GSCP.class);
        List<Integer> colors = new ArrayList<>();
        int a = 0, b = 0;
        for (int i = gscps.size() - 1; i >= 0; i--) {
            GSCP gscp = gscps.get(i);
            List<RK> jysjs = LitePal.where("ylmc=?", gscp.getName()).find(RK.class);
            if (jysjs.size() == 0) {
                a++;
            } else {
                b++;
            }
            colors.add(getColor());
        }
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(a, "没有业务供应商"));
        pieEntries.add(new PieEntry(b, "有业务供应商"));
        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setValueFormatter(new PercentFormatter());
        dataSet.setColors(colors);
        dataSet.setSliceSpace(3f);
        PieData data = new PieData(dataSet);
        pieChartLeftBottom.setData(data);
        pieChartLeftBottom.setDrawHoleEnabled(false);
        pieChartLeftBottom.setRotationEnabled(false);
        pieChartLeftBottom.getDescription().setText("有无业务供应商统计图");
        pieChartLeftBottom.getDescription().setTextSize(15);
        pieChartLeftBottom.setTouchEnabled(false);
        pieChartLeftBottom.setUsePercentValues(true);
        pieChartLeftBottom.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
    }

    private void initRightTop() {
        gysps = LitePal.findAll(GYSP.class);
        Map<String, Integer> map = new HashMap<>();
        List<String> strings = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();
        for (int i = 0; i < gysps.size(); i++) {
            List<GYS> gys1 = LitePal.where("gysNum=?", gysps.get(i).getGysNum() + "").find(GYS.class);
            if (gys1.size() != 0) {
                String name = gys1.get(0).getGysName();
                Integer count = map.get(name);
                map.put(name, (count == null) ? 1 : count + 1);
                strings.add(name);
                for (int k = 0; k < strings.size(); k++) {
                    for (int j = strings.size() - 1; j > k; j--) {
                        if (strings.get(k).equals(strings.get(j))) {
                            strings.remove(j);
                        }
                    }
                }
                colors.add(getColor());
            }
        }
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            pieEntries.add(new PieEntry((float) map.get(strings.get(i)) / (float) gysps.size(), strings.get(i)));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setValueFormatter(new PercentFormatter());
        dataSet.setColors(colors);
        dataSet.setSliceSpace(3f);
        PieData data = new PieData(dataSet);
        pieChartRightTop.setData(data);
        pieChartRightTop.setDrawHoleEnabled(false);
        pieChartRightTop.setRotationEnabled(false);
        pieChartRightTop.getDescription().setText("不同产品供应商数量统计图");
        pieChartRightTop.getDescription().setTextSize(15);
        pieChartRightTop.setTouchEnabled(false);
        pieChartRightTop.setUsePercentValues(true);
        pieChartRightTop.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
    }

    private void initLeftTop() {
        gys = LitePal.findAll(GYS.class);
        Map<String, Integer> map = new HashMap<>();
        List<String> strings = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();
        for (int i = 0; i < gys.size(); i++) {
            String city = gys.get(i).getGysCity();
            Integer count = map.get(city);
            Log.i("aaa", "initLeftTop: bb" + city);
            map.put(city, (count == null) ? 1 : count + 1);
            strings.add(city);
            for (int k = 0; k < strings.size(); k++) {
                for (int j = strings.size() - 1; j > k; j--) {
                    if (strings.get(k).equals(strings.get(j))) {
                        strings.remove(j);
                    }
                }
            }
            colors.add(getColor());
        }
        List<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            barEntries.add(new BarEntry(i, (float) map.get(strings.get(i)) / (float) gys.size()));
        }
        BarDataSet dataSet = new BarDataSet(barEntries, "");
        dataSet.setColors(colors);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("0.0");
                return format.format(value * 100) + "%";
            }
        });
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.2f);
        barChartLeftTop.setData(data);
        XAxis xAxis = barChartLeftTop.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        YAxis yAxis = barChartLeftTop.getAxisRight();
        yAxis.setStartAtZero(true);
        yAxis.setEnabled(false);
        YAxis yAxis1 = barChartLeftTop.getAxisLeft();
        yAxis1.setStartAtZero(true);
        barChartLeftTop.getLegend().setEnabled(false);
        barChartLeftTop.getDescription().setText("所在城市百分比统计图");
        barChartLeftTop.getDescription().setTextSize(15);
        barChartLeftTop.setDoubleTapToZoomEnabled(false);
        barChartLeftTop.invalidate();
    }

    private int getColor() {
        Random random = new Random();
        int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
        return ranColor;
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.bar_chart_left_top, R.id.pie_chart_right_top, R.id.pie_chart_left_bottom, R.id.bar_chart_right_bottom})
    public void onViewClicked(View view) {
        Intent intent = new Intent(this, Z_TJXQActivity.class);
        switch (view.getId()) {
            case R.id.bar_chart_left_top:
                intent.putExtra("lx", 1);
                break;
            case R.id.pie_chart_right_top:
                intent.putExtra("lx", 2);
                break;
            case R.id.pie_chart_left_bottom:
                intent.putExtra("lx", 3);
                break;
            case R.id.bar_chart_right_bottom:
                intent.putExtra("lx", 4);
                break;
        }
        startActivity(intent);
    }
}

