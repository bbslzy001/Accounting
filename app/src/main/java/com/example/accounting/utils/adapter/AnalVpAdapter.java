package com.example.accounting.utils.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.accounting.ui.view.fragment.analyse.DayAnalFragment;
import com.example.accounting.ui.view.fragment.analyse.MonthAnalFragment;
import com.example.accounting.ui.view.fragment.analyse.YearAnalFragment;

import java.util.List;

public class AnalVpAdapter extends FragmentStateAdapter
{
    private final List<Fragment> fragmentList = List.of(
            new DayAnalFragment(),
            new MonthAnalFragment(),
            new YearAnalFragment()
    );

    public AnalVpAdapter(@NonNull FragmentActivity fragmentActivity)
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