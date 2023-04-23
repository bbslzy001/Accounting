package com.example.accounting.ui.viewmodel.fragment.statistics;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.accounting.base.BaseFragmentViewModel;
import com.example.accounting.model.repository.TxnRvItemRepository;
import com.example.accounting.model.repository.YearMonthRepository;
import com.example.accounting.model.room.bean.TxnRvItem;
import com.example.accounting.model.room.bean.YearMonth;

import java.util.ArrayList;
import java.util.List;

public class ListStatsFragViewModel extends BaseFragmentViewModel
{
    private final MutableLiveData<Double> monthIncome = new MutableLiveData<>();
    private final MutableLiveData<Double> monthExpenditure = new MutableLiveData<>();
    private final MutableLiveData<String> currentYear = new MutableLiveData<>();
    private final MutableLiveData<String> currentMonth = new MutableLiveData<>();
    private LiveData<List<TxnRvItem>> itemList;  // 获取实时年月对应的交易记录列表
    private LiveData<List<YearMonth>> yearMonthList;  // 获取实时年月列表

    private List<String> yearList;  // 根据实时年月列表计算年列表
    private List<List<String>> monthList;  // 根据实时年月列表计算月列表
    private final TxnRvItemRepository txnRvItemRepository = new TxnRvItemRepository();
    private final YearMonthRepository yearMonthRepository = new YearMonthRepository();

    public ListStatsFragViewModel()
    {
        super();
    }

    public String[] getYearArray()
    {
        return yearList.toArray(new String[0]);
    }

    public String[] getMonthArray()
    {
        int yearIndex = yearList.indexOf(currentYear.getValue());
        return monthList.get(yearIndex).toArray(new String[0]);
    }

    public String[] getMonthArray(int yearIndex)
    {
        return monthList.get(yearIndex).toArray(new String[0]);
    }

    public int getCurrentYearIndex()
    {
        return yearList.indexOf(currentYear.getValue());
    }

    public int getCurrentMonthIndex()
    {
        int yearIndex = yearList.indexOf(currentYear.getValue());
        return monthList.get(yearIndex).indexOf(currentMonth.getValue());
    }

    public void setCurrentYear(int yearIndex)
    {
        String year = yearList.get(yearIndex);
        currentYear.setValue(year);
    }

    public void setCurrentMonth(int yearIndex, int monthIndex)
    {
        String month = monthList.get(yearIndex).get(monthIndex);
        this.currentMonth.setValue(month);
    }

    public LiveData<List<TxnRvItem>> getItemList()
    {
        return itemList;
    }

    public MutableLiveData<String> getCurrentYear()
    {
        return currentYear;
    }

    public MutableLiveData<String> getCurrentMonth()
    {
        return currentMonth;
    }

    public MutableLiveData<Double> getMonthIncome()
    {
        return monthIncome;
    }

    public MutableLiveData<Double> getMonthExpenditure()
    {
        return monthExpenditure;
    }

    public void observeYearMonthList(LifecycleOwner owner)
    {
        Log.d("test", "监听年月列表变化");
        yearMonthList.observe(owner, yearMonthList ->
        {
            Log.d("test", "检测到年月列表变化");
            List<String> yearList = new ArrayList<>();
            List<List<String>> monthList = new ArrayList<>();
            for (YearMonth yearMonth : yearMonthList)
            {
                String year = yearMonth.getYear() + "年";
                if (!yearList.contains(year))
                {
                    yearList.add(year);
                    List<String> months = new ArrayList<>();
                    months.add(yearMonth.getMonth() + "月");
                    monthList.add(months);
                }
                else
                {
                    int index = yearList.indexOf(year);
                    List<String> months = monthList.get(index);
                    months.add(yearMonth.getMonth() + "月");
                }
            }

            this.yearList = yearList;
            this.monthList = monthList;
        });
    }

    public void observeCurrentYearAndMonth(LifecycleOwner owner)
    {
        Log.d("test", "监听年月变化");
        currentYear.observe(owner, s ->
        {
            Log.d("test", "检测到年变化");
            Log.d("test", "observeCurrentYearAndMonth: "+currentYear.getValue()+" "+currentMonth.getValue());
            if(currentYear.getValue()!=null&&currentMonth.getValue()!=null) itemList = txnRvItemRepository.queryAllByYearAndMonth(currentYear.getValue().substring(0,currentYear.getValue().length()-1), currentMonth.getValue().substring(0,currentMonth.getValue().length()-1));
        });
        currentMonth.observe(owner, s ->
        {
            Log.d("test", "检测到月变化");
            Log.d("test", "observeCurrentYearAndMonth: "+currentYear.getValue().substring(0,currentYear.getValue().length()-1)+" "+currentMonth.getValue().substring(0,currentMonth.getValue().length()-1));
            if(currentYear.getValue()!=null&&currentMonth.getValue()!=null) itemList = txnRvItemRepository.queryAllByYearAndMonth(currentYear.getValue().substring(0,currentYear.getValue().length()-1), currentMonth.getValue().substring(0,currentMonth.getValue().length()-1));
        });
    }

    public void initYearMonthList()
    {
        Log.d("test", "初始化年月列表");
        yearMonthList = yearMonthRepository.queryAll();
    }

    public void initCurrentYearAndMonth()
    {
        if (yearList == null || monthList == null) {
            // if yearList or monthList is null, wait for LiveData to update them
            Log.d("test", "年列表和月列表没有更新，监听年列表和月列表变化");
            yearMonthList.observeForever(new Observer<>()
            {
                @Override
                public void onChanged(List<YearMonth> yearMonths)
                {
                    Log.d("test", "年列表和月列表异步更新");
                    yearMonthList.removeObserver(this);
                    initCurrentYearAndMonth();
                }
            });
        } else {
            // initialize currentYear and currentMonth
            Log.d("test", "年列表和月列表正常更新");
            currentYear.setValue(yearList.get(yearList.size() - 1));
            currentMonth.setValue(monthList.get(yearList.size() - 1).get(monthList.get(yearList.size() - 1).size() - 1));
        }
//        currentYear.setValue(yearList.get(yearList.size() - 1));
//        currentMonth.setValue(monthList.get(yearList.size() - 1).get(monthList.get(yearList.size() - 1).size() - 1));
    }

    public void setMonthIncome(Double monthIncome)
    {
        this.monthIncome.setValue(monthIncome);
    }

    public void setMonthExpenditure(Double monthExpenditure)
    {
        this.monthExpenditure.setValue(monthExpenditure);
    }
}