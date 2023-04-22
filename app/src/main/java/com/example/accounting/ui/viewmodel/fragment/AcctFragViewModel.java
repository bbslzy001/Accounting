package com.example.accounting.ui.viewmodel.fragment;

import androidx.lifecycle.LiveData;

import com.example.accounting.base.BaseFragmentViewModel;
import com.example.accounting.model.repository.AcctTypeRepository;
import com.example.accounting.model.room.bean.AcctType;

import java.util.List;

public class AcctFragViewModel extends BaseFragmentViewModel
{
    private final LiveData<List<AcctType>> items;
    private final AcctTypeRepository acctTypeRepository = new AcctTypeRepository();

    public AcctFragViewModel()
    {
        super();

        items = acctTypeRepository.queryAll();
    }

    public LiveData<List<AcctType>> getItems()
    {
        return items;
    }

    public void addAcctType()
    {
        acctTypeRepository.insert(new AcctType(0, "信用卡", 99.99));
        acctTypeRepository.insert(new AcctType(0, "信用卡", -99.99));
    }
}