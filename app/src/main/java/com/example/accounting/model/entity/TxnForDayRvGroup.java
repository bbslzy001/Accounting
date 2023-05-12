package com.example.accounting.model.entity;

import com.example.accounting.base.recyclerview.BaseRvGroup;

import java.util.List;

public class TxnForDayRvGroup extends BaseRvGroup<TxnForDayRvHeaderItem, TxnForDayRvSubItem>
{
    public TxnForDayRvGroup(TxnForDayRvHeaderItem headerItem, List<TxnForDayRvSubItem> subItemList)
    {
        super(headerItem, subItemList);
    }
}