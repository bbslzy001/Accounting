package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.accounting.model.room.bean.HomeRecyclerViewItem;

import java.util.List;

@Dao
public interface HomeRecyclerViewItemDao
{
    @Query("select SUM(TI_amount) from TradeInfo where TI_amount < 0")
    LiveData<Double> queryExpenditureAmount();

    @Query("select SUM(TI_amount) from TradeInfo where TI_amount >= 0")
    LiveData<Double> queryIncomeAmount();

    @Query("select SUM(TI_amount) from TradeInfo")
    LiveData<Double> queryTotalAmount();

    @Query("select TradeInfo.TI_id as tradeInfoId, " +
            "TradeInfo.TI_amount as amount, " +
            "TradeInfo.TI_date as date, " +
            "TradeInfo.TI_time as time, " +
            "TradeInfo.TI_remark as remark, " +
            "AccountType.AT_type as accountType, " +
            "TradeType.TT_type as tradeType " +
            "from TradeInfo " +
            "inner join AccountType on TradeInfo.AT_id = AccountType.AT_id " +
            "inner join TradeType on TradeInfo.TT_id = TradeType.TT_id " +
            "where TradeInfo.TI_amount < 0"
    )
    LiveData<List<HomeRecyclerViewItem>> queryAllExpenditures();

    @Query("select TradeInfo.TI_id as tradeInfoId, " +
            "TradeInfo.TI_amount as amount, " +
            "TradeInfo.TI_date as date, " +
            "TradeInfo.TI_time as time, " +
            "TradeInfo.TI_remark as remark, " +
            "AccountType.AT_type as accountType, " +
            "TradeType.TT_type as tradeType " +
            "from TradeInfo " +
            "inner join AccountType on TradeInfo.AT_id = AccountType.AT_id " +
            "inner join TradeType on TradeInfo.TT_id = TradeType.TT_id " +
            "where TradeInfo.TI_amount >= 0"
    )
    LiveData<List<HomeRecyclerViewItem>> queryAllIncomes();

    @Query("select TradeInfo.TI_id as tradeInfoId, " +
            "TradeInfo.TI_amount as amount, " +
            "TradeInfo.TI_date as date, " +
            "TradeInfo.TI_time as time, " +
            "TradeInfo.TI_remark as remark, " +
            "AccountType.AT_type as accountType, " +
            "TradeType.TT_type as tradeType " +
            "from TradeInfo " +
            "inner join AccountType on TradeInfo.AT_id = AccountType.AT_id " +
            "inner join TradeType on TradeInfo.TT_id = TradeType.TT_id"
    )
    LiveData<List<HomeRecyclerViewItem>> queryAll();
}