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
import com.example.accounting.databinding.FragmentAnalBinding;
import com.example.accounting.ui.viewmodel.fragment.AnalFragViewModel;
import com.example.accounting.utils.adapter.AnalVpAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

public class AnalFragment extends Fragment
{
    private FragmentAnalBinding binding;
    private AnalFragViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_anal, container, false);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(AnalFragViewModel.class);
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
        binding.viewPager.setAdapter(new AnalVpAdapter(requireActivity()));

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) ->
        {
            switch (position)
            {
                case 0 -> tab.setText(this.getString(R.string.day_anal));
                case 1 -> tab.setText(this.getString(R.string.month_anal));
                case 2 -> tab.setText(this.getString(R.string.year_anal));
            }
        }).attach();
    }
}