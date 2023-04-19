package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.accounting.model.room.bean.AccountType;

import java.util.List;

@Dao
public interface AccountTypeDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AccountType accountType);

    @Delete()
    void delete(AccountType accountType);

    @Query("delete from AccountType")
    void deleteAll();

    @Update
    void update(AccountType accountType);

    @Query("select * from AccountType where AT_id = :id")
    LiveData<AccountType> queryById(int id);

    @Query("select * from AccountType")
    LiveData<List<AccountType>> queryAll();
}