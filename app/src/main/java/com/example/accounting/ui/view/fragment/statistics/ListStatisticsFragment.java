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
import com.example.accounting.databinding.FragmentStatisticsListBinding;
import com.example.accounting.ui.viewmodel.fragment.statistics.ListStatisticsFragmentViewModel;

public class ListStatisticsFragment extends Fragment
{
    private FragmentStatisticsListBinding binding;
    private ListStatisticsFragmentViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistics_list, container, false);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(ListStatisticsFragmentViewModel.class);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
}