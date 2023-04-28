package com.example.accounting.model.repository;

import androidx.lifecycle.LiveData;

import com.example.accounting.model.room.bean.TxnInfo;
import com.example.accounting.model.room.dao.TxnInfoDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TxnInfoRepository
{
    private final TxnInfoDao txnInfoDao;
    private final ExecutorService executor;

    public TxnInfoRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        txnInfoDao = myDatabase.getTxnInfoDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insert(TxnInfo txnInfo)
    {
        executor.execute(()-> txnInfoDao.insert(txnInfo));
    }

    public LiveData<TxnInfo> queryById(int id)
    {
        return txnInfoDao.queryById(id);
    }

    public LiveData<List<TxnInfo>> queryAll()
    {
        return txnInfoDao.queryAll();
    }
}