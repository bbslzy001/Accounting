package com.example.accounting.ui.viewmodel.fragment;

import androidx.lifecycle.LiveData;

import com.example.accounting.base.BaseFragmentViewModel;
import com.example.accounting.model.repository.ChipRepository;
import com.example.accounting.model.repository.TxnRepository;
import com.example.accounting.model.room.bean.CardInfo;
import com.example.accounting.model.room.bean.Chip;

import java.util.List;

public class HomeFragViewModel extends BaseFragmentViewModel
{
    private LiveData<List<Chip>> chipList;
    private LiveData<CardInfo> cardInfo;
    private final ChipRepository chipRepository = new ChipRepository();
    private final TxnRepository txnRepository = new TxnRepository();

    public HomeFragViewModel()
    {
        super();
        initChipList();
        initCardInfo();
    }

    private void initChipList()
    {
        chipList = chipRepository.queryAll();
    }

    private void initCardInfo()
    {
        cardInfo = txnRepository.queryCardInfo();
    }

    public LiveData<List<Chip>> getChipList()
    {
        return chipList;
    }

    public LiveData<CardInfo> getCardInfo()
    {
        return cardInfo;
    }
}