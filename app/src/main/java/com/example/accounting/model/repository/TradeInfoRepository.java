package com.example.accounting.model.repository;

import androidx.lifecycle.LiveData;

import com.example.accounting.model.room.bean.TradeInfo;
import com.example.accounting.model.room.dao.TradeInfoDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradeInfoRepository
{
    private final TradeInfoDao tradeInfoDao;
    private final ExecutorService executor;

    public TradeInfoRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        tradeInfoDao = myDatabase.getTradeInfoDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insert(TradeInfo tradeInfo)
    {
        executor.execute(()->tradeInfoDao.insert(tradeInfo));
    }

    public LiveData<List<TradeInfo>> queryAll()
    {
        return tradeInfoDao.queryAll();
    }
}