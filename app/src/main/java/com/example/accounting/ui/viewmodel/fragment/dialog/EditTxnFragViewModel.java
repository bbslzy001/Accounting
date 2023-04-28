package com.example.accounting.ui.viewmodel.fragment.dialog;

import com.example.accounting.base.BaseFragmentViewModel;

public class EditTxnFragViewModel extends BaseFragmentViewModel
{
    private int txnInfoId;
    private String[] txnAcctList = new String[]{"Option 1", "Option 2", "Option 3", "Option 4", "Option 5", "Option 6", "Option 7", "Option 8", "Option 9", "Option 10", "Option 11", "Option 12", "Option 13"};
    private String[] txnTypeList = new String[]{"Option 1", "Option 2", "Option 3", "Option 4", "Option 5"};
    private double amount = -99.99;

    public EditTxnFragViewModel()
    {
        super();
    }

    public void setTxnInfoId(int txnInfoId)
    {
        this.txnInfoId = txnInfoId;
    }

    public int getTxnInfoId()
    {
        return txnInfoId;
    }

    public String[] getTxnAcctList()
    {
        return txnAcctList;
    }

    public String[] getTxnTypeList()
    {
        return txnTypeList;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public void update()
    {
    }
}