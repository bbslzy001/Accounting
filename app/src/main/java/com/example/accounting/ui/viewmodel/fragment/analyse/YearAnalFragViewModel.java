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

public class YearAnalFragViewModel extends BaseFragmentViewModel
{
    private LiveData<PostInfo> postInfo;
    private final TxnRepository txnRepository = new TxnRepository();

    public YearAnalFragViewModel()
    {
        super();
        initPostInfo();
    }

    private void initPostInfo()
    {
        postInfo = txnRepository.queryYearPostInfo();
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
        entries.add(new BarEntry(4, 9100));

        BarDataSet dataSet = new BarDataSet(entries, "收入");
        dataSet.setColor(Color.parseColor("#87CEFA"));
        dataSet.setValueTextColor(Color.BLUE);
        return new BarData(dataSet);
    }

    public BarData getExpenseData()
    {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, (float) 122.6));
        entries.add(new BarEntry(1, (float) 184.1));
        entries.add(new BarEntry(2, (float) 121.6));
        entries.add(new BarEntry(3, (float) 408.9));
        entries.add(new BarEntry(4, 1537F));

        BarDataSet dataSet = new BarDataSet(entries, "支出");
        dataSet.setColor(Color.parseColor("#87CEFA"));
        dataSet.setValueTextColor(Color.BLUE);
        return new BarData(dataSet);
    }
}