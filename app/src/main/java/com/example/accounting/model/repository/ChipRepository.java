package com.example.accounting.model.repository;

import androidx.lifecycle.LiveData;

import com.example.accounting.model.room.bean.Chip;
import com.example.accounting.model.room.dao.ChipDao;
import com.example.accounting.model.room.database.MyDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChipRepository
{
    private final ChipDao chipDao;
    private final ExecutorService executor;

    public ChipRepository()
    {
        MyDatabase myDatabase = MyDatabase.getMyDatabaseInstance();
        chipDao = myDatabase.getChipDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insert(Chip chip)
    {
        executor.execute(() -> chipDao.insert(chip));
    }

    public void delete(Chip chip)
    {
        executor.execute(() -> chipDao.delete(chip));
    }

    public void update(Chip chip)
    {
        executor.execute(() -> chipDao.update(chip));
    }

    public LiveData<List<Chip>> queryAll()
    {
        return chipDao.queryAll();
    }
}