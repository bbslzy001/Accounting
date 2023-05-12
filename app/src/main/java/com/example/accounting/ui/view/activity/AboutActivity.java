package com.example.accounting.ui.view.activity;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseActivity;
import com.example.accounting.databinding.ActivityAboutBinding;
import com.example.accounting.ui.viewmodel.activity.AboutActViewModel;

public class AboutActivity extends BaseActivity<ActivityAboutBinding, AboutActViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_about;
    }

    @Override
    protected Class<AboutActViewModel> getViewModelClass()
    {
        return AboutActViewModel.class;
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