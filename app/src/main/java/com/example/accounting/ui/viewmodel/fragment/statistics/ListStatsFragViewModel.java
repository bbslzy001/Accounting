package com.example.accounting.ui.viewmodel.fragment.statistics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.accounting.base.BaseFragmentViewModel;
import com.example.accounting.model.repository.TxnRvItemRepository;
import com.example.accounting.model.repository.YearMonthRepository;
import com.example.accounting.model.room.bean.TxnRvItem;
import com.example.accounting.model.room.bean.YearMonth;

import java.util.List;

public class ListStatsFragViewModel extends BaseFragmentViewModel
{
    private final MutableLiveData<Double> monthIncome = new MutableLiveData<>();
    private final MutableLiveData<Double> monthExpenditure = new MutableLiveData<>();
    private final MutableLiveData<String> currentYear = new MutableLiveData<>();
    private final MutableLiveData<String> currentMonth = new MutableLiveData<>();
    private final LiveData<List<TxnRvItem>> items;
    private final LiveData<List<YearMonth>> yearMonths;
    private final TxnRvItemRepository txnRvItemRepository = new TxnRvItemRepository();
    private final YearMonthRepository yearMonthRepository = new YearMonthRepository();

    public ListStatsFragViewModel()
    {
        super();

        items = txnRvItemRepository.queryAll();
        yearMonths = yearMonthRepository.queryAll();
    }

    public LiveData<List<TxnRvItem>> getItems()
    {
        return items;
    }

    public LiveData<List<YearMonth>> getYearMonths()
    {
        return yearMonths;
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

    public void setCurrentYear(String currentYear)
    {
        this.currentYear.setValue(currentYear);
    }

    public void setCurrentMonth(String currentMonth)
    {
        this.currentMonth.setValue(currentMonth);
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