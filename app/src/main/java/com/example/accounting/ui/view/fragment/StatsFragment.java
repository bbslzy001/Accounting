package com.example.accounting.ui.view.fragment;

import androidx.lifecycle.ViewModelProvider;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseFragment;
import com.example.accounting.databinding.FragmentStatsBinding;
import com.example.accounting.ui.viewmodel.ShareViewModel;
import com.example.accounting.ui.viewmodel.fragment.StatsFragViewModel;
import com.example.accounting.utils.adapter.StatsVpAdapter;

public class StatsFragment extends BaseFragment<FragmentStatsBinding, StatsFragViewModel>
{
    private ShareViewModel shareViewModel;

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_stats;
    }

    @Override
    protected Class<StatsFragViewModel> getViewModelClass()
    {
        return StatsFragViewModel.class;
    }

    @Override
    protected int getViewModelVariableId()
    {
        return BR.viewModel;
    }

    @Override
    protected void initView()
    {
        super.initView();

        initViewPager();
    }

    private void initViewPager()
    {
        binding.viewPager.setAdapter(new StatsVpAdapter(requireActivity()));
        binding.viewPager.setUserInputEnabled(false);

        // 为了获取相同的 ViewModel 实例，需要使用相同的 LifecycleOwner，
        // MainActivity 的 LifecycleOwner 和 StatisticsFragment 的 LifecycleOwner 不同，
        // 因此，此处传递的是 MainActivity 的 LifecycleOwner
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);
        shareViewModel.getButtonState().observe(getViewLifecycleOwner(), aBoolean ->
        {
            int currentItem = binding.viewPager.getCurrentItem();
            if (currentItem == 0) binding.viewPager.setCurrentItem(1, false);
            else binding.viewPager.setCurrentItem(0, false);
        });
    }
}