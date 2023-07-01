package com.example.accounting.model.room.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Chip")
public class Chip
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "C_id")
    private int id;

    @ColumnInfo(name = "C_state")
    private boolean state;

    public Chip(int id, boolean state)
    {
        this.id = id;
        this.state = state;
    }

    public int getId()
    {
        return id;
    }

    public boolean isState()
    {
        return state;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setState(boolean state)
    {
        this.state = state;
    }
}
