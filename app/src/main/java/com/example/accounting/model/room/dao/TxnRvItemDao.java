package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.accounting.model.room.bean.TxnRvItem;

import java.util.List;

@Dao
public interface TxnRvItemDao
{
    @Query("select ifnull(sum(TxnInfo.TI_amount), 0.0) as totalAmount " +
            "from TxnInfo " +
            "inner join AcctType on TxnInfo.AT_id = AcctType.AT_id " +
            "inner join TxnType on TxnInfo.TT_id = TxnType.TT_id " +
            "where TxnInfo.TI_date like :year || '/' || :month || '/%' " +
            "and TxnInfo.TI_amount > 0")
    LiveData<Double> queryIncomeByMonth(String year, String month);

    @Query("select ifnull(sum(TxnInfo.TI_amount), 0.0) as totalAmount " +
            "from TxnInfo " +
            "inner join AcctType on TxnInfo.AT_id = AcctType.AT_id " +
            "inner join TxnType on TxnInfo.TT_id = TxnType.TT_id " +
            "where TxnInfo.TI_date like :year || '/' || :month || '/%' " +
            "and TxnInfo.TI_amount < 0")
    LiveData<Double> queryExpenseByMonth(String year, String month);

    @Query("select TxnInfo.TI_id as txnInfoId, " +
            "TxnInfo.TI_amount as amount, " +
            "TxnInfo.TI_date as date, " +
            "TxnInfo.TI_time as time, " +
            "TxnInfo.TI_remark as remark, " +
            "AcctType.AT_type as acctType, " +
            "TxnType.TT_type as txnType " +
            "from TxnInfo " +
            "inner join AcctType on TxnInfo.AT_id = AcctType.AT_id " +
            "inner join TxnType on TxnInfo.TT_id = TxnType.TT_id " +
            "where date like :year || '/' || :month || '/%' " +
            "order by date asc, time asc")
    LiveData<List<TxnRvItem>> queryAllByMonth(String year, String month);

    @Query("select TxnInfo.TI_id as txnInfoId, " +
            "TxnInfo.TI_amount as amount, " +
            "TxnInfo.TI_date as date, " +
            "TxnInfo.TI_time as time, " +
            "TxnInfo.TI_remark as remark, " +
            "AcctType.AT_type as acctType, " +
            "TxnType.TT_type as txnType " +
            "from TxnInfo " +
            "inner join AcctType on TxnInfo.AT_id = AcctType.AT_id " +
            "inner join TxnType on TxnInfo.TT_id = TxnType.TT_id " +
            "where date = :date " +
            "order by date asc, time asc")
    LiveData<List<TxnRvItem>> queryAllByDay(String date);

    @Query("select TxnInfo.TI_id as txnInfoId, " +
            "TxnInfo.TI_amount as amount, " +
            "TxnInfo.TI_date as date, " +
            "TxnInfo.TI_time as time, " +
            "TxnInfo.TI_remark as remark, " +
            "AcctType.AT_type as acctType, " +
            "TxnType.TT_type as txnType " +
            "from TxnInfo " +
            "inner join AcctType on TxnInfo.AT_id = AcctType.AT_id " +
            "inner join TxnType on TxnInfo.TT_id = TxnType.TT_id " +
            "where TxnInfo.AT_id = :acctId " +
            "order by date asc, time asc")
    LiveData<List<TxnRvItem>> queryAllByAcctId(int acctId);
}