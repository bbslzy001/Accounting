package com.example.accounting.ui.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.accounting.R;
import com.example.accounting.databinding.FragmentStatisticsBinding;
import com.example.accounting.ui.viewmodel.fragment.StatisticsFragmentViewModel;
import com.example.accounting.utils.adapter.StatisticsFragmentViewPagerAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

public class StatisticsFragment extends Fragment
{
    private FragmentStatisticsBinding binding;
    private StatisticsFragmentViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistics, container, false);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(StatisticsFragmentViewModel.class);
        binding.setViewModel(viewModel);

        initTab();

        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化标签栏
     */
    private void initTab()
    {
        binding.viewPager.setAdapter(new StatisticsFragmentViewPagerAdapter(requireActivity()));

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) ->
        {
            switch (position)
            {
                case 0 -> tab.setText(this.getString(R.string.week_statistics));
                case 1 -> tab.setText(this.getString(R.string.month_statistics));
                case 2 -> tab.setText(this.getString(R.string.year_statistics));
            }
        }).attach();
    }
}