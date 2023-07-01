package com.example.accounting.ui.view.activity;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseActivity;
import com.example.accounting.databinding.ActivityDashboardBinding;
import com.example.accounting.ui.viewmodel.activity.DashboardActViewModel;
import com.google.android.material.chip.Chip;

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

    @Override
    protected void initView()
    {
        super.initView();
        initTopAppBar();
        initChip();
    }

    /**
     * 初始化顶部应用栏
     */
    private void initTopAppBar()
    {
        binding.topAppBar.setNavigationOnClickListener(view -> finish());
    }

    private void initChip()
    {
        Chip[] chips = new Chip[]{
                binding.dayChip1, binding.dayChip2, binding.dayChip3, binding.dayChip4, binding.dayChip5, binding.dayChip6, binding.dayChip7, binding.dayChip8, binding.dayChip9,
                binding.monthChip1, binding.monthChip2, binding.monthChip3,
                binding.yearChip1, binding.yearChip2, binding.yearChip3,
        };
        for (int i = 0; i < chips.length; i++)
        {
            int finalI = i + 1;
            chips[i].setOnCheckedChangeListener((buttonView, isChecked) -> viewModel.updateChip(new com.example.accounting.model.room.bean.Chip(finalI, isChecked)));
        }
    }
}