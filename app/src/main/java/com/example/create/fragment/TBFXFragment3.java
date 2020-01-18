package com.example.create.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.create.R;
import com.example.create.bean.JBXX;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import org.litepal.LitePal;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 张瀛煜 on 2020-01-18
 */
public class TBFXFragment3 extends Fragment {
    private LineChart lineChart;
    private List<JBXX> jbxxes;
    private int a,b,c,d;
    private List<Entry> entries;
    private List<String> xVaalue;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tbfx_framgent3, container, false);
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
        c=0;d=0;
        if (entries==null)entries = new ArrayList<>();
        else entries.clear();
        if (xVaalue==null)xVaalue = new ArrayList<>();
        else xVaalue.clear();
        jbxxes = LitePal.findAll(JBXX.class);
        for (int i = 0; i < jbxxes.size(); i++) {
            String data[] = jbxxes.get(i).getBirth().split("-");
            int year = 2020 - Integer.parseInt(data[0]);
            if (year<25){
                a++;
            }else if (year>25&&year<30){
                b++;
            }else if (year>30&&year<35){
                c++;
            }else {
                d++;
            }
        }
        entries.add(new Entry(1,a));
        entries.add(new Entry(2,b));
        entries.add(new Entry(3,c));
        entries.add(new Entry(4,d));
        xVaalue.add("");
        xVaalue.add("25以下");
        xVaalue.add("25～30以下");
        xVaalue.add("30～35以下");
        xVaalue.add("35以上");
        xVaalue.add("");
        LineDataSet dataSet = new LineDataSet(entries,"");
        dataSet.setDrawCircleHole(false);
        dataSet.setCircleColor(Color.RED);
        dataSet.setColor(Color.GREEN);
        dataSet.setLineWidth(5);
        dataSet.setCircleRadius(5);
        LineData data  =new LineData(dataSet);
        lineChart.setData(data);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(5);
        xAxis.setTextSize(20);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVaalue));
        xAxis.setLabelRotationAngle(-50);
        xAxis.setGranularity(1f);
        lineChart.getLegend().setEnabled(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.invalidate();
    }

    private void initView() {
        lineChart = getView().findViewById(R.id.line_chart);
    }
}
