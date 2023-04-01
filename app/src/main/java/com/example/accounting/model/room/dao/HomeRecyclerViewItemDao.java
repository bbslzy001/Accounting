package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.accounting.model.room.bean.HomeRecyclerViewItem;

import java.util.List;

@Dao
public interface HomeRecyclerViewItemDao
{
//    @Query("")
//    LiveData<List<HomeRecyclerViewItem>> queryAllExpenditures();
//
//    @Query("")
//    LiveData<List<HomeRecyclerViewItem>> queryAllIncomes();

    @Query("SELECT TradeInfo.TI_id AS tradeInfoId, " +
            "TradeInfo.TI_amount AS amount, " +
            "TradeInfo.TI_date AS date, " +
            "TradeInfo.TI_time AS time, " +
            "TradeInfo.TI_remark AS remark, " +
            "AccountType.AT_type AS accountType, " +
            "TradeType.TT_type AS tradeType " +
            "FROM TradeInfo " +
            "INNER JOIN AccountType ON TradeInfo.AT_id = AccountType.AT_id " +
            "INNER JOIN TradeType ON TradeInfo.TT_id = TradeType.TT_id")
    LiveData<List<HomeRecyclerViewItem>> queryAll();
}