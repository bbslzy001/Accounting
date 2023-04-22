package com.example.accounting.model.room.bean;

public class TxnRvItem
{
    private int txnInfoId;
    private double amount;
    private String date;
    private String time;
    private String remark;
    private String acctType;
    private String txnType;

    public TxnRvItem(int txnInfoId, double amount, String date, String time, String remark, String acctType, String txnType)
    {
        this.txnInfoId = txnInfoId;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.remark = remark;
        this.acctType = acctType;
        this.txnType = txnType;
    }

    public int getTxnInfoId()
    {
        return txnInfoId;
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

    public String getAcctType()
    {
        return acctType;
    }

    public String getTxnType()
    {
        return txnType;
    }

    public void setTxnInfoId(int txnInfoId)
    {
        this.txnInfoId = txnInfoId;
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

    public void setAcctType(String acctType)
    {
        this.acctType = acctType;
    }

    public void setTxnType(String txnType)
    {
        this.txnType = txnType;
    }
}