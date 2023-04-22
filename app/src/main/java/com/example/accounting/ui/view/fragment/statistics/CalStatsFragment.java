package com.example.accounting.ui.view.fragment.statistics;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseFragment;
import com.example.accounting.databinding.FragmentStatsCalBinding;
import com.example.accounting.ui.viewmodel.fragment.statistics.CalStatsFragViewModel;

public class CalStatsFragment extends BaseFragment<FragmentStatsCalBinding, CalStatsFragViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_stats_cal;
    }

    @Override
    protected Class<CalStatsFragViewModel> getViewModelClass()
    {
        return CalStatsFragViewModel.class;
    }

    @Override
    protected int getViewModelVariableId()
    {
        return BR.viewModel;
    }
}