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

    public BarData getIncomeData()
    {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 0));
        entries.add(new BarEntry(1, 0));
        entries.add(new BarEntry(2, 0));
        entries.add(new BarEntry(3, 0));
        entries.add(new BarEntry(4, 0));
        entries.add(new BarEntry(5, 9100));

        BarDataSet dataSet = new BarDataSet(entries, "收入");
        dataSet.setColor(Color.parseColor("#87CEFA"));
        dataSet.setValueTextColor(Color.BLUE);
        return new BarData(dataSet);
    }

    public BarData getExpenseData()
    {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry((float) 0, (float) 59.9));
        entries.add(new BarEntry(1F, (float) 122.6));
        entries.add(new BarEntry(2, (float) 184.1));
        entries.add(new BarEntry(3, (float) 121.6));
        entries.add(new BarEntry(4, (float) 408.9));
        entries.add(new BarEntry(5, 1537F));


        BarDataSet dataSet = new BarDataSet(entries, "支出");
        dataSet.setColor(Color.parseColor("#87CEFA"));
        dataSet.setValueTextColor(Color.BLUE);
        return new BarData(dataSet);
    }
}