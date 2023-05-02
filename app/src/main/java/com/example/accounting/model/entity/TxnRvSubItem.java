package com.example.accounting.model.entity;

public class TxnRvSubItem
{
    private final int txnInfoId;
    private final String txnType;
    private final String time;
    private final double amount;

    public TxnRvSubItem(int txnInfoId, String txnType, String time, double amount)
    {
        this.txnInfoId = txnInfoId;
        this.txnType = txnType;
        this.time = time;
        this.amount = amount;
    }

    public int getTxnInfoId()
    {
        return txnInfoId;
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