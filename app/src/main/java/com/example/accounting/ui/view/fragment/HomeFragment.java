package com.example.accounting.ui.view.fragment;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseFragment;
import com.example.accounting.databinding.FragmentHomeBinding;
import com.example.accounting.ui.viewmodel.fragment.HomeFragViewModel;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeFragViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_home;
    }

    @Override
    protected Class<HomeFragViewModel> getViewModelClass()
    {
        return HomeFragViewModel.class;
    }

    @Override
    protected int getViewModelVariableId()
    {
        return BR.viewModel;
    }
}