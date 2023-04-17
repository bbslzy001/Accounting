package com.example.accounting.model.entity;

public class HomeRvHeaderItem
{
    private final String date;
    private final double expenditure;
    private final double income;

    public HomeRvHeaderItem(String date, double expenditure, double income)
    {
        this.date = date;
        this.expenditure = expenditure;
        this.income = income;
    }

    public String getDate()
    {
        return date;
    }

    public double getExpenditure()
    {
        return expenditure;
    }

    public double getIncome()
    {
        return income;
    }
}