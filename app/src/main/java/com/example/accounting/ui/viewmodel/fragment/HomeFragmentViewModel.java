package com.example.accounting.ui.viewmodel.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.accounting.model.repository.TradeInfoRepository;
import com.example.accounting.model.room.bean.HomeRecyclerViewItem;
import com.example.accounting.model.repository.HomeRecyclerViewItemRepository;
import com.example.accounting.model.room.bean.TradeInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HomeFragmentViewModel extends ViewModel
{
    private final LiveData<List<HomeRecyclerViewItem>> items;
    private final HomeRecyclerViewItemRepository homeRecyclerViewItemRepository = new HomeRecyclerViewItemRepository();

    public HomeFragmentViewModel()
    {
        items = homeRecyclerViewItemRepository.queryAll();
    }

    public LiveData<List<HomeRecyclerViewItem>> getItems()
    {
        return items;
    }

    public void addTradeInfo()
    {
        TradeInfoRepository tradeInfoRepository = new TradeInfoRepository();
        tradeInfoRepository.insert(new TradeInfo(0, 10, "2023/04/01", new SimpleDateFormat("HH:mm:ss").format(new Date()), "nothing", 1, 1));
    }
}