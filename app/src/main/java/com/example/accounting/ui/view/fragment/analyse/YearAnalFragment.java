package com.example.accounting.ui.view.fragment.analyse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseFragment;
import com.example.accounting.databinding.FragmentAnalDayBinding;
import com.example.accounting.databinding.FragmentAnalYearBinding;
import com.example.accounting.ui.viewmodel.fragment.analyse.DayAnalFragViewModel;
import com.example.accounting.ui.viewmodel.fragment.analyse.YearAnalFragViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

public class YearAnalFragment extends BaseFragment<FragmentAnalDayBinding, YearAnalFragViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_anal_year;
    }

    @Override
    protected Class<YearAnalFragViewModel> getViewModelClass()
    {
        return YearAnalFragViewModel.class;
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
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"2018", "2019","2020","2021","2022","2023"}));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(6);

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
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"2018", "2019","2020","2021","2022","2023"}));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(6);

        // 填充数据
        binding.expenseChart.setData(viewModel.getExpenseData());

        // 设置空白描述
        Description description = new Description();
        description.setText("");
        binding.expenseChart.setDescription(description);
    }
}