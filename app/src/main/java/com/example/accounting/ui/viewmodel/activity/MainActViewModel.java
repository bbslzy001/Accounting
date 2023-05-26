package com.example.accounting.ui.viewmodel.activity;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.viewpager2.widget.ViewPager2;

import com.example.accounting.R;
import com.example.accounting.base.BaseActivityViewModel;
import com.example.accounting.model.repository.TxnTypeRepository;
import com.example.accounting.model.room.bean.TxnType;

public class MainActViewModel extends BaseActivityViewModel
{
    private final MutableLiveData<Integer> topAppBarTitle = new MutableLiveData<>(R.string.app_name);
    private final MutableLiveData<Integer> statsFragState = new MutableLiveData<>(R.string.list_stats);
    private final TxnTypeRepository txnTypeRepository = new TxnTypeRepository();

    public MainActViewModel()
    {
        super();
    }

    public MutableLiveData<Integer> getTopAppBarTitle()
    {
        return topAppBarTitle;
    }

    public void fakeData()
    {
        txnTypeRepository.insert(new TxnType(0, "吃饭"));
    }

    public void updateStatsFragState()
    {
        Integer state = statsFragState.getValue();
        state = state == null || state == R.string.list_stats ? R.string.cal_stats : R.string.list_stats;
        statsFragState.setValue(state);
    }

    public MutableLiveData<Integer> getStatsFragState()
    {
        return statsFragState;
    }

    public void observeStatsFragState(LifecycleOwner owner, ViewPager2 viewPager)
    {
        statsFragState.observe(owner, s ->
        {
            if (s == R.string.list_stats) viewPager.setCurrentItem(0, false);
            else viewPager.setCurrentItem(1, false);
        });
    }
}