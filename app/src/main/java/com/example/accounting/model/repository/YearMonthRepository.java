package com.example.accounting.model.repository;

import androidx.lifecycle.LiveData;

import com.example.accounting.model.room.bean.YearMonth;
import com.example.accounting.model.room.dao.YearMonthDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.util.List;

public class YearMonthRepository
{
    private final YearMonthDao yearMonthDao;

    public YearMonthRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        yearMonthDao = myDatabase.getYearMonthDao();
    }

    public LiveData<List<YearMonth>> queryAll()
    {
        return yearMonthDao.queryAll();
    }
}