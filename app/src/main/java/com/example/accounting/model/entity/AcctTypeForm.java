package com.example.accounting.model.entity;

public class AcctTypeForm
{
    private String type;
    private String amountText;

    public AcctTypeForm(String type, String amountText)
    {
        this.type = type;
        this.amountText = amountText;
    }

    public String getType()
    {
        return type;
    }

    public String getAmountText()
    {
        return amountText;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setAmountText(String amountText)
    {
        this.amountText = amountText;
    }
}