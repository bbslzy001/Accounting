package com.example.accounting.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.accounting.model.repository.AccountTypeRepository;
import com.example.accounting.model.room.bean.AccountType;
import com.example.accounting.utils.DisplayUtils;

import java.util.List;

public class MainActivityViewModel extends ViewModel
{
    private final LiveData<List<AccountType>> accountTypes;
    private final MutableLiveData<Integer> statusHeight = new MutableLiveData<>();
    private final MutableLiveData<Integer> navigationHeight = new MutableLiveData<>();

    public MainActivityViewModel()
    {
        AccountTypeRepository accountTypeRepository = new AccountTypeRepository();
        accountTypes = accountTypeRepository.queryAll();
    }

    public LiveData<List<AccountType>> getAccountTypes()
    {
        return accountTypes;
    }

    public LiveData<Integer> getStatusHeight()
    {
        statusHeight.setValue(DisplayUtils.getStatusBarHeight());
        return statusHeight;
    }

    public LiveData<Integer> getNavigationHeight()
    {
        navigationHeight.setValue(DisplayUtils.getNavigationBarHeight());
        return navigationHeight;
    }
}