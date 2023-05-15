package com.example.accounting.ui.viewmodel.activity;

import com.example.accounting.base.BaseActivityViewModel;
import com.example.accounting.model.repository.AcctTypeRepository;
import com.example.accounting.model.room.bean.AcctType;

public class AddAcctActViewModel extends BaseActivityViewModel
{
    private final AcctTypeRepository acctTypeRepository = new AcctTypeRepository();

    public AddAcctActViewModel()
    {
        super();
    }

    public void addAcctType(String type, double amount)
    {
        acctTypeRepository.insert(new AcctType(0, type, amount));
    }
}