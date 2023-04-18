package com.example.accounting.ui.viewmodel.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.accounting.model.room.bean.HomeRvItem;
import com.example.accounting.model.repository.HomeRvItemRepository;

import java.util.List;

public class HomeFragmentViewModel extends ViewModel
{
    private final LiveData<Double> expenditureAmount;
    private final LiveData<Double> incomeAmount;
    private final LiveData<Double> totalAmount;
    private final LiveData<List<HomeRvItem>> items;
    private final HomeRvItemRepository homeRvItemRepository = new HomeRvItemRepository();

    public HomeFragmentViewModel()
    {
        expenditureAmount = homeRvItemRepository.queryExpenditureAmount();
        incomeAmount = homeRvItemRepository.queryIncomeAmount();
        totalAmount = homeRvItemRepository.queryTotalAmount();
        items = homeRvItemRepository.queryAll();
    }

    public LiveData<Double> getExpenditureAmount()
    {
        return expenditureAmount;
    }

    public LiveData<Double> getIncomeAmount()
    {
        return incomeAmount;
    }

    public LiveData<Double> getTotalAmount()
    {
        return totalAmount;
    }

    public LiveData<List<HomeRvItem>> getItems()
    {
        return items;
    }
}