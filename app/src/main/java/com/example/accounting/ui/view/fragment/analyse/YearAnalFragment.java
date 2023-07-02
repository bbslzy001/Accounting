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
    private BarChart mBarChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anal_year, container, false);

        mBarChart = view.findViewById(R.id.ybar_chart);

        DayAnalFragViewModel viewModel = new ViewModelProvider(this).get(DayAnalFragViewModel.class);
        BarData barData = YearAnalFragViewModel.generateBarChartData();
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mBarChart.setData(barData);
        mBarChart.invalidate();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"2021", "2022", "2023"}));


        return view;
    }
}