package com.example.accounting.ui.viewmodel.fragment.analyse;

import android.graphics.Color;

import com.example.accounting.base.BaseFragmentViewModel;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class YearAnalFragViewModel extends BaseFragmentViewModel
{
    public YearAnalFragViewModel()
    {
        super();
    }
    public static BarData generateBarChartData() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 12343));
        entries.add(new BarEntry(1, 20450));
        entries.add(new BarEntry(2, 13803));

        BarDataSet dataSet = new BarDataSet(entries, "花销");
        dataSet.setColor(Color.parseColor("#87CEFA"));
        dataSet.setValueTextColor(Color.BLUE);
        return new BarData(dataSet);
    }
}