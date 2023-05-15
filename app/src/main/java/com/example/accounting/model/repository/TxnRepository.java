package com.example.accounting.model.repository;

import androidx.lifecycle.LiveData;

import com.example.accounting.model.room.bean.Txn;
import com.example.accounting.model.room.dao.TxnDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TxnRepository
{
    private final TxnDao txnDao;
    private final ExecutorService executor;

    public TxnRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        txnDao = myDatabase.getTxnDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insert(Txn txn)
    {
        executor.execute(() -> txnDao.insert(txn));
    }

    public void deleteById(int id)
    {
        executor.execute(() -> txnDao.deleteById(id));
    }

    public void update(Txn txn)
    {
        executor.execute(()-> txnDao.update(txn));
    }

    public LiveData<Txn> queryById(int id)
    {
        return txnDao.queryById(id);
    }

    public LiveData<List<Txn>> queryAll()
    {
        return txnDao.queryAll();
    }
}