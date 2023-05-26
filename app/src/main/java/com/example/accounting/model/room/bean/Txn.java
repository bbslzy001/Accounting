package com.example.accounting.model.room.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Txn",
        indices = {
                @Index(value = "T_date"),
                @Index(value = "T_amount"),
                @Index(value = "A_id"),
                @Index(value = "TT_id")
        },
        foreignKeys = {
                @ForeignKey(
                        entity = Acct.class,
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE,
                        parentColumns = "A_id", childColumns = "A_id"
                ),
                @ForeignKey(
                        entity = TxnType.class,
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE,
                        parentColumns = "TT_id", childColumns = "TT_id"
                )
        }
)
public class Txn
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "T_id")
    private int id;

    @ColumnInfo(name = "T_amount")
    private double amount;

    @ColumnInfo(name = "T_date")
    private String date;

    @ColumnInfo(name = "T_time")
    private String time;

    @ColumnInfo(name = "T_remark")
    private String remark;

    @ColumnInfo(name = "A_id")
    private int acctId;

    @ColumnInfo(name = "TT_id")
    private int txnTypeId;

    public Txn(int id, double amount, String date, String time, String remark, int acctId, int txnTypeId)
    {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.remark = remark;
        this.acctId = acctId;
        this.txnTypeId = txnTypeId;
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

    public int getAcctId()
    {
        return acctId;
    }

    public int getTxnTypeId()
    {
        return txnTypeId;
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

    public void setAcctId(int acctId)
    {
        this.acctId = acctId;
    }

    public void setTxnTypeId(int txnTypeId)
    {
        this.txnTypeId = txnTypeId;
    }
}