package com.example.accounting.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.accounting.base.BaseFragmentViewModel;

public class ShareViewModel extends BaseFragmentViewModel
{
    private final MutableLiveData<Boolean> buttonState = new MutableLiveData<>(true);

    public ShareViewModel()
    {
        super();
    }

    public LiveData<Boolean> getButtonState()
    {
        return buttonState;
    }

    public void setButtonState()
    {
        buttonState.postValue(Boolean.FALSE.equals(buttonState.getValue()));
    }
}