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
        txnRvItemDao = myDatabase.getHomeRecyclerViewItemDao();
    }

    public LiveData<Double> queryExpenditureAmount()
    {
        return txnRvItemDao.queryExpenditureAmount();
    }

    public LiveData<Double> queryIncomeAmount()
    {
        return txnRvItemDao.queryIncomeAmount();
    }

    public LiveData<Double> queryTotalAmount()
    {
        return txnRvItemDao.queryTotalAmount();
    }

    public LiveData<List<TxnRvItem>> queryAllExpenditures()
    {
        return txnRvItemDao.queryAllExpenditures();
    }

    public LiveData<List<TxnRvItem>> queryAllIncomes()
    {
        return txnRvItemDao.queryAllIncomes();
    }

    public LiveData<List<TxnRvItem>> queryAll()
    {
        return txnRvItemDao.queryAll();
    }
}