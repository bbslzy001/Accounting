package com.example.accounting.ui.view.fragment;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseFragment;
import com.example.accounting.databinding.FragmentAnalBinding;
import com.example.accounting.ui.viewmodel.fragment.AnalFragViewModel;
import com.example.accounting.utils.adapter.AnalVpAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

public class AnalFragment extends BaseFragment<FragmentAnalBinding, AnalFragViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_anal;
    }

    @Override
    protected Class<AnalFragViewModel> getViewModelClass()
    {
        return AnalFragViewModel.class;
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

        initTab();
    }

    /**
     * 初始化标签栏
     */
    private void initTab()
    {
        binding.viewPager.setAdapter(new AnalVpAdapter(requireActivity()));

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) ->
        {
            switch (position)
            {
                case 0 -> tab.setText(this.getString(R.string.day_anal));
                case 1 -> tab.setText(this.getString(R.string.month_anal));
                case 2 -> tab.setText(this.getString(R.string.year_anal));
            }
        }).attach();
    }
}