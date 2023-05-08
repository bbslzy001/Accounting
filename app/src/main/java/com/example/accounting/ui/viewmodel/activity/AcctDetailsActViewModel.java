package com.example.accounting.ui.viewmodel.activity;

import androidx.lifecycle.LiveData;

import com.example.accounting.base.BaseActivityViewModel;
import com.example.accounting.model.repository.TxnRvItemRepository;
import com.example.accounting.model.room.bean.TxnRvItem;

import java.util.List;

public class AcctDetailsActViewModel extends BaseActivityViewModel
{
    private LiveData<List<TxnRvItem>> itemList;  // 获取实时年月对应的交易记录列表
    private final TxnRvItemRepository txnRvItemRepository = new TxnRvItemRepository();

    public AcctDetailsActViewModel()
    {
        super();
    }

    public void initItemList(int acctId)
    {
        itemList = txnRvItemRepository.queryAllByAcctId(acctId);
    }

    public LiveData<List<TxnRvItem>> getItemList()
    {
        return itemList;
    }
}