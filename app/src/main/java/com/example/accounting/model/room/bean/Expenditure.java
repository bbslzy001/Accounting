package com.example.accounting.model.room.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Expenditure",
        indices = {
                @Index(value = "E_date"),
                @Index(value = "AT_id"),
                @Index(value = "TT_id")
        },
        foreignKeys = {
                @ForeignKey(
                        entity = AccountType.class,
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE,
                        parentColumns = "AT_id", childColumns = "AT_id"
                ),
                @ForeignKey(
                        entity = TradeType.class,
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE,
                        parentColumns = "TT_id", childColumns = "TT_id"
                )
        }
)
public class Expenditure
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "E_id")
    private int id;

    @ColumnInfo(name = "E_amount")
    private double amount;

    @ColumnInfo(name = "E_date")
    private String date;

    @ColumnInfo(name = "E_time")
    private String time;

    @ColumnInfo(name = "E_remark")
    private String remark;

    @ColumnInfo(name = "AT_id")
    private int accountTypeId;

    @ColumnInfo(name = "TT_id")
    private int tradeTypeId;

    public Expenditure(int id, double amount, String date, String time, String remark, int accountTypeId, int tradeTypeId)
    {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.remark = remark;
        this.accountTypeId = accountTypeId;
        this.tradeTypeId = tradeTypeId;
    }

    public int getId()
    {
        return id;
    }

    public double getAmount()
    {
        return amount;
    }

    public String getDate()
    {
        return date;
    }

    public String getTime()
    {
        return time;
    }

    public String getRemark()
    {
        return remark;
    }

    public int getAccountTypeId()
    {
        return accountTypeId;
    }

    public int getTradeTypeId()
    {
        return tradeTypeId;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public void setAccountTypeId(int accountTypeId)
    {
        this.accountTypeId = accountTypeId;
    }

    public void setTradeTypeId(int tradeTypeId)
    {
        this.tradeTypeId = tradeTypeId;
    }
}