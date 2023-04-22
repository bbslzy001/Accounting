package com.example.accounting.model.repository;

import androidx.lifecycle.LiveData;

import com.example.accounting.model.room.bean.TxnType;
import com.example.accounting.model.room.dao.TxnTypeDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TxnTypeRepository
{
    private final TxnTypeDao txnTypeDao;
    private final ExecutorService executor;

    public TxnTypeRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        txnTypeDao = myDatabase.getTradeTypeDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insert(TxnType txnType)
    {
        executor.execute(()-> txnTypeDao.insert(txnType));
    }

    public void deleteAll()
    {
        executor.execute(()-> txnTypeDao.deleteAll());
    }

    public LiveData<TxnType> queryById(int id)
    {
        return txnTypeDao.queryById(id);
    }

    public LiveData<List<TxnType>> queryAll()
    {
        return txnTypeDao.queryAll();
    }
}