package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.accounting.model.room.bean.CardInfo;
import com.example.accounting.model.room.bean.PostInfo;
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

    @Query("select " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate and :endDate and TT_id = 1) as dining, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate and :endDate and TT_id = 2) as transportation, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate and :endDate and TT_id = 3) as shopping, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate and :endDate and TT_id = 4) as entertainment, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate and :endDate and TT_id = 5) as salary, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate and :endDate and TT_id = 6) as bonus, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate and :endDate and TT_id = 7) as investment"
    )
    LiveData<PostInfo> queryPostInfo(String startDate, String endDate);

    @Query("select " +
            "dayIncome, dayExpense, (dayIncome + dayExpense) as dayAmount, " +
            "threeDayIncome, threeDayExpense, (threeDayIncome + threeDayExpense) as threeDayAmount, " +
            "weekIncome, weekExpense, (weekIncome + weekExpense) as weekAmount, " +
            "monthIncome, monthExpense, (monthIncome + monthExpense) as monthAmount, " +
            "threeMonthIncome, threeMonthExpense, (threeMonthIncome + threeMonthExpense) as threeMonthAmount, " +
            "sixMonthIncome, sixMonthExpense, (sixMonthIncome + sixMonthExpense) as sixMonthAmount, " +
            "yearIncome, yearExpense, (yearIncome + yearExpense) as yearAmount, " +
            "threeYearIncome, threeYearExpense, (threeYearIncome + threeYearExpense) as threeYearAmount, " +
            "fiveYearIncome, fiveYearExpense, (fiveYearIncome + fiveYearExpense) as fiveYearAmount " +
            "from ( select " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate1 and :endDate and T_amount > 0) AS dayIncome, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate1 and :endDate and T_amount < 0) AS dayExpense, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate2 and :endDate and T_amount > 0) AS threeDayIncome, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate2 and :endDate and T_amount < 0) AS threeDayExpense, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate3 and :endDate and T_amount > 0) AS weekIncome, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate3 and :endDate and T_amount < 0) AS weekExpense, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate4 and :endDate and T_amount > 0) AS monthIncome, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate4 and :endDate and T_amount < 0) AS monthExpense, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate5 and :endDate and T_amount > 0) AS threeMonthIncome, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate5 and :endDate and T_amount < 0) AS threeMonthExpense, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate6 and :endDate and T_amount > 0) AS sixMonthIncome, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate6 and :endDate and T_amount < 0) AS sixMonthExpense, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate7 and :endDate and T_amount > 0) AS yearIncome, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate7 and :endDate and T_amount < 0) AS yearExpense, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate8 and :endDate and T_amount > 0) AS threeYearIncome, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate8 and :endDate and T_amount < 0) AS threeYearExpense, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate9 and :endDate and T_amount > 0) AS fiveYearIncome, " +
            "(select ifnull(sum(T_amount), 0.0) from Txn where T_date between :startDate9 and :endDate and T_amount < 0) AS fiveYearExpense " +
            ") as subquery"
    )
    LiveData<CardInfo> queryCardInfo(String endDate, String startDate1, String startDate2, String startDate3, String startDate4, String startDate5, String startDate6, String startDate7, String startDate8, String startDate9);
}