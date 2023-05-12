package com.example.accounting.model.entity;

import com.example.accounting.base.recyclerview.BaseRvGroup;

import java.util.List;

public class TxnForMonthRvGroup extends BaseRvGroup<TxnForMonthRvHeaderItem,TxnForMonthRvSubItem>
{
    public TxnForMonthRvGroup(TxnForMonthRvHeaderItem headerItem, List<TxnForMonthRvSubItem> subItemList)
    {
        super(headerItem, subItemList);
    }
}