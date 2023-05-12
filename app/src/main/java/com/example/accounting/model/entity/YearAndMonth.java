package com.example.accounting.model.entity;

public class YearAndMonth
{
    private final String year;
    private final String month;

    public YearAndMonth(String year, String month)
    {
        this.year = year;
        this.month = month;
    }

    public String getYear()
    {
        return year;
    }

    public String getMonth()
    {
        return month;
    }
}