package com.example.accounting.model.entity;

public class TxnForOneDayRvHeader
{
    private final String date;
    private final double income;
    private final double expense;

    public TxnForOneDayRvHeader(String date, double income, double expense)
    {
        this.date = date;
        this.income = income;
        this.expense = expense;
    }

    public String getDate()
    {
        return date;
    }

    public double getIncome()
    {
        return income;
    }

    public double getExpense()
    {
        return expense;
    }
}