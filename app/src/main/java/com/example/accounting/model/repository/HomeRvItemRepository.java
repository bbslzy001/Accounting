package com.example.accounting.model.repository;

import androidx.lifecycle.LiveData;

import com.example.accounting.model.room.bean.HomeRvItem;
import com.example.accounting.model.room.dao.HomeRvItemDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.util.List;

public class HomeRvItemRepository
{
    private final HomeRvItemDao homeRvItemDao;

    public HomeRvItemRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        homeRvItemDao = myDatabase.getHomeRecyclerViewItemDao();
    }

    public LiveData<Double> queryExpenditureAmount()
    {
        return homeRvItemDao.queryExpenditureAmount();
    }

    public LiveData<Double> queryIncomeAmount()
    {
        return homeRvItemDao.queryIncomeAmount();
    }

    public LiveData<Double> queryTotalAmount()
    {
        return homeRvItemDao.queryTotalAmount();
    }

    public LiveData<List<HomeRvItem>> queryAllExpenditures()
    {
        return homeRvItemDao.queryAllExpenditures();
    }

    public LiveData<List<HomeRvItem>> queryAllIncomes()
    {
        return homeRvItemDao.queryAllIncomes();
    }

    public LiveData<List<HomeRvItem>> queryAll()
    {
        return homeRvItemDao.queryAll();
    }
}