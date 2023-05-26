package com.example.accounting.ui.viewmodel.activity;

import androidx.lifecycle.LiveData;

import com.example.accounting.base.BaseActivityViewModel;
import com.example.accounting.model.repository.AcctRepository;
import com.example.accounting.model.repository.TxnRvItemRepository;
import com.example.accounting.model.room.bean.Acct;
import com.example.accounting.model.room.bean.TxnRvItem;

import java.util.List;

public class AcctDetailsActViewModel extends BaseActivityViewModel
{
    private LiveData<List<TxnRvItem>> itemList;  // 获取实时年月对应的交易记录列表
    private LiveData<Acct> acct;
    private final TxnRvItemRepository txnRvItemRepository = new TxnRvItemRepository();
    private final AcctRepository acctRepository = new AcctRepository();

    public AcctDetailsActViewModel()
    {
        super();
    }

    public void initAcct(int acctId)
    {
        acct = acctRepository.queryById(acctId);
    }

    public void initItemList(int acctId)
    {
        itemList = txnRvItemRepository.queryAllByAcctId(acctId);
    }

    public LiveData<List<TxnRvItem>> getItemList()
    {
        return itemList;
    }

    public LiveData<Acct> getTitle()
    {
        return acct;
    }
}