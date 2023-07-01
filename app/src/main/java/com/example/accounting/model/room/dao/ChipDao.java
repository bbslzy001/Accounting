package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.accounting.model.room.bean.Chip;

import java.util.List;

@Dao
public interface ChipDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Chip chip);

    @Delete
    void delete(Chip chip);

    @Update
    void update(Chip chip);

    @Query("select * from Chip")
    LiveData<List<Chip>> queryAll();
}