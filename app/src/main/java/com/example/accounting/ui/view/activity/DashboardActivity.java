package com.example.accounting.ui.view.activity;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseActivity;
import com.example.accounting.databinding.ActivityDashboardBinding;
import com.example.accounting.ui.viewmodel.activity.DashboardActViewModel;

public class DashboardActivity extends BaseActivity<ActivityDashboardBinding, DashboardActViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_dashboard;
    }

    @Override
    protected Class<DashboardActViewModel> getViewModelClass()
    {
        return DashboardActViewModel.class;
    }

    @Override
    protected int getViewModelVariableId()
    {
        return BR.viewModel;
    }
}