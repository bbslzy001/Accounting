package com.example.accounting.model.room.bean;

import androidx.room.Ignore;

public class PostInfo
{
    private double dining;
    private double transportation;
    private double shopping;
    private double entertainment;
    private double salary;
    private double bonus;
    private double investment;

    @Ignore
    public PostInfo()
    {
    }

    public PostInfo(double dining, double transportation, double shopping, double entertainment, double salary, double bonus, double investment)
    {
        this.dining = dining;
        this.transportation = transportation;
        this.shopping = shopping;
        this.entertainment = entertainment;
        this.salary = salary;
        this.bonus = bonus;
        this.investment = investment;
    }

    public double getDining()
    {
        return dining;
    }

    public double getTransportation()
    {
        return transportation;
    }

    public double getShopping()
    {
        return shopping;
    }

    public double getEntertainment()
    {
        return entertainment;
    }

    public double getSalary()
    {
        return salary;
    }

    public double getBonus()
    {
        return bonus;
    }

    public double getInvestment()
    {
        return investment;
    }
}
