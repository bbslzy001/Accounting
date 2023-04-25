package com.example.accounting.ui.viewmodel.fragment.statistics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.example.accounting.base.BaseFragmentViewModel;
import com.example.accounting.model.entity.YearAndMonth;
import com.example.accounting.model.repository.TxnRvItemRepository;
import com.example.accounting.model.repository.YearMonthRepository;
import com.example.accounting.model.room.bean.TxnRvItem;
import com.example.accounting.model.room.bean.YearMonth;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListStatsFragViewModel extends BaseFragmentViewModel
{
    private final MutableLiveData<Double> monthIncome = new MutableLiveData<>();
    private final MutableLiveData<Double> monthExpenditure = new MutableLiveData<>();
    private final MutableLiveData<YearAndMonth> currentYearAndMonth = new MutableLiveData<>();
    private LiveData<List<TxnRvItem>> itemList;  // 获取实时年月对应的交易记录列表
    private LiveData<List<YearMonth>> yearMonthList;  // 获取实时年月列表
    private List<String> yearList;  // 根据实时年月列表计算年列表
    private List<List<String>> monthList;  // 根据实时年月列表计算月列表
    private final TxnRvItemRepository txnRvItemRepository = new TxnRvItemRepository();
    private final YearMonthRepository yearMonthRepository = new YearMonthRepository();

    public ListStatsFragViewModel()
    {
        super();
        initYearMonthList();
        initItemList();
    }

    private void initItemList()
    {
        itemList = Transformations.switchMap(currentYearAndMonth, yearAndMonth ->
        {
            String year = yearAndMonth.getYear().substring(0, yearAndMonth.getYear().length() - 1);
            String month = yearAndMonth.getMonth().substring(0, yearAndMonth.getMonth().length() - 1);
            return txnRvItemRepository.queryAllByYearAndMonth(year, month);
        });
    }

    public String[] getYearArray()
    {
        return yearList.toArray(new String[0]);
    }

    public String[] getMonthArray()
    {
        int yearIndex = yearList.indexOf(Objects.requireNonNull(currentYearAndMonth.getValue()).getYear());
        return monthList.get(yearIndex).toArray(new String[0]);
    }

    public String[] getMonthArray(int yearIndex)
    {
        return monthList.get(yearIndex).toArray(new String[0]);
    }

    public int getCurrentYearIndex()
    {
        return yearList.indexOf(Objects.requireNonNull(currentYearAndMonth.getValue()).getYear());
    }

    public int getCurrentMonthIndex()
    {
        int yearIndex = yearList.indexOf(Objects.requireNonNull(currentYearAndMonth.getValue()).getYear());
        return monthList.get(yearIndex).indexOf(currentYearAndMonth.getValue().getMonth());
    }

    public void setCurrentYear(int yearIndex)
    {
        String year = yearList.get(yearIndex);
        String month = Objects.requireNonNull(currentYearAndMonth.getValue()).getMonth();
        currentYearAndMonth.setValue(new YearAndMonth(year, month));
    }

    public void setCurrentMonth(int yearIndex, int monthIndex)
    {
        String year = yearList.get(yearIndex);
        String month = monthList.get(yearIndex).get(monthIndex);
        currentYearAndMonth.setValue(new YearAndMonth(year, month));
    }

    public LiveData<List<TxnRvItem>> getItemList()
    {
        return itemList;
    }

    public MutableLiveData<YearAndMonth> getCurrentYearAndMonth()
    {
        return currentYearAndMonth;
    }

    public MutableLiveData<Double> getMonthIncome()
    {
        return monthIncome;
    }

    public MutableLiveData<Double> getMonthExpenditure()
    {
        return monthExpenditure;
    }

    public void initYearMonthList()
    {
        yearMonthList = yearMonthRepository.queryAll();

        yearMonthList.observeForever(yearMonthList ->
        {
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
            initCurrentYearAndMonth();
        });
    }

    public void initCurrentYearAndMonth()
    {
        if (yearList == null || monthList == null)
        {
            yearMonthList.observeForever(new Observer<>()
            {
                @Override
                public void onChanged(List<YearMonth> yearMonths)
                {
                    yearMonthList.removeObserver(this);
                    initCurrentYearAndMonth();
                }
            });
        }
        else
        {
            String year = yearList.get(yearList.size() - 1);
            String month = monthList.get(yearList.size() - 1).get(monthList.get(yearList.size() - 1).size() - 1);
            currentYearAndMonth.setValue(new YearAndMonth(year, month));
        }
    }
}