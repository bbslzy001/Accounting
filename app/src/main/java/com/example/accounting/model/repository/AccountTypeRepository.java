package com.example.accounting.model.repository;

import androidx.lifecycle.LiveData;

import com.example.accounting.model.room.bean.AccountType;
import com.example.accounting.model.room.dao.AccountTypeDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountTypeRepository
{
    private AccountTypeDao accountTypeDao;
    private final ExecutorService executor;

    public AccountTypeRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        accountTypeDao = myDatabase.getAccountDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insert(AccountType accountType)
    {
        executor.execute(() -> accountTypeDao.insert(accountType));
    }

    public void delete(AccountType accountType)
    {
        executor.execute(() -> accountTypeDao.delete(accountType));
    }

    public void deleteAll()
    {
        executor.execute(() -> accountTypeDao.deleteAll());
    }

    public void update(AccountType accountType)
    {
        executor.execute(() -> accountTypeDao.update(accountType));
    }

    public LiveData<AccountType> queryById(int id)
    {
        return accountTypeDao.queryById(id);
    }

    public LiveData<Double> queryTotalAmount()
    {
        return accountTypeDao.queryTotalAmount();
    }

    public LiveData<Double> queryNegativeAmount()
    {
        return accountTypeDao.queryNegativeAmount();
    }

    public LiveData<Double> queryNetAmount()
    {
        return accountTypeDao.queryNetAmount();
    }

    public LiveData<List<AccountType>> queryAll()
    {
        return accountTypeDao.queryAll();
    }
}