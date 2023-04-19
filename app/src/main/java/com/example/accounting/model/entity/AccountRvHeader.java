package com.example.accounting.model.entity;

public class AccountRvHeader
{
    private final double netAmount;
    private final double totalAmount;
    private final double negativeAmount;

    public AccountRvHeader(double netAmount, double totalAmount, double negativeAmount)
    {
        this.netAmount = netAmount;
        this.totalAmount = totalAmount;
        this.negativeAmount = negativeAmount;
    }

    public double getNetAmount()
    {
        return netAmount;
    }

    public double getTotalAmount()
    {
        return totalAmount;
    }

    public double getNegativeAmount()
    {
        return negativeAmount;
    }
}