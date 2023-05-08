package com.example.accounting.model.entity;

import com.example.accounting.base.recyclerview.BaseRvGroup;

import java.util.List;

public class TxnRvGroup extends BaseRvGroup<TxnRvHeaderItem,TxnRvSubItem>
{
    public TxnRvGroup(TxnRvHeaderItem headerItem, List<TxnRvSubItem> subItemList)
    {
        super(headerItem, subItemList);
    }
}