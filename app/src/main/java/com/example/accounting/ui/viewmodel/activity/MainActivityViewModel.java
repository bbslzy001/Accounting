package com.example.accounting.ui.viewmodel.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.accounting.model.repository.AccountTypeRepository;
import com.example.accounting.model.repository.TradeTypeRepository;
import com.example.accounting.model.room.bean.AccountType;
import com.example.accounting.model.room.bean.TradeType;
import com.example.accounting.utils.DisplayUtils;

public class MainActivityViewModel extends ViewModel
{
    private final MutableLiveData<Integer> statusHeight = new MutableLiveData<>();
    private final MutableLiveData<Integer> navigationHeight = new MutableLiveData<>();
    private final MutableLiveData<String> topAppBarTitle = new MutableLiveData<>();
    private final AccountTypeRepository accountTypeRepository = new AccountTypeRepository();
    private final TradeTypeRepository tradeTypeRepository = new TradeTypeRepository();

    public MainActivityViewModel()
    {
        statusHeight.setValue(DisplayUtils.getStatusBarHeight());
        navigationHeight.setValue(DisplayUtils.getNavigationBarHeight());
    }

    public LiveData<Integer> getStatusHeight()
    {
        return statusHeight;
    }

    public LiveData<Integer> getNavigationHeight()
    {
        return navigationHeight;
    }

    public MutableLiveData<String> getTopAppBarTitle()
    {
        return topAppBarTitle;
    }

    public void fakeData()
    {
        tradeTypeRepository.insert(new TradeType(0,"吃饭"));
        accountTypeRepository.insert(new AccountType(0, "工商银行储蓄卡",100.0));
        accountTypeRepository.insert(new AccountType(0, "微信",100.0));
        accountTypeRepository.insert(new AccountType(0, "支付宝",100.0));
    }
}