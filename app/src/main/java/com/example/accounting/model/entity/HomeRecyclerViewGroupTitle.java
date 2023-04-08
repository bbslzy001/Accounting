package com.example.accounting.model.entity;

public class HomeRecyclerViewGroupTitle
{
    private String date;
    private double expenditure;
    private double income;

    public HomeRecyclerViewGroupTitle(String date, double expenditure, double income)
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

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setExpenditure(double expenditure)
    {
        this.expenditure = expenditure;
    }

    public void setIncome(double income)
    {
        this.income = income;
    }
}