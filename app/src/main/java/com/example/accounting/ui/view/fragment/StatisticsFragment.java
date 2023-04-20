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
import com.example.accounting.ui.viewmodel.ShareViewModel;
import com.example.accounting.ui.viewmodel.fragment.StatisticsFragmentViewModel;
import com.example.accounting.utils.adapter.StatisticsFragmentViewPagerAdapter;

public class StatisticsFragment extends Fragment
{
    private FragmentStatisticsBinding binding;
    private StatisticsFragmentViewModel viewModel;
    private ShareViewModel shareViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistics, container, false);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(StatisticsFragmentViewModel.class);
        binding.setViewModel(viewModel);

        binding.viewPager.setAdapter(new StatisticsFragmentViewPagerAdapter(requireActivity()));

        // 为了获取相同的 ViewModel 实例，需要使用相同的 LifecycleOwner，
        // MainActivity 的 LifecycleOwner 和 StatisticsFragment 的 LifecycleOwner 不同，
        // 因此，此处传递的是 MainActivity 的 LifecycleOwner
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);
        shareViewModel.getButtonState().observe(getViewLifecycleOwner(), aBoolean ->
        {
            int currentItem = binding.viewPager.getCurrentItem();
            if (currentItem == 0) binding.viewPager.setCurrentItem(1);
            else binding.viewPager.setCurrentItem(0);
        });

        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
}