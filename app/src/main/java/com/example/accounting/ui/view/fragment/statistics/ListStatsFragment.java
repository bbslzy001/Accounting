package com.example.accounting.ui.view.fragment.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.accounting.R;
import com.example.accounting.databinding.FragmentStatsListBinding;
import com.example.accounting.ui.viewmodel.fragment.statistics.ListStatsFragViewModel;
import com.example.accounting.utils.TxnRvItemDecoration;
import com.example.accounting.utils.adapter.TxnRvAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Locale;

public class ListStatsFragment extends Fragment
{
    private FragmentStatsListBinding binding;
    private ListStatsFragViewModel viewModel;

    private int[] years;
    private int[][] months;
    private int currentYear;
    private int currentMonth;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stats_list, container, false);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(ListStatsFragViewModel.class);
        binding.setViewModel(viewModel);

        initData();
        initDatePicker();
        initRecyclerView();

        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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
            yearPicker.setMaxValue(years.length - 1);
            yearPicker.setDisplayedValues(getDisplayedYears());
            yearPicker.setValue(years.length - 1);
            yearPicker.setWrapSelectorWheel(false);  // 条目大于3时是否开启循环滚动

            // 设置月份选择器的范围和默认值
            monthPicker.setMinValue(0);
            monthPicker.setMaxValue(months[years.length - 1].length - 1);
            monthPicker.setDisplayedValues(getDisplayedMonths(years.length - 1));
            monthPicker.setValue(months[years.length - 1].length - 1);
            monthPicker.setWrapSelectorWheel(false);  // 条目大于3时是否开启循环滚动

            yearPicker.setOnValueChangedListener((picker, oldVal, newVal) ->
            {
                int maxMonth = months[newVal].length - 1;
                monthPicker.setDisplayedValues(null);  // 先重置数组，避免发生下标越界
                monthPicker.setMaxValue(maxMonth);
                monthPicker.setDisplayedValues(getDisplayedMonths(newVal));
                monthPicker.setValue(maxMonth);
            });

            // 添加取消和确定按钮的点击事件
            bottomSheetView.findViewById(R.id.cancelButton).setOnClickListener(v -> bottomSheetDialog.dismiss());
            bottomSheetView.findViewById(R.id.okButton).setOnClickListener(v ->
            {
                // 获取选中的年份和月份
                int year = yearPicker.getValue();
                int month = monthPicker.getValue();

                currentYear = years[year];
                currentMonth = months[year][month];
                updateTextButton();

                // 关闭底部抽屉
                bottomSheetDialog.dismiss();
            });

            // 设置底部抽屉的视图并显示它
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });
    }

    private String[] getDisplayedYears()
    {
        String[] displayedYears = new String[years.length];
        for (int i = 0; i < years.length; i++)
        {
            displayedYears[i] = years[i] + "年";
        }
        return displayedYears;
    }

    private String[] getDisplayedMonths(int index)
    {
        String[] displayedMonths = new String[months[index].length];
        for (int i = 0; i < months[index].length; i++)
        {
            displayedMonths[i] = months[index][i] + "月";
        }
        return displayedMonths;
    }

    /**
     * 模拟数据从数据库获取
     */
    private void initData()
    {
        years = new int[]{2020, 2021, 2022};
        months = new int[][]{{4, 6, 10}, {7, 8, 9, 10, 11, 12}, {3}};

        currentYear=years[years.length-1];
        currentMonth=months[years.length-1][months[years.length-1].length-1];

        updateTextButton();
    }

    /**
     * 更新 TextButton 的文本
     */
    private void updateTextButton()
    {
        String date = String.format(Locale.getDefault(), "%d年%02d月 ▼", currentYear, currentMonth);
        binding.textButton.setText(date);
    }
}