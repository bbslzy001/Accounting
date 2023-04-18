package com.example.accounting.ui.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.accounting.R;
import com.example.accounting.databinding.FragmentAnalyseBinding;
import com.example.accounting.ui.viewmodel.fragment.AnalyseFragmentViewModel;
import com.example.accounting.utils.adapter.AnalyseFragmentViewPagerAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

public class AnalyseFragment extends Fragment
{
    private FragmentAnalyseBinding binding;
    private AnalyseFragmentViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_analyse, container, false);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(AnalyseFragmentViewModel.class);
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
        binding.viewPager.setAdapter(new AnalyseFragmentViewPagerAdapter(requireActivity()));

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) ->
        {
            switch (position)
            {
                case 0 -> tab.setText(this.getString(R.string.day_analyse));
                case 1 -> tab.setText(this.getString(R.string.month_analyse));
                case 2 -> tab.setText(this.getString(R.string.year_analyse));
            }
        }).attach();
    }
}