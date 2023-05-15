package com.example.accounting.ui.viewmodel.activity;

import androidx.lifecycle.MutableLiveData;

import com.example.accounting.base.BaseActivityViewModel;
import com.example.accounting.model.entity.AcctTypeForm;
import com.example.accounting.model.repository.AcctTypeRepository;
import com.example.accounting.model.room.bean.AcctType;

public class AddAcctActViewModel extends BaseActivityViewModel
{
    private final MutableLiveData<AcctTypeForm> formData = new MutableLiveData<>();
    private final AcctTypeRepository acctTypeRepository = new AcctTypeRepository();

    public AddAcctActViewModel()
    {
        super();
    }

    public MutableLiveData<AcctTypeForm> getFormData()
    {
        return formData;
    }

    public void initFormData()
    {
        AcctTypeForm acctTypeForm = new AcctTypeForm(null, null);
        formData.setValue(acctTypeForm);
    }

    public int insertAcctType()
    {
        AcctTypeForm acctTypeForm = formData.getValue();
        if (acctTypeForm == null) return 0;
        else if (acctTypeForm.getType() == null) return -1;
        else if (acctTypeForm.getAmountText() == null) return -2;
        else if (!isDouble(acctTypeForm.getAmountText())) return -3;
        double amount = Double.parseDouble(acctTypeForm.getAmountText());
        acctTypeRepository.insert(new AcctType(0, acctTypeForm.getType(), amount));
        return 1;
    }

    private boolean isDouble(String amountText)
    {
        try
        {
            Double.parseDouble(amountText);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
}