package com.example.accounting.ui.viewmodel.activity;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.viewpager2.widget.ViewPager2;

import com.example.accounting.R;
import com.example.accounting.base.BaseActivityViewModel;
import com.example.accounting.model.repository.ChipRepository;
import com.example.accounting.model.room.bean.Chip;

public class MainActViewModel extends BaseActivityViewModel
{
    private final MutableLiveData<Integer> topAppBarTitle = new MutableLiveData<>(R.string.app_name);
    private final MutableLiveData<Integer> statsFragState = new MutableLiveData<>(R.string.list_stats);
    private final ChipRepository chipRepository = new ChipRepository();

    public MainActViewModel()
    {
        super();
    }

    public MutableLiveData<Integer> getTopAppBarTitle()
    {
        return topAppBarTitle;
    }

    public void initData()
    {
        for (int i = 0; i < 15; ++i)
        {
            chipRepository.insert(new Chip(0, true));
        }
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