package com.example.accounting.ui.view.activity;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseActivity;
import com.example.accounting.databinding.ActivitySettingBinding;
import com.example.accounting.ui.viewmodel.activity.SettingActViewModel;

public class SettingActivity extends BaseActivity<ActivitySettingBinding, SettingActViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_setting;
    }

    @Override
    protected Class<SettingActViewModel> getViewModelClass()
    {
        return SettingActViewModel.class;
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
    }
}