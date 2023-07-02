package com.example.accounting.ui.viewmodel.fragment.analyse;

import android.graphics.Color;

import com.example.accounting.base.BaseFragmentViewModel;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class DayAnalFragViewModel extends BaseFragmentViewModel
{
    public DayAnalFragViewModel()
    {
        super();
    }

    public BarData getIncomeData()
    {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 100));
        entries.add(new BarEntry(1, 200));

        BarDataSet dataSet = new BarDataSet(entries, "收入");
        dataSet.setColor(Color.parseColor("#87CEFA"));
        dataSet.setValueTextColor(Color.BLUE);
        return new BarData(dataSet);
    }

    public BarData getExpenseData()
    {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 100));
        entries.add(new BarEntry(1, 200));
        entries.add(new BarEntry(2, 150));
        entries.add(new BarEntry(3, 300));

        BarDataSet dataSet = new BarDataSet(entries, "支出");
        dataSet.setColor(Color.parseColor("#87CEFA"));
        dataSet.setValueTextColor(Color.BLUE);
        return new BarData(dataSet);
    }
}