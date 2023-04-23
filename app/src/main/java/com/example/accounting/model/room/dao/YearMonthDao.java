package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.accounting.model.room.bean.YearMonth;

import java.util.List;

@Dao
public interface YearMonthDao
{
    @Query("SELECT DISTINCT SUBSTR(TI_date, 1, 4) AS year, SUBSTR(TI_date, 6, 2) AS month FROM TxnInfo ORDER BY year ASC, month ASC")
    LiveData<List<YearMonth>> queryAll();
}