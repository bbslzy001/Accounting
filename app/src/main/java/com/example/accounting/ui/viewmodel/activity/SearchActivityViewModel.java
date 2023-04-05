package com.example.accounting.ui.viewmodel.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.accounting.utils.DisplayUtils;

public class SearchActivityViewModel extends ViewModel
{
    private final MutableLiveData<Integer> statusHeight = new MutableLiveData<>();
    private final MutableLiveData<Integer> navigationHeight = new MutableLiveData<>();

    public SearchActivityViewModel()
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