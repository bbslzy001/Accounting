package com.example.accounting.ui.viewmodel.fragment.statistics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.accounting.model.repository.HomeRvItemRepository;
import com.example.accounting.model.room.bean.HomeRvItem;

import java.util.List;

public class ListStatisticsFragmentViewModel extends ViewModel
{
    private MutableLiveData<Integer> currentYear;
    private MutableLiveData<Integer> currentMonth;
    private final LiveData<List<HomeRvItem>> items;
    private final HomeRvItemRepository homeRvItemRepository = new HomeRvItemRepository();

    public ListStatisticsFragmentViewModel()
    {
        items = homeRvItemRepository.queryAll();
    }

    public LiveData<List<HomeRvItem>> getItems()
    {
        return items;
    }
}