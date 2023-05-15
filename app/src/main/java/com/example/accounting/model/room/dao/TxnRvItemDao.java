package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.accounting.model.room.bean.TxnRvItem;

import java.util.List;

@Dao
public interface TxnRvItemDao
{
    @Query("select ifnull(sum(Txn.T_amount), 0.0) as totalAmount " +
            "from Txn " +
            "inner join Acct on Txn.A_id = Acct.A_id " +
            "inner join TxnType on Txn.TT_id = TxnType.TT_id " +
            "where Txn.T_date like :year || '/' || :month || '/%' " +
            "and Txn.T_amount > 0")
    LiveData<Double> queryIncomeByMonth(String year, String month);

    @Query("select ifnull(sum(Txn.T_amount), 0.0) as totalAmount " +
            "from Txn " +
            "inner join Acct on Txn.A_id = Acct.A_id " +
            "inner join TxnType on Txn.TT_id = TxnType.TT_id " +
            "where Txn.T_date like :year || '/' || :month || '/%' " +
            "and Txn.T_amount < 0")
    LiveData<Double> queryExpenseByMonth(String year, String month);

    @Query("select Txn.T_id as txnId, " +
            "Txn.T_amount as amount, " +
            "Txn.T_date as date, " +
            "Txn.T_time as time, " +
            "Txn.T_remark as remark, " +
            "Acct.A_name as acctName, " +
            "TxnType.TT_type as txnType " +
            "from Txn " +
            "inner join Acct on Txn.A_id = Acct.A_id " +
            "inner join TxnType on Txn.TT_id = TxnType.TT_id " +
            "where date like :year || '/' || :month || '/%' " +
            "order by date asc, time asc")
    LiveData<List<TxnRvItem>> queryAllByMonth(String year, String month);

    @Query("select Txn.T_id as txnId, " +
            "Txn.T_amount as amount, " +
            "Txn.T_date as date, " +
            "Txn.T_time as time, " +
            "Txn.T_remark as remark, " +
            "Acct.A_name as acctName, " +
            "TxnType.TT_type as txnType " +
            "from Txn " +
            "inner join Acct on Txn.A_id = Acct.A_id " +
            "inner join TxnType on Txn.TT_id = TxnType.TT_id " +
            "where date = :date " +
            "order by date asc, time asc")
    LiveData<List<TxnRvItem>> queryAllByDay(String date);

    @Query("select Txn.T_id as txnId, " +
            "Txn.T_amount as amount, " +
            "Txn.T_date as date, " +
            "Txn.T_time as time, " +
            "Txn.T_remark as remark, " +
            "Acct.A_name as acctName, " +
            "TxnType.TT_type as txnType " +
            "from Txn " +
            "inner join Acct on Txn.A_id = Acct.A_id " +
            "inner join TxnType on Txn.TT_id = TxnType.TT_id " +
            "where Txn.A_id = :acctId " +
            "order by date asc, time asc")
    LiveData<List<TxnRvItem>> queryAllByAcctId(int acctId);
}