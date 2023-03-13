package com.example.accounting.model.repository;

import com.example.accounting.model.room.dao.TradeTypeDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradeTypeRepository
{
    private TradeTypeDao tradeTypeDao;
    private final ExecutorService executor;

    public TradeTypeRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        tradeTypeDao = myDatabase.getTradeTypeDao();
        executor = Executors.newSingleThreadExecutor();
    }
}