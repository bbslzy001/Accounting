package com.example.accounting.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.accounting.model.room.bean.HomeRvItem;

import java.util.List;

@Dao
public interface HomeRvItemDao
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
            "where TradeInfo.TI_amount < 0 " +
            "order by date asc, time asc"
    )
    LiveData<List<HomeRvItem>> queryAllExpenditures();

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
            "where TradeInfo.TI_amount >= 0 " +
            "order by date asc, time asc"
    )
    LiveData<List<HomeRvItem>> queryAllIncomes();

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
            "order by date asc, time asc"
    )
    LiveData<List<HomeRvItem>> queryAll();
}