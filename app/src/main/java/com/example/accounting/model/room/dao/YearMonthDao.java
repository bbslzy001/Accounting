package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.accounting.model.room.bean.YearMonth;

import java.util.List;

@Dao
public interface YearMonthDao
{
    @Query("select distinct substr(T_date, 1, 4) as year, " +
            "substr(T_date, 6, 2) as month " +
            "from Txn " +
            "order by year asc, month asc")
    LiveData<List<YearMonth>> queryAll();
}