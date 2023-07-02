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
import com.example.accounting.ui.viewmodel.fragment.analyse.DayAnalFragViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.LineData;
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
    private BarChart mBarChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anal_day, container, false);

        mBarChart = view.findViewById(R.id.bar_chart);

        DayAnalFragViewModel viewModel = new ViewModelProvider(this).get(DayAnalFragViewModel.class);
        BarData barData = DayAnalFragViewModel.generateBarChartData();
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Mon", "Tues", "Weds", "Thurs", "Fri","Sat","Sun"}));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mBarChart.setData(barData);
        mBarChart.invalidate();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"}));


        return view;
    }
}