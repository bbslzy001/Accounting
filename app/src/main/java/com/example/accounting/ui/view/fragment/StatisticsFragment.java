package com.example.accounting.ui.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.accounting.R;
import com.example.accounting.databinding.FragmentStatisticsBinding;
import com.example.accounting.ui.viewmodel.fragment.StatisticsFragmentViewModel;

public class StatisticsFragment extends Fragment
{
    private FragmentStatisticsBinding binding;
    private StatisticsFragmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistics, container, false);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(StatisticsFragmentViewModel.class);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
}