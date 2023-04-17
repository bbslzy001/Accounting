package com.example.accounting.model.room.bean;

public class HomeRvItem
{
    private int tradeInfoId;
    private double amount;
    private String date;
    private String time;
    private String remark;
    private String accountType;
    private String tradeType;

    public HomeRvItem(int tradeInfoId, double amount, String date, String time, String remark, String accountType, String tradeType)
    {
        this.tradeInfoId = tradeInfoId;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.remark = remark;
        this.accountType = accountType;
        this.tradeType = tradeType;
    }

    public int getTradeInfoId()
    {
        return tradeInfoId;
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

    public String getAccountType()
    {
        return accountType;
    }

    public String getTradeType()
    {
        return tradeType;
    }

    public void setTradeInfoId(int tradeInfoId)
    {
        this.tradeInfoId = tradeInfoId;
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

    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }

    public void setTradeType(String tradeType)
    {
        this.tradeType = tradeType;
    }
}