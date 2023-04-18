package com.example.accounting.utils.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.accounting.ui.view.fragment.statistics.CalendarStatisticsFragment;
import com.example.accounting.ui.view.fragment.statistics.ListStatisticsFragment;

import java.util.List;

public class StatisticsFragmentViewPagerAdapter extends FragmentStateAdapter
{
    private final List<Fragment> fragmentList = List.of(
            new CalendarStatisticsFragment(),
            new ListStatisticsFragment()
    );

    public StatisticsFragmentViewPagerAdapter(@NonNull FragmentActivity fragmentActivity)
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
