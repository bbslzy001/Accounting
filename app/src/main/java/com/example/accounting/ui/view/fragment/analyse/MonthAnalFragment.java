package com.example.accounting.ui.view.fragment.analyse;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseFragment;
import com.example.accounting.databinding.FragmentAnalMonthBinding;
import com.example.accounting.ui.viewmodel.fragment.analyse.MonthAnalFragViewModel;

public class MonthAnalFragment extends BaseFragment<FragmentAnalMonthBinding, MonthAnalFragViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_anal_month;
    }

    @Override
    protected Class<MonthAnalFragViewModel> getViewModelClass()
    {
        return MonthAnalFragViewModel.class;
    }

    @Override
    protected int getViewModelVariableId()
    {
        return BR.viewModel;
    }
}