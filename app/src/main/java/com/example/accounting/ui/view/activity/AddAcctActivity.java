package com.example.accounting.ui.view.activity;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseActivity;
import com.example.accounting.databinding.ActivityAddAcctBinding;
import com.example.accounting.ui.viewmodel.activity.AddAcctActViewModel;

public class AddAcctActivity extends BaseActivity<ActivityAddAcctBinding, AddAcctActViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return  R.layout.activity_add_acct;
    }

    @Override
    protected Class<AddAcctActViewModel> getViewModelClass()
    {
        return AddAcctActViewModel.class;
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
        initTopAppBar();
    }

    /**
     * 初始化顶部应用栏
     */
    private void initTopAppBar()
    {
        binding.topAppBar.setNavigationOnClickListener(view -> finish());
        binding.topAppBar.setOnMenuItemClickListener(menuItem ->
        {
            if (menuItem.getItemId() == R.id.add)
            {
                finish();
            }
            return false;
        });
    }
}