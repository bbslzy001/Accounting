package com.example.accounting.model.entity;

public class HomeRvSubItem
{
    private final String tradeType;
    private final String time;
    private final double amount;

    public HomeRvSubItem(String tradeType, String time, double amount)
    {
        this.tradeType = tradeType;
        this.time = time;
        this.amount = amount;
    }

    public String getTradeType()
    {
        return tradeType;
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
