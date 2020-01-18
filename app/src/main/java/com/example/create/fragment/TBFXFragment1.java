package com.example.create.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.create.R;
import com.example.create.bean.JBXX;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.litepal.LitePal;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by 张瀛煜 on 2020-01-17
 */
public class TBFXFragment1 extends Fragment {
    private PieChart leftChart;
    private PieChart rightChart;
    private List<JBXX> jbxxes;
    private List<PieEntry> pieEntries, pieEntries1;
    private List<Integer> colors,colors1;
    private int a, b, c, d, e, f;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tbfx_fragment1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        if (jbxxes==null)jbxxes = new ArrayList<>();
        else jbxxes.clear();
        a=0;
        b=0;
        c=0;
        d=0;
        e=0;f=0;
        jbxxes = LitePal.findAll(JBXX.class);
        Log.i("eeee", "initData: "+jbxxes.size());
        if (pieEntries == null) pieEntries = new ArrayList<>();
        else pieEntries.clear();
        if (pieEntries1 == null) pieEntries1 = new ArrayList<>();
        else pieEntries1.clear();
        if (colors == null) colors = new ArrayList<>();
        else colors.clear();
        if (colors1 == null) colors1 = new ArrayList<>();
        else colors1.clear();
        for (int i = 0; i < jbxxes.size(); i++) {
            JBXX jbxx = jbxxes.get(i);
            try {
                int year = Integer.parseInt(jbxx.getGzjl());
                if (year == 0) {
                    a++;
                } else if (year == 1) {
                    b++;
                } else if (year > 1 && year < 3) {
                    c++;
                } else {
                    d++;
                }
                switch (jbxx.getSex()) {
                    case "男":
                        e++;
                        break;
                    case "女":
                        f++;
                        break;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        pieEntries.add(new PieEntry(a, "无"));
        pieEntries.add(new PieEntry(b, "1年"));
        pieEntries.add(new PieEntry(c, "1～3年"));
        pieEntries.add(new PieEntry(d, "3年以上"));
        colors.add(Color.parseColor("#FF6F5E"));
        colors.add(Color.parseColor("#7BFF41"));
        colors.add(Color.parseColor("#BC80FF"));
        colors.add(Color.parseColor("#FFDF38"));
        colors1.add(Color.parseColor("#BC80FF"));
        colors1.add(Color.parseColor("#FFDF38"));
        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setValueTextSize(20);
        dataSet.setValueLinePart1OffsetPercentage(60);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.2f);
        dataSet.setValueLineColor(Color.BLACK);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("0");
                return format.format(value);
            }
        });
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        Legend legend = leftChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setTextSize(25);
        legend.setFormSize(25);
        leftChart.setData(data);
        leftChart.setDrawHoleEnabled(false);
        leftChart.setEntryLabelColor(Color.BLACK);
        leftChart.setEntryLabelTextSize(20);
        leftChart.invalidate();
        pieEntries1.add(new PieEntry(e, "男"));
        pieEntries1.add(new PieEntry(f, "女"));
        PieDataSet dataSet1 = new PieDataSet(pieEntries1, "");
        dataSet1.setValueTextSize(20);
        dataSet1.setValueLinePart1OffsetPercentage(60);
        dataSet1.setValueLinePart1Length(0.2f);
        dataSet1.setValueLinePart2Length(0.2f);
        dataSet1.setValueLineColor(Color.BLACK);
        dataSet1.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet1.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet1.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("0");
                return format.format(value);
            }
        });
        dataSet1.setColors(colors1);
        PieData data1 = new PieData(dataSet1);
        Legend legend1 = rightChart.getLegend();
        legend1.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend1.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend1.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend1.setTextSize(25);
        legend1.setFormSize(25);
        rightChart.setData(data1);
        rightChart.setDrawHoleEnabled(false);
        rightChart.setEntryLabelColor(Color.BLACK);
        rightChart.setEntryLabelTextSize(20);
        rightChart.invalidate();
    }

    private void initView() {
        leftChart = getView().findViewById(R.id.left_chart);
        rightChart = getView().findViewById(R.id.right_chart);

    }
}
