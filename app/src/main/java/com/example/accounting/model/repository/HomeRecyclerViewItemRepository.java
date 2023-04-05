package com.example.accounting.model.repository;

import androidx.lifecycle.LiveData;

import com.example.accounting.model.room.bean.HomeRecyclerViewItem;
import com.example.accounting.model.room.dao.HomeRecyclerViewItemDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.util.List;

public class HomeRecyclerViewItemRepository
{
    private HomeRecyclerViewItemDao homeRecyclerViewItemDao;

    public HomeRecyclerViewItemRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        homeRecyclerViewItemDao = myDatabase.getHomeRecyclerViewItemDao();
    }

    public LiveData<Double> queryExpenditureAmount()
    {
        return homeRecyclerViewItemDao.queryExpenditureAmount();
    }

    public LiveData<Double> queryIncomeAmount()
    {
        return homeRecyclerViewItemDao.queryIncomeAmount();
    }

    public LiveData<Double> queryTotalAmount()
    {
        return homeRecyclerViewItemDao.queryTotalAmount();
    }

    public LiveData<List<HomeRecyclerViewItem>> queryAllExpenditures()
    {
        return homeRecyclerViewItemDao.queryAllExpenditures();
    }

    public LiveData<List<HomeRecyclerViewItem>> queryAllIncomes()
    {
        return homeRecyclerViewItemDao.queryAllIncomes();
    }

    public LiveData<List<HomeRecyclerViewItem>> queryAll()
    {
        return homeRecyclerViewItemDao.queryAll();
    }
}