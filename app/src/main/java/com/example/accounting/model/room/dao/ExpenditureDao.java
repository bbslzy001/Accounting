package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.accounting.model.room.bean.Expenditure;

import java.util.List;

@Dao
public interface ExpenditureDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Expenditure expenditure);

    @Delete()
    void delete(Expenditure expenditure);

    @Query("delete from Expenditure")
    void deleteAll();

    @Update
    void update(Expenditure expenditure);

    @Query("select * from Expenditure where E_id = :id")
    LiveData<Expenditure> queryById(int id);

    @Query("select * from Expenditure")
    LiveData<List<Expenditure>> queryAll();
}