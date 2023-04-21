package com.example.accounting.utils.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.accounting.ui.view.fragment.analyse.DayAnalyseFragment;
import com.example.accounting.ui.view.fragment.analyse.MonthAnalyseFragment;
import com.example.accounting.ui.view.fragment.analyse.YearAnalyseFragment;

import java.util.List;

public class AnalyseVpAdapter extends FragmentStateAdapter
{
    private final List<Fragment> fragmentList = List.of(
            new DayAnalyseFragment(),
            new MonthAnalyseFragment(),
            new YearAnalyseFragment()
    );

    public AnalyseVpAdapter(@NonNull FragmentActivity fragmentActivity)
    {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position)
    {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount()
    {
        return fragmentList.size();
    }
}