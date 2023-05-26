package com.example.accounting.model.room.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Acct")
public class Acct
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "A_id")
    private int id;

    @ColumnInfo(name = "A_name")
    private String name;

    @ColumnInfo(name = "A_amount")
    private double amount;

    public Acct(int id, String name, double amount)
    {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }
}