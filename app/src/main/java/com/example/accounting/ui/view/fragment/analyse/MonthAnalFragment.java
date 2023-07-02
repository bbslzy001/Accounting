package com.example.accounting.ui.view.fragment.analyse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseFragment;
import com.example.accounting.databinding.FragmentAnalMonthBinding;
import com.example.accounting.ui.viewmodel.fragment.analyse.DayAnalFragViewModel;
import com.example.accounting.ui.viewmodel.fragment.analyse.MonthAnalFragViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

public class MonthAnalFragment extends BaseFragment<FragmentAnalMonthBinding, MonthAnalFragViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_anal_month;
    }
    @Override
    protected Class<MonthAnalFragViewModel> getViewModelClass()
    {
        return MonthAnalFragViewModel.class;
    }

    @Override
    protected int getViewModelVariableId()
    {
        return BR.viewModel;
    }
    private BarChart mBarChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anal_month, container, false);

        mBarChart = view.findViewById(R.id.mbar_chart);

        DayAnalFragViewModel viewModel = new ViewModelProvider(this).get(DayAnalFragViewModel.class);
        BarData barData = MonthAnalFragViewModel.generateBarChartData();
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mBarChart.setData(barData);
        mBarChart.invalidate();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"一月", "二月", "三月", "四月", "五月", "六月", "七月","八月","九月","十月","十一月","十二月"}));


        return view;
    }
}