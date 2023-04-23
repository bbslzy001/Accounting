package com.example.accounting.ui.view.activity;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseActivity;
import com.example.accounting.databinding.ActivitySearchBinding;
import com.example.accounting.ui.viewmodel.activity.SearchActViewModel;

public class SearchActivity extends BaseActivity<ActivitySearchBinding, SearchActViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_search;
    }

    @Override
    protected Class<SearchActViewModel> getViewModelClass()
    {
        return SearchActViewModel.class;
    }

    @Override
    protected int getViewModelVariableId()
    {
        return BR.viewModel;
    }
}