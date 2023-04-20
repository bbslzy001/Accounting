package com.example.accounting.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel
{
    private final MutableLiveData<Boolean> buttonState = new MutableLiveData<>(true);

    public ShareViewModel()
    {
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