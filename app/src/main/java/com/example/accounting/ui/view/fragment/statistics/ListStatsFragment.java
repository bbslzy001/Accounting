package com.example.accounting.ui.view.fragment.statistics;

import android.view.View;
import android.widget.NumberPicker;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseFragment;
import com.example.accounting.databinding.FragmentStatsListBinding;
import com.example.accounting.model.room.bean.YearMonth;
import com.example.accounting.ui.viewmodel.fragment.statistics.ListStatsFragViewModel;
import com.example.accounting.utils.TxnRvItemDecoration;
import com.example.accounting.utils.adapter.TxnRvAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class ListStatsFragment extends BaseFragment<FragmentStatsListBinding, ListStatsFragViewModel>
{
    String[] yearArray;
    String[][] monthArray;
    int maxYearIndex;
    int maxMonthIndex;

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_stats_list;
    }

    @Override
    protected Class<ListStatsFragViewModel> getViewModelClass()
    {
        return ListStatsFragViewModel.class;
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

        initData();
        initDatePicker();
        initRecyclerView();
    }

    /**
     * 初始化滚动视图
     */
    private void initRecyclerView()
    {
        TxnRvAdapter adapter = new TxnRvAdapter();
        binding.recyclerView.setAdapter(adapter);
        TxnRvItemDecoration itemDecoration = new TxnRvItemDecoration(this.getContext(), adapter);
        binding.recyclerView.addItemDecoration(itemDecoration);
        binding.recyclerView.addOnItemTouchListener(itemDecoration);
    }

    private void initDatePicker()
    {
        binding.textButton.setOnClickListener(view ->
        {
            // 创建一个BottomSheetDialog对象
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());

            // 加载布局文件
            View bottomSheetView = getLayoutInflater().inflate(R.layout.date_picker_stats_list, null);

            // 创建年份和月份选择器
            NumberPicker yearPicker = bottomSheetView.findViewById(R.id.yearPicker);
            NumberPicker monthPicker = bottomSheetView.findViewById(R.id.monthPicker);

            // 设置年份选择器的范围和默认值
            yearPicker.setMinValue(0);
            yearPicker.setMaxValue(maxYearIndex);
            yearPicker.setDisplayedValues(yearArray);
            yearPicker.setValue(maxYearIndex);
            yearPicker.setWrapSelectorWheel(false);  // 条目大于3时是否开启循环滚动

            // 设置月份选择器的范围和默认值
            monthPicker.setMinValue(0);
            monthPicker.setMaxValue(maxMonthIndex);
            monthPicker.setDisplayedValues(monthArray[maxYearIndex]);
            monthPicker.setValue(maxMonthIndex);
            monthPicker.setWrapSelectorWheel(false);  // 条目大于3时是否开启循环滚动

            yearPicker.setOnValueChangedListener((picker, oldVal, newVal) ->
            {
                maxMonthIndex = monthArray[newVal].length - 1;
                monthPicker.setDisplayedValues(null);  // 先重置数组，避免发生下标越界
                monthPicker.setMaxValue(maxMonthIndex);
                monthPicker.setDisplayedValues(monthArray[newVal]);
                monthPicker.setValue(maxMonthIndex);
            });

            // 添加取消和确定按钮的点击事件
            bottomSheetView.findViewById(R.id.cancelButton).setOnClickListener(v -> bottomSheetDialog.dismiss());
            bottomSheetView.findViewById(R.id.okButton).setOnClickListener(v ->
            {
                // 获取选中的年份和月份
                int year = yearPicker.getValue();
                int month = monthPicker.getValue();

                viewModel.setCurrentYear(yearArray[year]);
                viewModel.setCurrentMonth(monthArray[year][month]);

                // 关闭底部抽屉
                bottomSheetDialog.dismiss();
            });

            // 设置底部抽屉的视图并显示它
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });
    }

    /**
     * 初始化数据（年份和月份）
     */
    private void initData()
    {
        viewModel.getYearMonths().observe(this, yearMonths ->
        {
            List<String> yearList = new ArrayList<>();
            List<List<String>> monthList = new ArrayList<>();
            for (YearMonth yearMonth : yearMonths)
            {
                String year = yearMonth.getYear() + "年";
                if (!yearList.contains(year))
                {
                    yearList.add(year);
                    List<String> months = new ArrayList<>();
                    months.add(yearMonth.getMonth() + "月");
                    monthList.add(months);
                }
                else
                {
                    int index = yearList.indexOf(year);
                    List<String> months = monthList.get(index);
                    months.add(yearMonth.getMonth() + "月");
                }
            }

            yearArray = yearList.toArray(new String[0]);
            monthArray = new String[monthList.size()][];
            for (int i = 0; i < monthList.size(); ++i)
            {
                List<String> months = monthList.get(i);
                monthArray[i] = months.toArray(new String[0]);
            }

            maxYearIndex = yearArray.length - 1;
            viewModel.setCurrentYear(yearArray[maxYearIndex]);
            maxMonthIndex = monthArray[maxYearIndex].length - 1;
            viewModel.setCurrentMonth(monthArray[maxYearIndex][maxMonthIndex]);
        });
    }
}