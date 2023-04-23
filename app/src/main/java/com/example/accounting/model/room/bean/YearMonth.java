package com.example.accounting.model.room.bean;

public class YearMonth
{
    private String year;
    private String month;

    public YearMonth(String year, String month)
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

    public void setYear(String year)
    {
        this.year = year;
    }

    public void setMonth(String month)
    {
        this.month = month;
    }
}