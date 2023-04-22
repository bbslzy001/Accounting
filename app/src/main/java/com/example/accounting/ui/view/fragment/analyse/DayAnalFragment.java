package com.example.accounting.ui.view.fragment.analyse;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseFragment;
import com.example.accounting.databinding.FragmentAnalDayBinding;
import com.example.accounting.ui.viewmodel.fragment.analyse.DayAnalFragViewModel;

public class DayAnalFragment extends BaseFragment<FragmentAnalDayBinding, DayAnalFragViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_anal_day;
    }

    @Override
    protected Class<DayAnalFragViewModel> getViewModelClass()
    {
        return DayAnalFragViewModel.class;
    }

    @Override
    protected int getViewModelVariableId()
    {
        return BR.viewModel;
    }
}