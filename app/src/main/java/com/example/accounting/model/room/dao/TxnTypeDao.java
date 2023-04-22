package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.accounting.model.room.bean.TxnType;

import java.util.List;

@Dao
public interface TxnTypeDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TxnType txnType);

    @Delete()
    void delete(TxnType txnType);

    @Query("delete from TxnType")
    void deleteAll();

    @Update
    void update(TxnType txnType);

    @Query("select * from TxnType where TT_id = :id")
    LiveData<TxnType> queryById(int id);

    @Query("select * from TxnType")
    LiveData<List<TxnType>> queryAll();
}