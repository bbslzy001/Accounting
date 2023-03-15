package com.example.accounting.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.accounting.ui.view.fragment.HomeFragment;
import com.example.accounting.ui.view.fragment.AccountFragment;
import com.example.accounting.ui.view.fragment.StatisticsFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter
{
    private final List<Fragment> fragmentList = List.of(
            new HomeFragment(),
            new StatisticsFragment(),
            new AccountFragment()
    );

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity)
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