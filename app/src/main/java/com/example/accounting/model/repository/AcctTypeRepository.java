package com.example.accounting.model.repository;

import androidx.lifecycle.LiveData;

import com.example.accounting.model.room.bean.AcctType;
import com.example.accounting.model.room.dao.AcctTypeDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AcctTypeRepository
{
    private final AcctTypeDao acctTypeDao;
    private final ExecutorService executor;

    public AcctTypeRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        acctTypeDao = myDatabase.getAccountDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insert(AcctType acctType)
    {
        executor.execute(() -> acctTypeDao.insert(acctType));
    }

    public void delete(AcctType acctType)
    {
        executor.execute(() -> acctTypeDao.delete(acctType));
    }

    public void deleteAll()
    {
        executor.execute(() -> acctTypeDao.deleteAll());
    }

    public void update(AcctType acctType)
    {
        executor.execute(() -> acctTypeDao.update(acctType));
    }

    public LiveData<AcctType> queryById(int id)
    {
        return acctTypeDao.queryById(id);
    }

    public LiveData<List<AcctType>> queryAll()
    {
        return acctTypeDao.queryAll();
    }
}