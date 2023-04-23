package com.example.accounting.utils.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.accounting.ui.view.fragment.statistics.CalStatsFragment;
import com.example.accounting.ui.view.fragment.statistics.ListStatsFragment;

import java.util.List;

public class StatsVpAdapter extends FragmentStateAdapter
{
    private final List<Fragment> fragmentList = List.of(
            new CalStatsFragment(),
            new ListStatsFragment()
    );

    public StatsVpAdapter(@NonNull FragmentActivity fragmentActivity)
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