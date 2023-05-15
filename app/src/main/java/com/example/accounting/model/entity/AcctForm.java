package com.example.accounting.model.entity;

public class AcctForm
{
    private String name;
    private String amountText;

    public AcctForm(String name, String amountText)
    {
        this.name = name;
        this.amountText = amountText;
    }

    public String getName()
    {
        return name;
    }

    public String getAmountText()
    {
        return amountText;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAmountText(String amountText)
    {
        this.amountText = amountText;
    }
}