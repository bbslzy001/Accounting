package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.accounting.model.room.bean.AcctType;

import java.util.List;

@Dao
public interface AcctTypeDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AcctType acctType);

    @Delete()
    void delete(AcctType acctType);

    @Query("delete from AcctType")
    void deleteAll();

    @Update
    void update(AcctType acctType);

    @Query("select * from AcctType where AT_id = :id")
    LiveData<AcctType> queryById(int id);

    @Query("select * from AcctType")
    LiveData<List<AcctType>> queryAll();
}