package com.example.accounting.ui.view.activity;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseActivity;
import com.example.accounting.databinding.ActivityAcctDetailsBinding;
import com.example.accounting.model.room.bean.TxnRvItem;
import com.example.accounting.ui.viewmodel.activity.AcctDetailsActViewModel;
import com.example.accounting.utils.TxnForMonthRvItemDecoration;
import com.example.accounting.utils.adapter.TxnForMonthRvAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Objects;

public class AcctDetailsActivity extends BaseActivity<ActivityAcctDetailsBinding, AcctDetailsActViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_acct_details;
    }

    @Override
    protected Class<AcctDetailsActViewModel> getViewModelClass()
    {
        return AcctDetailsActViewModel.class;
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
        int acctId = getIntent().getIntExtra("acctId",-1);
        viewModel.initAcctType(acctId);
        viewModel.initItemList(acctId);
        initTopAppbar();
        initRecyclerView();
    }

    /**
     * 初始化顶部标题栏
     */
    private void initTopAppbar()
    {
        binding.topAppBar.setNavigationOnClickListener(view -> finish());
    }

    /**
     * 初始化滚动视图
     */
    private void initRecyclerView()
    {
        TxnForMonthRvAdapter adapter = new TxnForMonthRvAdapter();
        adapter.setOnSubItemClickListener(txnInfoId ->
        {
            TxnRvItem txnRvItem = new TxnRvItem();
            for(TxnRvItem item: Objects.requireNonNull(viewModel.getItemList().getValue()))
            {
                if(item.getTxnInfoId()==txnInfoId)
                {
                    txnRvItem = item;
                    break;
                }
            }
            new MaterialAlertDialogBuilder(AcctDetailsActivity.this)
                    .setTitle("交易信息")
                    .setMessage("交易类型：" + txnRvItem.getTxnType() + "\n交易账户：" + txnRvItem.getAcctType() + "\n交易金额：" + txnRvItem.getAmount() + "\n交易日期：" + txnRvItem.getDate() + "\n交易时间：" + txnRvItem.getTime() + "\n交易备注：" + txnRvItem.getRemark())
                    .setPositiveButton("关闭", null)
                    .show();
        });
        binding.recyclerView.setAdapter(adapter);
        TxnForMonthRvItemDecoration itemDecoration = new TxnForMonthRvItemDecoration(this, adapter);
        binding.recyclerView.addItemDecoration(itemDecoration);
        binding.recyclerView.addOnItemTouchListener(itemDecoration);
    }
}