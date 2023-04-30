package com.example.accounting.ui.view.fragment.statistics;

import android.content.Intent;
import android.view.View;
import android.widget.NumberPicker;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseFragment;
import com.example.accounting.databinding.FragmentStatsListBinding;
import com.example.accounting.model.room.bean.TxnRvItem;
import com.example.accounting.ui.view.activity.EditTxnActivity;
import com.example.accounting.ui.viewmodel.fragment.statistics.ListStatsFragViewModel;
import com.example.accounting.utils.TxnRvItemDecoration;
import com.example.accounting.utils.adapter.TxnRvAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Objects;

public class ListStatsFragment extends BaseFragment<FragmentStatsListBinding, ListStatsFragViewModel>
{
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

        initDatePicker();
        initRecyclerView();
        observeYearMonthList();
    }

    /**
     * 初始化滚动视图
     */
    private void initRecyclerView()
    {
        TxnRvAdapter adapter = new TxnRvAdapter();
        adapter.setOnSubItemClickListener(new TxnRvAdapter.OnSubItemClickListener()
        {
            @Override
            public void onItemClick(int groupIndex, int position)
            {
                int index = position - groupIndex - 1;
                TxnRvItem txnRvItem = Objects.requireNonNull(viewModel.getItemList().getValue()).get(index);
                new MaterialAlertDialogBuilder(requireActivity())
                        .setTitle("交易信息")
                        .setMessage("交易类型：" + txnRvItem.getTxnType() + "\n交易账户：" + txnRvItem.getAcctType() + "\n交易金额：" + txnRvItem.getAmount() + "\n交易日期：" + txnRvItem.getDate() + "\n交易时间：" + txnRvItem.getTime() + "\n交易备注：" + txnRvItem.getRemark())
                        .setPositiveButton("关闭", null)
                        .show();
            }

            @Override
            public void onItemLongClick(int groupIndex, int position)
            {
                int index = position - groupIndex - 1;
                new MaterialAlertDialogBuilder(requireActivity())
                        .setTitle("执行操作")
                        .setNeutralButton("取消", null)
                        .setNegativeButton("删除", (dialogInterface, i) ->
                        {
                            int id = Objects.requireNonNull(viewModel.getItemList().getValue()).get(index).getTxnInfoId();
                            viewModel.deleteTxnInfo(id);
                        })
                        .setPositiveButton("编辑", (dialogInterface, i) ->
                        {
                            TxnRvItem txnRvItem = Objects.requireNonNull(viewModel.getItemList().getValue()).get(index);
                            startActivity(new Intent(requireActivity(), EditTxnActivity.class).putExtra("txnRvItem", txnRvItem));
                        })
                        .show();
            }
        });
        binding.recyclerView.setAdapter(adapter);
        TxnRvItemDecoration itemDecoration = new TxnRvItemDecoration(this.getContext(), adapter);
        binding.recyclerView.addItemDecoration(itemDecoration);
        binding.recyclerView.addOnItemTouchListener(itemDecoration);
    }

    /**
     * 初始化日期选择器
     */
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
            yearPicker.setMaxValue(viewModel.getYearArray().length - 1);
            yearPicker.setDisplayedValues(viewModel.getYearArray());
            yearPicker.setValue(viewModel.getCurrentYearIndex());
            yearPicker.setWrapSelectorWheel(false);  // 条目大于3时是否开启循环滚动

            // 设置月份选择器的范围和默认值
            monthPicker.setMinValue(0);
            monthPicker.setMaxValue(viewModel.getMonthArray().length - 1);
            monthPicker.setDisplayedValues(viewModel.getMonthArray());
            monthPicker.setValue(viewModel.getCurrentMonthIndex());
            monthPicker.setWrapSelectorWheel(false);  // 条目大于3时是否开启循环滚动

            yearPicker.setOnValueChangedListener((picker, oldVal, newVal) ->
            {
                String[] monthArray = viewModel.getMonthArray(newVal);
                monthPicker.setDisplayedValues(null);  // 先重置数组，避免发生下标越界
                monthPicker.setMaxValue(monthArray.length - 1);
                monthPicker.setDisplayedValues(monthArray);
                monthPicker.setValue(monthArray.length - 1);
            });

            // 添加取消和确定按钮的点击事件
            bottomSheetView.findViewById(R.id.cancelButton).setOnClickListener(v -> bottomSheetDialog.dismiss());
            bottomSheetView.findViewById(R.id.okButton).setOnClickListener(v ->
            {
                // 获取选中的年份和月份
                int year = yearPicker.getValue();
                int month = monthPicker.getValue();

                viewModel.setCurrentYear(year);
                viewModel.setCurrentMonth(year, month);

                // 关闭底部抽屉
                bottomSheetDialog.dismiss();
            });

            // 设置底部抽屉的视图并显示它
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });
    }

    /**
     * 观察年月列表
     */
    private void observeYearMonthList()
    {
        viewModel.observeYearMonthList(this);
    }
}