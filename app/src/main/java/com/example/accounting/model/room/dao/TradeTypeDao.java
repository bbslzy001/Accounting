package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.accounting.model.room.bean.TradeType;

import java.util.List;

@Dao
public interface TradeTypeDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TradeType tradeType);

    @Delete()
    void delete(TradeType tradeType);

    @Query("delete from TradeType")
    void deleteAll();

    @Update
    void update(TradeType tradeType);

    @Query("select * from TradeType where TT_id = :id")
    LiveData<TradeType> queryById(int id);

    @Query("select * from TradeType")
    LiveData<List<TradeType>> queryAll();
}