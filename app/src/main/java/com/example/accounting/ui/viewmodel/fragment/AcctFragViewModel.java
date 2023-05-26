package com.example.accounting.ui.viewmodel.fragment;

import androidx.lifecycle.LiveData;

import com.example.accounting.base.BaseFragmentViewModel;
import com.example.accounting.model.repository.AcctRepository;
import com.example.accounting.model.room.bean.Acct;

import java.util.List;

public class AcctFragViewModel extends BaseFragmentViewModel
{
    private final LiveData<List<Acct>> items;
    private final AcctRepository acctRepository = new AcctRepository();

    public AcctFragViewModel()
    {
        super();

        items = acctRepository.queryAll();
    }

    public LiveData<List<Acct>> getItems()
    {
        return items;
    }
}