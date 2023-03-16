package com.example.accounting.model.room.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "AccountType")
public class AccountType
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "AT_id")
    private int id;

    @ColumnInfo(name = "AT_type")
    private String type;

    @ColumnInfo(name = "AT_amount")
    private double amount;

    public AccountType(int id, String type, double amount)
    {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    public int getId()
    {
        return id;
    }

    public String getType()
    {
        return type;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }
}