package com.example.accounting.model.entity;

import com.example.accounting.base.recyclerview.BaseRvSubItem;

public class TxnForMonthRvSubItem extends BaseRvSubItem
{
    private final String txnType;
    private final String time;
    private final double amount;

    public TxnForMonthRvSubItem(int subItemId, String txnType, String time, double amount)
    {
        super(subItemId);
        this.txnType = txnType;
        this.time = time;
        this.amount = amount;
    }

    public String getTxnType()
    {
        return txnType;
    }

    public String getTime()
    {
        return time;
    }

    public double getAmount()
    {
        return amount;
    }
}