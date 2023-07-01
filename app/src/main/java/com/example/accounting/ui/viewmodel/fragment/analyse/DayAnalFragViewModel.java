package com.example.accounting.ui.viewmodel.fragment.analyse;
import android.graphics.Color;

import com.example.accounting.base.BaseActivityViewModel;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.example.accounting.base.BaseFragmentViewModel;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class DayAnalFragViewModel extends BaseFragmentViewModel
{
    public DayAnalFragViewModel()
    {
        super();
    }
    public static BarData generateBarChartData() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 100));
        entries.add(new BarEntry(1, 200));
        entries.add(new BarEntry(2, 150));
        entries.add(new BarEntry(3, 300));
        entries.add(new BarEntry(4, 250));
        entries.add(new BarEntry(5, 180));
        entries.add(new BarEntry(6, 120));

        BarDataSet dataSet = new BarDataSet(entries, "花销");
        dataSet.setColor(Color.parseColor("#87CEFA"));
        dataSet.setValueTextColor(Color.BLUE);
        return new BarData(dataSet);
    }
}