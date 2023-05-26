package com.example.accounting.model.repository;

import androidx.lifecycle.LiveData;

import com.example.accounting.model.room.bean.Acct;
import com.example.accounting.model.room.dao.AcctDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AcctRepository
{
    private final AcctDao acctDao;
    private final ExecutorService executor;

    public AcctRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        acctDao = myDatabase.getAcctDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insert(Acct acct)
    {
        executor.execute(() -> acctDao.insert(acct));
    }

    public void delete(Acct acct)
    {
        executor.execute(() -> acctDao.delete(acct));
    }

    public void deleteAll()
    {
        executor.execute(() -> acctDao.deleteAll());
    }

    public void update(Acct acct)
    {
        executor.execute(() -> acctDao.update(acct));
    }

    public LiveData<Acct> queryById(int id)
    {
        return acctDao.queryById(id);
    }

    public LiveData<List<Acct>> queryAll()
    {
        return acctDao.queryAll();
    }
}