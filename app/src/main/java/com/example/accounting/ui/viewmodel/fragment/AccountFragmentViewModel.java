package com.example.accounting.ui.viewmodel.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.accounting.model.repository.AccountTypeRepository;
import com.example.accounting.model.room.bean.AccountType;

import java.util.List;

public class AccountFragmentViewModel extends ViewModel
{
    private final LiveData<List<AccountType>> items;
    private final AccountTypeRepository accountTypeRepository = new AccountTypeRepository();

    public AccountFragmentViewModel()
    {
        items = accountTypeRepository.queryAll();
    }

    public LiveData<List<AccountType>> getItems()
    {
        return items;
    }

    public void addAccountType()
    {
        accountTypeRepository.insert(new AccountType(0, "信用卡", 99.99));
        accountTypeRepository.insert(new AccountType(0, "信用卡", -99.99));
    }
}