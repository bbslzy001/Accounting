package com.example.accounting.model.entity;

public class TxnRvSubItem
{
    private final String txnType;
    private final String time;
    private final double amount;

    public TxnRvSubItem(String txnType, String time, double amount)
    {
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