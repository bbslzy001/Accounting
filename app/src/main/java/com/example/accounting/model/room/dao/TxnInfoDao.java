package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.accounting.model.room.bean.TxnInfo;

import java.util.List;

@Dao
public interface TxnInfoDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TxnInfo txnInfo);

    @Delete()
    void delete(TxnInfo txnInfo);

    @Query("delete from TxnInfo")
    void deleteAll();

    @Update
    void update(TxnInfo txnInfo);

    @Query("select * from TxnInfo where TI_id = :id")
    LiveData<TxnInfo> queryById(int id);

    @Query("select * from TxnInfo")
    LiveData<List<TxnInfo>> queryAll();
}