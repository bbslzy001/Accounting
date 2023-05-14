package com.example.accounting.ui.view.activity;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseActivity;
import com.example.accounting.databinding.ActivityAddTxnBinding;
import com.example.accounting.ui.viewmodel.activity.AddTxnActViewModel;

public class AddTxnActivity extends BaseActivity<ActivityAddTxnBinding, AddTxnActViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return  R.layout.activity_add_txn;
    }

    @Override
    protected Class<AddTxnActViewModel> getViewModelClass()
    {
        return AddTxnActViewModel.class;
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