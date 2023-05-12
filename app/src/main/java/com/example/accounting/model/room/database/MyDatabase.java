package com.example.accounting.model.room.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.accounting.base.BaseApplication;
import com.example.accounting.model.room.bean.AcctType;
import com.example.accounting.model.room.bean.TxnInfo;
import com.example.accounting.model.room.bean.TxnType;
import com.example.accounting.model.room.dao.AcctTypeDao;
import com.example.accounting.model.room.dao.TxnInfoDao;
import com.example.accounting.model.room.dao.TxnRvItemDao;
import com.example.accounting.model.room.dao.TxnTypeDao;
import com.example.accounting.model.room.dao.YearMonthDao;

@Database(
        entities = {
                TxnInfo.class,
                AcctType.class,
                TxnType.class
        },
        version = 1
)
public abstract class MyDatabase extends RoomDatabase
{
    private static final String DB_NAME = "Accounting.db";
    private static MyDatabase myDatabase;

    public abstract TxnInfoDao getTxnInfoDao();

    public abstract AcctTypeDao getAcctDao();

    public abstract TxnTypeDao getTxnTypeDao();

    public abstract TxnRvItemDao getTxnRvItemDao();

    public abstract YearMonthDao getYearMonthDao();

    public static MyDatabase getMyDatabaseInstance()
    {
        if (myDatabase == null) myDatabase = createMyDatabase();
        return myDatabase;
    }

    private static MyDatabase createMyDatabase()
    {
        return Room.databaseBuilder(BaseApplication.getContext(), MyDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration()  // 构建实例时销毁原数据库（仅开发时使用）
                .build();
    }
}