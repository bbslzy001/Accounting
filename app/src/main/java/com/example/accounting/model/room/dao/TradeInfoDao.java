package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.accounting.model.room.bean.TradeInfo;

import java.util.List;

@Dao
public interface TradeInfoDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TradeInfo expenditure);

    @Delete()
    void delete(TradeInfo expenditure);

    @Query("delete from TradeInfo")
    void deleteAll();

    @Update
    void update(TradeInfo expenditure);

    @Query("select * from TradeInfo where TI_id = :id")
    LiveData<TradeInfo> queryById(int id);

    @Query("select * from TradeInfo")
    LiveData<List<TradeInfo>> queryAll();
}