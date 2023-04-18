package com.example.accounting.ui.viewmodel.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.accounting.model.repository.AccountTypeRepository;
import com.example.accounting.model.room.bean.AccountType;

import java.util.List;

public class AccountFragmentViewModel extends ViewModel
{
    private final LiveData<Double> totalAmount;
    private final LiveData<Double> negativeAmount;
    private final LiveData<Double> netAmount;
    private final LiveData<List<AccountType>> items;
    private final AccountTypeRepository accountTypeRepository = new AccountTypeRepository();

    public AccountFragmentViewModel()
    {
        totalAmount = accountTypeRepository.queryTotalAmount();
        negativeAmount = accountTypeRepository.queryNegativeAmount();
        netAmount = accountTypeRepository.queryNetAmount();
        items = accountTypeRepository.queryAll();
    }

    public LiveData<Double> getTotalAmount()
    {
        return totalAmount;
    }

    public LiveData<Double> getNegativeAmount()
    {
        return negativeAmount;
    }

    public LiveData<Double> getNetAmount()
    {
        return netAmount;
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