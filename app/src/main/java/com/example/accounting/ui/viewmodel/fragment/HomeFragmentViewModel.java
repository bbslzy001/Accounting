package com.example.accounting.ui.viewmodel.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.accounting.model.repository.TradeInfoRepository;
import com.example.accounting.model.room.bean.HomeRvItem;
import com.example.accounting.model.repository.HomeRvItemRepository;
import com.example.accounting.model.room.bean.TradeInfo;

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

    public void addTradeInfo()
    {
        TradeInfoRepository tradeInfoRepository = new TradeInfoRepository();
        tradeInfoRepository.insert(new TradeInfo(0, 10, "2023/04/01", "20:55:00", "nothing", 1, 1));
        tradeInfoRepository.insert(new TradeInfo(0, 10, "2023/04/02", "21:55:55", "nothing", 1, 1));
        tradeInfoRepository.insert(new TradeInfo(0, 10, "2023/04/03", "22:55:00", "nothing", 1, 1));
        tradeInfoRepository.insert(new TradeInfo(0, -10, "2023/04/03", "22:55:00", "nothing", 1, 1));
        tradeInfoRepository.insert(new TradeInfo(0, -10, "2023/04/03", "22:55:00", "nothing", 1, 1));
        tradeInfoRepository.insert(new TradeInfo(0, -10, "2023/04/03", "22:55:00", "nothing", 1, 1));
    }
}