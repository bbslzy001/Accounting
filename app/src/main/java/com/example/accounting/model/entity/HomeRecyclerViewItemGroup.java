package com.example.accounting.model.entity;

import com.example.accounting.model.room.bean.HomeRecyclerViewItem;

import java.util.List;

public class HomeRecyclerViewItemGroup
{
    private String date;
    private double expenditure;
    private double income;
    private List<HomeRecyclerViewItem> items;

    public HomeRecyclerViewItemGroup(String date, double expenditure, double income, List<HomeRecyclerViewItem> items)
    {
        this.date = date;
        this.expenditure = expenditure;
        this.income = income;
        this.items = items;
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

    public List<HomeRecyclerViewItem> getItems()
    {
        return items;
    }
}
