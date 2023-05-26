package com.example.accounting.ui.viewmodel.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.accounting.base.BaseActivityViewModel;
import com.example.accounting.model.entity.TxnForm;
import com.example.accounting.model.repository.AcctRepository;
import com.example.accounting.model.repository.TxnRepository;
import com.example.accounting.model.repository.TxnTypeRepository;
import com.example.accounting.model.room.bean.Acct;
import com.example.accounting.model.room.bean.Txn;
import com.example.accounting.model.room.bean.TxnType;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class AddTxnActViewModel extends BaseActivityViewModel
{
    private String[] acctArray;
    private String[] txnTypeArray;
    private LiveData<List<TxnType>> txnTypeListLiveData;
    private LiveData<List<Acct>> acctListLiveData;
    private final MutableLiveData<TxnForm> formData = new MutableLiveData<>();
    private final MutableLiveData<Integer> isAllCompleted = new MutableLiveData<>(2);
    private final AcctRepository acctRepository = new AcctRepository();
    private final TxnTypeRepository txnTypeRepository = new TxnTypeRepository();
    private final TxnRepository txnRepository = new TxnRepository();

    public AddTxnActViewModel()
    {
        super();
    }

    public String[] getAcctArray()
    {
        return acctArray;
    }

    public String[] getTxnTypeArray()
    {
        return txnTypeArray;
    }

    public MutableLiveData<TxnForm> getFormData()
    {
        return formData;
    }

    public MutableLiveData<Integer> isAllCompleted()
    {
        return isAllCompleted;
    }

    public String getDate()
    {
        return Objects.requireNonNull(formData.getValue()).getDate();
    }

    public String getTime()
    {
        return Objects.requireNonNull(formData.getValue()).getTime();
    }

    public long getMilliseconds()
    {
        return Objects.requireNonNull(formData.getValue()).getMilliseconds();
    }

    public int getHour()
    {
        return Objects.requireNonNull(formData.getValue()).getHour();
    }

    public int getMinute()
    {
        return Objects.requireNonNull(formData.getValue()).getMinute();
    }

    public void initFormData()
    {
        TxnForm txnForm = new TxnForm(0, null, null, null, Calendar.getInstance(), null);
        formData.setValue(txnForm);
    }

    public void initDropdownData()
    {
        txnTypeListLiveData = txnTypeRepository.queryAll();
        acctListLiveData = acctRepository.queryAll();

        txnTypeListLiveData.observeForever(new Observer<>()
        {
            @Override
            public void onChanged(List<TxnType> txnTypes)
            {
                if (txnTypes != null)
                {
                    txnTypeArray = new String[txnTypes.size()];
                    for (int i = 0; i < txnTypes.size(); i++)
                        txnTypeArray[i] = txnTypes.get(i).getType();
                    isAllCompleted.setValue(isAllCompleted.getValue() - 1);
                    txnTypeListLiveData.removeObserver(this);
                }
            }
        });

        acctListLiveData.observeForever(new Observer<>()
        {
            @Override
            public void onChanged(List<Acct> accts)
            {
                if (accts != null)
                {
                    acctArray = new String[accts.size()];
                    for (int i = 0; i < accts.size(); i++)
                        acctArray[i] = accts.get(i).getName();
                    isAllCompleted.setValue(isAllCompleted.getValue() - 1);
                    acctListLiveData.removeObserver(this);
                }
            }
        });
    }

    public int insertTxn()
    {
        TxnForm txnForm = formData.getValue();
        if (txnForm == null) return 0;
        else if (txnForm.getAmountText() == null) return -1;
        else if (txnForm.getAcctName() == null) return -2;
        else if (txnForm.getTxnType() == null) return -3;
        else if (!isPositiveDouble(txnForm.getAmountText())) return -4;
        double amount = txnForm.getIncomeOrExpense() == 0 ? Double.parseDouble(txnForm.getAmountText()) : -Double.parseDouble(txnForm.getAmountText());
        int acctId = 0;
        int txnTypeId = 0;
        for (Acct type : Objects.requireNonNull(acctListLiveData.getValue()))
        {
            if (type.getName().equals(txnForm.getAcctName()))
            {
                acctId = type.getId();
                break;
            }
        }
        for (TxnType type : Objects.requireNonNull(txnTypeListLiveData.getValue()))
        {
            if (type.getType().equals(txnForm.getTxnType()))
            {
                txnTypeId = type.getId();
                break;
            }
        }
        txnRepository.insert(new Txn(0, amount, txnForm.getDate(), txnForm.getTime(), txnForm.getRemark(), acctId, txnTypeId));
        return 1;
    }

    private boolean isPositiveDouble(String amountText)
    {
        try
        {
            double amount = Double.parseDouble(amountText);
            return amount > 0.0;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
}