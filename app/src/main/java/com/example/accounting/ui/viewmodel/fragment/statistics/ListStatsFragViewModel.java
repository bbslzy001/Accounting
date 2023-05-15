package com.example.accounting.ui.viewmodel.fragment.statistics;

import android.content.res.Resources;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.example.accounting.base.BaseApplication;
import com.example.accounting.base.BaseFragmentViewModel;
import com.example.accounting.model.entity.YearAndMonth;
import com.example.accounting.model.repository.TxnRepository;
import com.example.accounting.model.repository.TxnRvItemRepository;
import com.example.accounting.model.repository.YearMonthRepository;
import com.example.accounting.model.room.bean.Txn;
import com.example.accounting.model.room.bean.TxnRvItem;
import com.example.accounting.model.room.bean.YearMonth;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListStatsFragViewModel extends BaseFragmentViewModel
{
    private LiveData<List<YearMonth>> yearMonthList;  // 获取实时年月列表
    private LiveData<List<TxnRvItem>> itemList;  // 获取实时年月对应的交易记录列表
    private LiveData<Double> monthIncome;
    private LiveData<Double> monthExpenditure;
    private final MutableLiveData<YearAndMonth> currentYearAndMonth = new MutableLiveData<>();
    private final List<String> yearList = new ArrayList<>();  // 根据实时年月列表计算年列表
    private final List<List<String>> monthList = new ArrayList<>();  // 根据实时年月列表计算月列表
    private final TxnRvItemRepository txnRvItemRepository = new TxnRvItemRepository();
    private final YearMonthRepository yearMonthRepository = new YearMonthRepository();
    private final TxnRepository txnRepository = new TxnRepository();

    public ListStatsFragViewModel()
    {
        super();
        initYearMonthList();
        initItemList();
        initMonthIncomeAndExpenditure();
    }

    private void initYearMonthList()
    {
        yearMonthList = yearMonthRepository.queryAll();
    }

    private void initItemList()
    {
        itemList = Transformations.switchMap(currentYearAndMonth, yearAndMonth ->
        {
            String year = yearAndMonth.getYear().substring(0, yearAndMonth.getYear().length() - 1);
            String month = yearAndMonth.getMonth().substring(0, yearAndMonth.getMonth().length() - 1);
            return txnRvItemRepository.queryAllByMonth(year, month);
        });
    }

    private void initMonthIncomeAndExpenditure()
    {
        monthIncome = Transformations.switchMap(currentYearAndMonth, yearAndMonth ->
        {
            String year = yearAndMonth.getYear().substring(0, yearAndMonth.getYear().length() - 1);
            String month = yearAndMonth.getMonth().substring(0, yearAndMonth.getMonth().length() - 1);
            return txnRvItemRepository.queryIncomeByMonth(year, month);
        });
        monthExpenditure = Transformations.switchMap(currentYearAndMonth, yearAndMonth ->
        {
            String year = yearAndMonth.getYear().substring(0, yearAndMonth.getYear().length() - 1);
            String month = yearAndMonth.getMonth().substring(0, yearAndMonth.getMonth().length() - 1);
            return txnRvItemRepository.queryExpenseByMonth(year, month);
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

    public void deleteTxn(int id)
    {
        txnRepository.deleteById(id);
    }

    public LiveData<Txn> queryTxn(int id)
    {
        return txnRepository.queryById(id);
    }

    public void insertTxn(Txn txn)
    {
        txnRepository.insert(txn);
    }

    public LiveData<List<TxnRvItem>> getItemList()
    {
        return itemList;
    }

    public MutableLiveData<YearAndMonth> getCurrentYearAndMonth()
    {
        return currentYearAndMonth;
    }

    public LiveData<Double> getMonthIncome()
    {
        return monthIncome;
    }

    public LiveData<Double> getMonthExpenditure()
    {
        return monthExpenditure;
    }

    public void observeYearMonthList(LifecycleOwner owner)
    {
        yearMonthList.observe(owner, yearMonths ->
        {
            yearList.clear();
            monthList.clear();
            for (YearMonth yearMonth : yearMonths)
            {
                String year = yearMonth.getYear() + "年";
                String month = yearMonth.getMonth() + "月";
                if (!yearList.contains(year))
                {
                    yearList.add(year);
                    monthList.add(new ArrayList<>());
                }
                monthList.get(yearList.indexOf(year)).add(month);
            }
            initCurrentYearAndMonth();
        });
    }

    public void initCurrentYearAndMonth()
    {
        if (yearList.isEmpty() || monthList.isEmpty())
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
            if (currentYearAndMonth.getValue() != null) return;
            String year = yearList.get(yearList.size() - 1);
            String month = monthList.get(yearList.size() - 1).get(monthList.get(yearList.size() - 1).size() - 1);
            currentYearAndMonth.setValue(new YearAndMonth(year, month));
        }
    }

    public int getNavigationBarHeight()
    {
        int height = 0;
        Resources resources = BaseApplication.getContext().getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
}