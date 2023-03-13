package com.example.accounting.model.repository;

import com.example.accounting.model.room.dao.IncomeDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IncomeRepository
{
    private IncomeDao incomeDao;
    private final ExecutorService executor;

    public IncomeRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        incomeDao = myDatabase.getIncomeDao();
        executor = Executors.newSingleThreadExecutor();
    }
}