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

    public LiveData<Double> queryIncomeByMonth(String year, String month)
    {
        return txnRvItemDao.queryIncomeByMonth(year, month);
    }

    public LiveData<Double> queryExpenseByMonth(String year, String month)
    {
        return txnRvItemDao.queryExpenseByMonth(year, month);
    }

    public LiveData<List<TxnRvItem>> queryAllByMonth(String year, String month)
    {
        return txnRvItemDao.queryAllByMonth(year, month);
    }

    public LiveData<List<TxnRvItem>> queryAllByDay(String date)
    {
        return txnRvItemDao.queryAllByDay(date);
    }

    public LiveData<List<TxnRvItem>> queryAllByAcctId(int acctId)
    {
        return txnRvItemDao.queryAllByAcctId(acctId);
    }
}