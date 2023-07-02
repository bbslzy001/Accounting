package com.example.accounting.model.repository;

import androidx.lifecycle.LiveData;

import com.example.accounting.model.room.bean.CardInfo;
import com.example.accounting.model.room.bean.Txn;
import com.example.accounting.model.room.dao.TxnDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TxnRepository
{
    private final TxnDao txnDao;
    private final ExecutorService executor;

    public TxnRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        txnDao = myDatabase.getTxnDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insert(Txn txn)
    {
        executor.execute(() -> txnDao.insert(txn));
    }

    public void deleteById(int id)
    {
        executor.execute(() -> txnDao.deleteById(id));
    }

    public void update(Txn txn)
    {
        executor.execute(()-> txnDao.update(txn));
    }

    public LiveData<Txn> queryById(int id)
    {
        return txnDao.queryById(id);
    }

    public LiveData<List<Txn>> queryAll()
    {
        return txnDao.queryAll();
    }

    public LiveData<CardInfo> queryCardInfo()
    {
        List<String> dateRanges = getDateRanges();
        return txnDao.queryCardInfo(dateRanges.get(0), dateRanges.get(1), dateRanges.get(2), dateRanges.get(3), dateRanges.get(4), dateRanges.get(5), dateRanges.get(6), dateRanges.get(7), dateRanges.get(8), dateRanges.get(9));
    }

    private List<String> getDateRanges()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        List<String> dateRanges = new ArrayList<>();

        // Add the end date (today)
        dateRanges.add(sdf.format(cal.getTime()));

        int[] intervals = {0, 3, 7, 1, 3, 6, 1, 2, 3};
        int[] calendarFields = {Calendar.DATE, Calendar.DATE, Calendar.DATE, Calendar.MONTH, Calendar.MONTH, Calendar.MONTH, Calendar.YEAR, Calendar.YEAR, Calendar.YEAR};

        for (int i = 0; i < intervals.length; i++)
        {
            cal.add(calendarFields[i], -intervals[i]);
            dateRanges.add(sdf.format(cal.getTime()));
            cal.add(calendarFields[i], intervals[i]); // Reset to today
        }

        return dateRanges;
    }
}