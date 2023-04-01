package com.example.accounting.model.repository;

import androidx.lifecycle.LiveData;

import com.example.accounting.model.room.bean.TradeType;
import com.example.accounting.model.room.dao.TradeTypeDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.util.List;
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

    public void insert(TradeType tradeType)
    {
        executor.execute(()->tradeTypeDao.insert(tradeType));
    }

    public void deleteAll()
    {
        executor.execute(()->tradeTypeDao.deleteAll());
    }

    public LiveData<TradeType> queryById(int id)
    {
        return tradeTypeDao.queryById(id);
    }

    public LiveData<List<TradeType>> queryAll()
    {
        return tradeTypeDao.queryAll();
    }
}