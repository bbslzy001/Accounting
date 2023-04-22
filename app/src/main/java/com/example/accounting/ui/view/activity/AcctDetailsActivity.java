package com.example.accounting.ui.view.activity;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseActivity;
import com.example.accounting.databinding.ActivityAcctDetailsBinding;
import com.example.accounting.ui.viewmodel.activity.AcctDetailsActViewModel;

public class AcctDetailsActivity extends BaseActivity<ActivityAcctDetailsBinding, AcctDetailsActViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_acct_details;
    }

    @Override
    protected Class<AcctDetailsActViewModel> getViewModelClass()
    {
        return AcctDetailsActViewModel.class;
    }

    @Override
    protected int getViewModelVariableId()
    {
        return BR.viewModel;
    }
}