package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.accounting.model.room.bean.Income;

import java.util.List;

@Dao
public interface IncomeDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Income income);

    @Delete()
    void delete(Income income);

    @Query("delete from Income")
    void deleteAll();

    @Update
    void update(Income income);

    @Query("select * from Income where I_id = :id")
    LiveData<Income> queryById(int id);

    @Query("select * from Income")
    LiveData<List<Income>> queryAll();
}