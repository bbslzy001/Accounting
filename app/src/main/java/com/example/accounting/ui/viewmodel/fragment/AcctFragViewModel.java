package com.example.accounting.ui.viewmodel.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.accounting.model.repository.AcctTypeRepository;
import com.example.accounting.model.room.bean.AcctType;

import java.util.List;

public class AcctFragViewModel extends ViewModel
{
    private final LiveData<List<AcctType>> items;
    private final AcctTypeRepository acctTypeRepository = new AcctTypeRepository();

    public AcctFragViewModel()
    {
        items = acctTypeRepository.queryAll();
    }

    public LiveData<List<AcctType>> getItems()
    {
        return items;
    }

    public void addAccountType()
    {
        acctTypeRepository.insert(new AcctType(0, "信用卡", 99.99));
        acctTypeRepository.insert(new AcctType(0, "信用卡", -99.99));
    }
}