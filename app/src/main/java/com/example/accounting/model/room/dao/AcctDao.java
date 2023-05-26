package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.accounting.model.room.bean.Acct;

import java.util.List;

@Dao
public interface AcctDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Acct acct);

    @Delete()
    void delete(Acct acct);

    @Query("delete from Acct")
    void deleteAll();

    @Update
    void update(Acct acct);

    @Query("select Acct.A_id, " +
            "Acct.A_name, " +
            "Acct.A_amount + IFNULL(SUM(Txn.T_amount), 0.0) as A_amount " +
            "from Acct " +
            "left join Txn on Acct.A_id = Txn.A_id " +
            "where Acct.A_id = :id")
    LiveData<Acct> queryById(int id);

    @Query("select Acct.A_id, " +
            "Acct.A_name, " +
            "Acct.A_amount + IFNULL(SUM(Txn.T_amount), 0.0) as A_amount " +
            "from Acct " +
            "left join Txn on Acct.A_id = Txn.A_id " +
            "group by Acct.A_id")
    LiveData<List<Acct>> queryAll();
}