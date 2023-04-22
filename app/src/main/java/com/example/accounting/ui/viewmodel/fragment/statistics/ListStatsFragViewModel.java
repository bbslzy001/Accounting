package com.example.accounting.ui.viewmodel.fragment.statistics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.accounting.base.BaseFragmentViewModel;
import com.example.accounting.model.repository.TxnRvItemRepository;
import com.example.accounting.model.room.bean.TxnRvItem;

import java.util.List;

public class ListStatsFragViewModel extends BaseFragmentViewModel
{
    private MutableLiveData<Integer> currentYear;
    private MutableLiveData<Integer> currentMonth;
    private final LiveData<List<TxnRvItem>> items;
    private final TxnRvItemRepository txnRvItemRepository = new TxnRvItemRepository();

    public ListStatsFragViewModel()
    {
        super();

        items = txnRvItemRepository.queryAll();
    }

    public LiveData<List<TxnRvItem>> getItems()
    {
        return items;
    }
}