package com.example.accounting.model.room.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TxnType")
public class TxnType
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "TT_id")
    private int id;

    @ColumnInfo(name = "TT_type")
    private String type;

    public TxnType(int id, String type)
    {
        this.id = id;
        this.type = type;
    }

    public int getId()
    {
        return id;
    }

    public String getType()
    {
        return type;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}