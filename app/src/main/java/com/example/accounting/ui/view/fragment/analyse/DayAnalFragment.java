package com.example.accounting.ui.view.fragment.analyse;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseFragment;
import com.example.accounting.databinding.FragmentAnalDayBinding;
import com.example.accounting.ui.viewmodel.fragment.analyse.DayAnalFragViewModel;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

public class DayAnalFragment extends BaseFragment<FragmentAnalDayBinding, DayAnalFragViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_anal_day;
    }

    @Override
    protected Class<DayAnalFragViewModel> getViewModelClass()
    {
        return DayAnalFragViewModel.class;
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
        initIncomeChart();
        initExpenseChart();
    }

    private void initIncomeChart()
    {
        // 设置x轴
        XAxis xAxis = binding.incomeChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Mon", "Tues","Weds","Thurs","Fri","Sat","Sun"}));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        // 填充数据
        binding.incomeChart.setData(viewModel.getIncomeData());

        // 设置空白描述
        Description description = new Description();
        description.setText("");
        binding.incomeChart.setDescription(description);
    }

    private void initExpenseChart()
    {
        // 设置x轴
        XAxis xAxis = binding.expenseChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Mon", "Tues","Weds","Thurs","Fri","Sat","Sun"}));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(7);

        // 填充数据
        binding.expenseChart.setData(viewModel.getExpenseData());

        // 设置空白描述
        Description description = new Description();
        description.setText("");
        binding.expenseChart.setDescription(description);
    }
}