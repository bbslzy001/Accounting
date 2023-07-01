package com.example.accounting.ui.viewmodel.fragment.analyse;

import android.graphics.Color;

import com.example.accounting.base.BaseFragmentViewModel;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class MonthAnalFragViewModel extends BaseFragmentViewModel
{
    public MonthAnalFragViewModel()
    {
        super();
    }
    public static BarData generateBarChartData() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 1000));
        entries.add(new BarEntry(1, 2000));
        entries.add(new BarEntry(2, 1500));
        entries.add(new BarEntry(3, 3000));
        entries.add(new BarEntry(4, 2500));
        entries.add(new BarEntry(5, 1800));
        entries.add(new BarEntry(6, 1200));
        entries.add(new BarEntry(7, 2000));
        entries.add(new BarEntry(8, 1500));
        entries.add(new BarEntry(9, 3000));
        entries.add(new BarEntry(10, 2500));
        entries.add(new BarEntry(11, 1800));
        entries.add(new BarEntry(12, 1200));

        BarDataSet dataSet = new BarDataSet(entries, "花销");
        dataSet.setColor(Color.parseColor("#87CEFA"));
        dataSet.setValueTextColor(Color.BLUE);
        return new BarData(dataSet);
    }
}