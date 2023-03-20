package com.example.accounting.ui.viewmodel.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.accounting.model.repository.AccountTypeRepository;
import com.example.accounting.model.room.bean.AccountType;

import java.util.List;

public class HomeFragmentViewModel extends ViewModel
{
    private final LiveData<List<AccountType>> accountTypes;

    public HomeFragmentViewModel()
    {
        AccountTypeRepository accountTypeRepository = new AccountTypeRepository();
        accountTypes = accountTypeRepository.queryAll();
    }

    public LiveData<List<AccountType>> getAccountTypes()
    {
        return accountTypes;
    }
}