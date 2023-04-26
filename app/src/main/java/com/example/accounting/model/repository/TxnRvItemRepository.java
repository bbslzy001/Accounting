package com.example.accounting.model.repository;

import androidx.lifecycle.LiveData;

import com.example.accounting.model.room.bean.TxnRvItem;
import com.example.accounting.model.room.dao.TxnRvItemDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.util.List;

public class TxnRvItemRepository
{
    private final TxnRvItemDao txnRvItemDao;

    public TxnRvItemRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        txnRvItemDao = myDatabase.getTxnRvItemDao();
    }

    public LiveData<Double> queryIncomeByYearAndMonth(String year, String month)
    {
        return txnRvItemDao.queryIncomeByYearAndMonth(year, month);
    }

    public LiveData<Double> queryExpenditureByYearAndMonth(String year, String month)
    {
        return txnRvItemDao.queryExpenditureByYearAndMonth(year, month);
    }

    public LiveData<List<TxnRvItem>> queryAllByYearAndMonth(String year, String month)
    {
        return txnRvItemDao.queryAllByYearAndMonth(year, month);
    }
}