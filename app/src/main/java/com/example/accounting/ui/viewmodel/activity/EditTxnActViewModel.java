package com.example.accounting.ui.viewmodel.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.accounting.base.BaseActivityViewModel;
import com.example.accounting.model.entity.TxnInfoForm;
import com.example.accounting.model.repository.AcctTypeRepository;
import com.example.accounting.model.repository.TxnInfoRepository;
import com.example.accounting.model.repository.TxnTypeRepository;
import com.example.accounting.model.room.bean.AcctType;
import com.example.accounting.model.room.bean.TxnInfo;
import com.example.accounting.model.room.bean.TxnRvItem;
import com.example.accounting.model.room.bean.TxnType;

import java.util.List;
import java.util.Objects;

public class EditTxnActViewModel extends BaseActivityViewModel
{
    private int txnInfoId;
    private String[] acctTypeArray;
    private String[] txnTypeArray;
    private LiveData<List<TxnType>> txnTypeListLiveData;
    private LiveData<List<AcctType>> acctTypeListLiveData;
    private final MutableLiveData<TxnInfoForm> formData = new MutableLiveData<>();
    private final MutableLiveData<Integer> isAllCompleted = new MutableLiveData<>(2);
    private final AcctTypeRepository acctTypeRepository = new AcctTypeRepository();
    private final TxnTypeRepository txnTypeRepository = new TxnTypeRepository();
    private final TxnInfoRepository txnInfoRepository = new TxnInfoRepository();

    public EditTxnActViewModel()
    {
        super();
    }

    public String[] getAcctTypeArray()
    {
        return acctTypeArray;
    }

    public String[] getTxnTypeArray()
    {
        return txnTypeArray;
    }

    public MutableLiveData<TxnInfoForm> getFormData()
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

    public void initFormData(TxnRvItem txnRvItem)
    {
        txnInfoId = txnRvItem.getTxnInfoId();
        int incomeOrExpense = txnRvItem.getAmount() > 0 ? 0 : 1;
        String acctType = txnRvItem.getAcctType();
        String txnType = txnRvItem.getTxnType();
        String amountText = String.valueOf(Math.abs(txnRvItem.getAmount()));
        String date = txnRvItem.getDate();
        String time = txnRvItem.getTime();
        String remark = txnRvItem.getRemark();
        TxnInfoForm txnInfoForm = new TxnInfoForm(incomeOrExpense, acctType, txnType, amountText, date, time, remark);
        formData.setValue(txnInfoForm);
    }

    public void initDropdownData()
    {
        txnTypeListLiveData = txnTypeRepository.queryAll();
        acctTypeListLiveData = acctTypeRepository.queryAll();

        txnTypeListLiveData.observeForever(new Observer<>()
        {
            @Override
            public void onChanged(List<TxnType> txnTypes)
            {
                if (txnTypes != null)
                {
                    txnTypeArray = new String[txnTypes.size()];
                    for (int i = 0; i < txnTypes.size(); i++) txnTypeArray[i] = txnTypes.get(i).getType();
                    isAllCompleted.setValue(isAllCompleted.getValue() - 1);
                    txnTypeListLiveData.removeObserver(this);
                }
            }
        });

        acctTypeListLiveData.observeForever(new Observer<>()
        {
            @Override
            public void onChanged(List<AcctType> acctTypes)
            {
                if (acctTypes != null)
                {
                    acctTypeArray = new String[acctTypes.size()];
                    for (int i = 0; i < acctTypes.size(); i++) acctTypeArray[i] = acctTypes.get(i).getType();
                    isAllCompleted.setValue(isAllCompleted.getValue() - 1);
                    acctTypeListLiveData.removeObserver(this);
                }
            }
        });
    }

    public int updateTxnInfo()
    {
        TxnInfoForm txnInfoForm = formData.getValue();
        if (txnInfoForm == null) return 0;
        else if (txnInfoForm.getAmountText() == null) return -1;
        else if (txnInfoForm.getAcctType() == null) return -2;
        else if (txnInfoForm.getTxnType() == null) return -3;
        else if (!isPositiveDouble(txnInfoForm.getAmountText())) return -4;
        double amount = txnInfoForm.getIncomeOrExpense() == 0 ? Double.parseDouble(txnInfoForm.getAmountText()) : -Double.parseDouble(txnInfoForm.getAmountText());
        int acctTypeId = 0;
        int txnTypeId = 0;
        for (AcctType type : Objects.requireNonNull(acctTypeListLiveData.getValue()))
        {
            if (type.getType().equals(txnInfoForm.getAcctType()))
            {
                acctTypeId = type.getId();
                break;
            }
        }
        for (TxnType type : Objects.requireNonNull(txnTypeListLiveData.getValue()))
        {
            if (type.getType().equals(txnInfoForm.getTxnType()))
            {
                txnTypeId = type.getId();
                break;
            }
        }
        txnInfoRepository.update(new TxnInfo(txnInfoId, amount, txnInfoForm.getDate(), txnInfoForm.getTime(), txnInfoForm.getRemark(), acctTypeId, txnTypeId));
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