package com.example.accounting.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.accounting.utils.DisplayUtils;

public class MainActivityViewModel extends ViewModel
{

    private final MutableLiveData<Integer> statusHeight = new MutableLiveData<>();
    private final MutableLiveData<Integer> navigationHeight = new MutableLiveData<>();

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
}