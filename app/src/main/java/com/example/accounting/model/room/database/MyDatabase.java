package com.example.accounting.model.room.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.accounting.base.BaseApplication;
import com.example.accounting.model.room.bean.Acct;
import com.example.accounting.model.room.bean.Chip;
import com.example.accounting.model.room.bean.Txn;
import com.example.accounting.model.room.bean.TxnType;
import com.example.accounting.model.room.dao.AcctDao;
import com.example.accounting.model.room.dao.ChipDao;
import com.example.accounting.model.room.dao.TxnDao;
import com.example.accounting.model.room.dao.TxnRvItemDao;
import com.example.accounting.model.room.dao.TxnTypeDao;
import com.example.accounting.model.room.dao.YearMonthDao;

@Database(
        entities = {
                Txn.class,
                Acct.class,
                TxnType.class,
                Chip.class
        },
        version = 1
)
public abstract class MyDatabase extends RoomDatabase
{
    private static final String DB_NAME = "Accounting.db";
    private static MyDatabase myDatabase;

    public abstract TxnDao getTxnDao();

    public abstract AcctDao getAcctDao();

    public abstract TxnTypeDao getTxnTypeDao();

    public abstract TxnRvItemDao getTxnRvItemDao();

    public abstract YearMonthDao getYearMonthDao();

    public abstract ChipDao getChipDao();

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