package com.example.accounting.ui.viewmodel.activity;

import androidx.lifecycle.LiveData;

import com.example.accounting.base.BaseActivityViewModel;
import com.example.accounting.model.repository.ChipRepository;
import com.example.accounting.model.room.bean.Chip;

import java.util.ArrayList;
import java.util.List;

public class DashboardActViewModel extends BaseActivityViewModel
{
    private LiveData<List<Chip>> chipList;
    private final ChipRepository chipRepository = new ChipRepository();

    public DashboardActViewModel()
    {
        super();
        initChipList();
    }

    private void initChipList()
    {
        chipList = chipRepository.queryAll();
    }

    public LiveData<List<Chip>> getChipList()
    {
        return chipList;
    }

    public void updateChip(Chip chip)
    {
        chipRepository.update(chip);
    }
}