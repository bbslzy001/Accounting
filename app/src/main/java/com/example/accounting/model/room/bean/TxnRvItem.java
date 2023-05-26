package com.example.accounting.model.room.bean;

import androidx.room.Ignore;

import java.io.Serializable;

public class TxnRvItem implements Serializable
{
    private int txnId;
    private double amount;
    private String date;
    private String time;
    private String remark;
    private String acctName;
    private String txnType;

    @Ignore
    public TxnRvItem()
    {
    }

    public TxnRvItem(int txnId, double amount, String date, String time, String remark, String acctName, String txnType)
    {
        this.txnId = txnId;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.remark = remark;
        this.acctName = acctName;
        this.txnType = txnType;
    }

    public int getTxnId()
    {
        return txnId;
    }

    public double getAmount()
    {
        return amount;
    }

    public String getDate()
    {
        return date;
    }

    public String getTime()
    {
        return time;
    }

    public String getRemark()
    {
        return remark;
    }

    public String getAcctName()
    {
        return acctName;
    }

    public String getTxnType()
    {
        return txnType;
    }

    public void setTxnId(int txnId)
    {
        this.txnId = txnId;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public void setAcctName(String acctName)
    {
        this.acctName = acctName;
    }

    public void setTxnType(String txnType)
    {
        this.txnType = txnType;
    }
}