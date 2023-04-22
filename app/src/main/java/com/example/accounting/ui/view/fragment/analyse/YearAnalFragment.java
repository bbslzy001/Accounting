package com.example.accounting.ui.view.fragment.analyse;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseFragment;
import com.example.accounting.databinding.FragmentAnalYearBinding;
import com.example.accounting.ui.viewmodel.fragment.analyse.YearAnalFragViewModel;

public class YearAnalFragment extends BaseFragment<FragmentAnalYearBinding, YearAnalFragViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_anal_year;
    }

    @Override
    protected Class<YearAnalFragViewModel> getViewModelClass()
    {
        return YearAnalFragViewModel.class;
    }

    @Override
    protected int getViewModelVariableId()
    {
        return BR.viewModel;
    }
}