package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.accounting.model.room.bean.Txn;

import java.util.List;

@Dao
public interface TxnDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Txn txn);

    @Delete()
    void delete(Txn txn);

    @Query("delete from Txn")
    void deleteAll();

    @Query("delete from Txn where T_id = :id")
    void deleteById(int id);

    @Update
    void update(Txn txn);

    @Query("select * from Txn where T_id = :id")
    LiveData<Txn> queryById(int id);

    @Query("select * from Txn")
    LiveData<List<Txn>> queryAll();
}