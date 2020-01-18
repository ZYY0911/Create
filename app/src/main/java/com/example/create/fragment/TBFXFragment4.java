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
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 张瀛煜 on 2020-01-18
 */
public class TBFXFragment4 extends Fragment {
    private RadarChart radarChart;
    private List<RadarEntry> radarEntries;
    private List<JBXX> jbxxes;
    private int a, b, c, d, e;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tbfx_fragment4, container, false);
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
        d=0;
        a=0;
        c=0;
        e=0;
        b=0;
        jbxxes = LitePal.findAll(JBXX.class);
        for (int i = 0; i < jbxxes.size(); i++) {
            switch (jbxxes.get(i).getXl()) {
                case "博士":
                    a++;
                    break;
                case "硕士":
                    b++;
                    break;
                case "本科":
                    c++;
                    break;
                case "专科":
                    d++;
                    break;
                case "专科以下":
                    e++;
                    break;
            }
        }
        if (radarEntries == null) radarEntries = new ArrayList<>();
        else radarEntries.clear();
        radarEntries.add(new RadarEntry(a, "博士"));
        radarEntries.add(new RadarEntry(b, "硕士"));
        radarEntries.add(new RadarEntry(c, "本科"));
        radarEntries.add(new RadarEntry(d, "专科"));
        radarEntries.add(new RadarEntry(e, "专科以下"));
        RadarDataSet dataSet = new RadarDataSet(radarEntries, "");
        List<IRadarDataSet> iRadarDataSets = new ArrayList<>();
        iRadarDataSets.add(dataSet);
        iRadarDataSets.add(drawAngleCircle());
        RadarData data = new RadarData(iRadarDataSets);
        radarChart.setData(data);
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setDrawLabels(false);
        YAxis yAxis = radarChart.getYAxis();
        yAxis.setAxisMaximum(8f);
        Legend legend = radarChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        radarChart.invalidate();radarChart.setTouchEnabled(false);
    }

    private void initView() {
        radarChart = getView().findViewById(R.id.radar_chart);
    }

    private RadarDataSet drawAngleCircle() {
        List<RadarEntry> yVals = new ArrayList<>();
        // 各个顶点的图标资源
        int[] drawables = new int[]{
                R.drawable.shape_circle_1,
                R.drawable.shape_circle_2,
                R.drawable.shape_circle_3,
                R.drawable.shape_circle_4,
                R.drawable.shape_circle_5,
        };
        for (int i = 0; i < 5; i++) {
            RadarEntry radarEntry = new RadarEntry(9f);
            radarEntry.setIcon(getResources().getDrawable(drawables[i])); // 为每个数据设置一个图标
            yVals.add(radarEntry);
        }
        RadarDataSet ds = new RadarDataSet(yVals, "");
        ds.setColors(Color.TRANSPARENT); // 不显示数据连线
        ds.setDrawValues(false); // 不绘制数据值
        return ds;

    }
}
