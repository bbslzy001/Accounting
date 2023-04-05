package com.example.accounting.model.room.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.accounting.application.MyApplication;
import com.example.accounting.model.room.bean.AccountType;
import com.example.accounting.model.room.bean.TradeInfo;
import com.example.accounting.model.room.bean.TradeType;
import com.example.accounting.model.room.dao.AccountTypeDao;
import com.example.accounting.model.room.dao.HomeRecyclerViewItemDao;
import com.example.accounting.model.room.dao.TradeInfoDao;
import com.example.accounting.model.room.dao.TradeTypeDao;

@Database(
        entities = {
                TradeInfo.class,
                AccountType.class,
                TradeType.class
        },
        version = 1
)
public abstract class MyDatabase extends RoomDatabase
{
    private static final String DB_NAME = "Accounting.db";
    private static MyDatabase myDatabase;

    public abstract TradeInfoDao getTradeInfoDao();
    public abstract AccountTypeDao getAccountDao();
    public abstract TradeTypeDao getTradeTypeDao();

    public abstract HomeRecyclerViewItemDao getHomeRecyclerViewItemDao();

    public static MyDatabase getMyDatabaseInstance()
    {
        if (myDatabase == null) myDatabase = createMyDatabase();
        return myDatabase;
    }

    private static MyDatabase createMyDatabase()
    {
        return Room.databaseBuilder(MyApplication.getContext(), MyDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();  // 构建实例时销毁原数据库（仅开发时使用）
    }
}