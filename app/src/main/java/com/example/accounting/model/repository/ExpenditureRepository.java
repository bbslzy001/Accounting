package com.example.accounting.model.repository;

import com.example.accounting.model.room.dao.ExpenditureDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExpenditureRepository
{
    private ExpenditureDao expenditureDao;
    private final ExecutorService executor;

    public ExpenditureRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        expenditureDao = myDatabase.getExpenditureDao();
        executor = Executors.newSingleThreadExecutor();
    }
}