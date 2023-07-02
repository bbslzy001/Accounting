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
    public BarData getIncomeData()
    {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 0));
        entries.add(new BarEntry(1, 0));
        entries.add(new BarEntry(2, 0));
        entries.add(new BarEntry(3, 0));
        entries.add(new BarEntry(4, 800));
        entries.add(new BarEntry(5, 8300));

        BarDataSet dataSet = new BarDataSet(entries, "收入");
        dataSet.setColor(Color.parseColor("#87CEFA"));
        dataSet.setValueTextColor(Color.BLUE);
        return new BarData(dataSet);
    }

    public BarData getExpenseData()
    {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry((float) 0, (float) 36.5));
        entries.add(new BarEntry(1F, (float)99.2));
        entries.add(new BarEntry(2F, 147.5F));
        entries.add(new BarEntry(3, (float)107.2));
        entries.add(new BarEntry(4F, (float) 588.9));
        entries.add(new BarEntry(5, 440.7F));

        BarDataSet dataSet = new BarDataSet(entries, "支出");
        dataSet.setColor(Color.parseColor("#87CEFA"));
        dataSet.setValueTextColor(Color.BLUE);
        return new BarData(dataSet);
    }
}