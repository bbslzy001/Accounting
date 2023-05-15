package com.example.accounting.ui.viewmodel.activity;

import androidx.lifecycle.MutableLiveData;

import com.example.accounting.base.BaseActivityViewModel;
import com.example.accounting.model.entity.AcctForm;
import com.example.accounting.model.repository.AcctRepository;
import com.example.accounting.model.room.bean.Acct;

public class AddAcctActViewModel extends BaseActivityViewModel
{
    private final MutableLiveData<AcctForm> formData = new MutableLiveData<>();
    private final AcctRepository acctRepository = new AcctRepository();

    public AddAcctActViewModel()
    {
        super();
    }

    public MutableLiveData<AcctForm> getFormData()
    {
        return formData;
    }

    public void initFormData()
    {
        AcctForm acctForm = new AcctForm(null, null);
        formData.setValue(acctForm);
    }

    public int insertAcct()
    {
        AcctForm acctForm = formData.getValue();
        if (acctForm == null) return 0;
        else if (acctForm.getName() == null) return -1;
        else if (acctForm.getAmountText() == null) return -2;
        else if (!isDouble(acctForm.getAmountText())) return -3;
        double amount = Double.parseDouble(acctForm.getAmountText());
        acctRepository.insert(new Acct(0, acctForm.getName(), amount));
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