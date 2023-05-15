package com.example.accounting.ui.view.fragment.statistics;

import android.content.Intent;
import android.widget.FrameLayout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseFragment;
import com.example.accounting.databinding.FragmentStatsCalBinding;
import com.example.accounting.model.room.bean.Txn;
import com.example.accounting.model.room.bean.TxnRvItem;
import com.example.accounting.ui.view.activity.EditTxnActivity;
import com.example.accounting.ui.viewmodel.fragment.statistics.CalStatsFragViewModel;
import com.example.accounting.utils.adapter.TxnForOneDayRvAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class CalStatsFragment extends BaseFragment<FragmentStatsCalBinding, CalStatsFragViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_stats_cal;
    }

    @Override
    protected Class<CalStatsFragViewModel> getViewModelClass()
    {
        return CalStatsFragViewModel.class;
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
        observeCalDate();
        initRecyclerView();
    }

    /**
     * 监听日历日期变化
     */
    private void observeCalDate()
    {
        binding.calendar.setOnCalendarChangedListener((baseCalendar, year, month, localDate, dateChangeBehavior) -> viewModel.setCurrentDate(localDate));
    }

    /**
     * 初始化滚动视图
     */
    private void initRecyclerView()
    {
        TxnForOneDayRvAdapter adapter = new TxnForOneDayRvAdapter();
        adapter.setOnItemClickListener(new TxnForOneDayRvAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(int txnId)
            {
                TxnRvItem txnRvItem = new TxnRvItem();
                for (TxnRvItem item : Objects.requireNonNull(viewModel.getItemList().getValue()))
                {
                    if (item.getTxnId() == txnId)
                    {
                        txnRvItem = item;
                        break;
                    }
                }
                new MaterialAlertDialogBuilder(requireActivity())
                        .setTitle("交易信息")
                        .setMessage("交易类型：" + txnRvItem.getTxnType() + "\n交易账户：" + txnRvItem.getAcctName() + "\n交易金额：" + txnRvItem.getAmount() + "\n交易日期：" + txnRvItem.getDate() + "\n交易时间：" + txnRvItem.getTime() + "\n交易备注：" + txnRvItem.getRemark())
                        .setPositiveButton("关闭", null)
                        .show();
            }

            @Override
            public void onItemLongClick(int txnId)
            {
                new MaterialAlertDialogBuilder(requireActivity())
                        .setTitle("执行操作")
                        .setNeutralButton("取消", null)
                        .setNegativeButton("删除", (dialogInterface, i) ->
                        {
                            final Txn[] backup = {null};
                            LiveData<Txn> backupLiveData = viewModel.queryTxn(txnId);
                            backupLiveData.observeForever(new Observer<>()
                            {
                                @Override
                                public void onChanged(Txn txn)
                                {
                                    if (txn != null)
                                    {
                                        backup[0] = txn;
                                        backupLiveData.removeObserver(this);
                                        viewModel.deleteTxn(txnId);
                                        Snackbar snackbar = Snackbar.make(requireView(), "数据已删除", Snackbar.LENGTH_LONG);
                                        snackbar.setAction("撤销", view -> viewModel.insertTxn(backup[0]));
                                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) snackbar.getView().getLayoutParams();
                                        params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, params.bottomMargin + viewModel.getNavigationBarHeight());
                                        snackbar.getView().setLayoutParams(params);
                                        snackbar.show();
                                    }
                                }
                            });
                        })
                        .setPositiveButton("编辑", (dialogInterface, i) ->
                        {
                            TxnRvItem txnRvItem = new TxnRvItem();
                            for (TxnRvItem item : Objects.requireNonNull(viewModel.getItemList().getValue()))
                            {
                                if (item.getTxnId() == txnId)
                                {
                                    txnRvItem = item;
                                    break;
                                }
                            }
                            startActivity(new Intent(requireActivity(), EditTxnActivity.class).putExtra("txnRvItem", txnRvItem));
                        })
                        .show();
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }
}