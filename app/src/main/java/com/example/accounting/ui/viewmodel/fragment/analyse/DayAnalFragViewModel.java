package com.example.accounting.ui.viewmodel.fragment.analyse;

import android.graphics.Color;

import androidx.lifecycle.LiveData;

import com.example.accounting.base.BaseFragmentViewModel;
import com.example.accounting.model.repository.TxnRepository;
import com.example.accounting.model.room.bean.PostInfo;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class DayAnalFragViewModel extends BaseFragmentViewModel
{
    private LiveData<PostInfo> postInfo;
    private final TxnRepository txnRepository = new TxnRepository();

    public DayAnalFragViewModel()
    {
        super();
        initPostInfo();
    }

    private void initPostInfo()
    {
        postInfo = txnRepository.queryDayPostInfo();
    }

    public LiveData<PostInfo> getPostInfo()
    {
        return postInfo;
    }

    public BarData getIncomeData()
    {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 0));
        entries.add(new BarEntry(1, 0));
        entries.add(new BarEntry(2, 0));
        entries.add(new BarEntry(3, 0));
        entries.add(new BarEntry(4, 800));
        entries.add(new BarEntry(5, 3500));
        entries.add(new BarEntry(6, 1200));

        BarDataSet dataSet = new BarDataSet(entries, "收入");
        dataSet.setColor(Color.parseColor("#87CEFA"));
        dataSet.setValueTextColor(Color.BLUE);
        return new BarData(dataSet);
    }

    public BarData getExpenseData()
    {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry((float) 0, (float) 60.8));
        entries.add(new BarEntry(1F, (float) 33.1));
        entries.add(new BarEntry(2, 65));
        entries.add(new BarEntry(3, (float) 75.2));
        entries.add(new BarEntry(4, 88));
        entries.add(new BarEntry(5, 217.5F));
        entries.add(new BarEntry(6F, (float) 223.2));


        BarDataSet dataSet = new BarDataSet(entries, "支出");
        dataSet.setColor(Color.parseColor("#87CEFA"));
        dataSet.setValueTextColor(Color.BLUE);
        return new BarData(dataSet);
    }
}