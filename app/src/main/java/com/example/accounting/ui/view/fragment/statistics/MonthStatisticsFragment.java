package com.example.accounting.ui.view.fragment.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.accounting.R;
import com.example.accounting.databinding.FragmentStatisticsMonthBinding;
import com.example.accounting.ui.viewmodel.fragment.statistics.MonthStatisticsFragmentViewModel;

public class MonthStatisticsFragment extends Fragment
{
    private FragmentStatisticsMonthBinding binding;
    private MonthStatisticsFragmentViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistics_month, container, false);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(MonthStatisticsFragmentViewModel.class);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
}