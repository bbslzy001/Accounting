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
import com.example.accounting.databinding.FragmentHomeBinding;
import com.example.accounting.ui.viewmodel.fragment.HomeFragViewModel;

public class HomeFragment extends Fragment
{
    private FragmentHomeBinding binding;
    private HomeFragViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(HomeFragViewModel.class);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
}