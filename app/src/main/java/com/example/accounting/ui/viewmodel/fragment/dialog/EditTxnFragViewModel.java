package com.example.accounting.ui.viewmodel.fragment.dialog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.accounting.base.BaseFragmentViewModel;
import com.example.accounting.model.repository.AcctTypeRepository;
import com.example.accounting.model.repository.TxnInfoRepository;
import com.example.accounting.model.repository.TxnTypeRepository;
import com.example.accounting.model.room.bean.AcctType;
import com.example.accounting.model.room.bean.TxnInfo;
import com.example.accounting.model.room.bean.TxnType;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class EditTxnFragViewModel extends BaseFragmentViewModel
{
    private int txnInfoId;
    private String[] acctTypeArray;
    private String[] txnTypeArray;
    private long milliseconds;
    private int hour;
    private int minute;
    private boolean isIncome;
    private String remark;
    private double amount;
    private LiveData<List<TxnType>> txnTypeListLiveData;
    private LiveData<List<AcctType>> acctTypeListLiveData;
    private LiveData<TxnInfo> txnInfoLiveData;

    private final AcctTypeRepository acctTypeRepository = new AcctTypeRepository();
    private final TxnTypeRepository txnTypeRepository = new TxnTypeRepository();
    private final TxnInfoRepository txnInfoRepository = new TxnInfoRepository();
    private MutableLiveData<Integer> isAllCompleted = new MutableLiveData<>(3);

    public MutableLiveData<Integer> getIsAllCompleted()
    {
        return isAllCompleted;
    }

    public EditTxnFragViewModel()
    {
        super();
    }

    public void initTxnInfo(int txnInfoId)
    {
        this.txnInfoId = txnInfoId;

        txnTypeListLiveData = txnTypeRepository.queryAll();
        acctTypeListLiveData = acctTypeRepository.queryAll();
        txnInfoLiveData = txnInfoRepository.queryById(txnInfoId);
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

        txnInfoLiveData.observeForever(new Observer<>()
        {
            @Override
            public void onChanged(TxnInfo txnInfo)
            {
                if (txnInfo != null)
                {
                    initDatePickerData(txnInfo.getDate());
                    initTimePickerData(txnInfo.getTime());
                    initAmountTextAndToggleButton(txnInfo.getAmount());
                    initRemarkText(txnInfo.getRemark());
                    isAllCompleted.setValue(isAllCompleted.getValue() - 1);
                    txnInfoLiveData.removeObserver(this);
                }
            }
        });
    }

    private void initDatePickerData(String date)
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")); // 创建UTC时区的Calendar对象
        String[] dateParts = date.split("/"); // 分割日期字符串
        int year = Integer.parseInt(dateParts[0]); // 获取年份
        int month = Integer.parseInt(dateParts[1]) - 1; // 获取月份（Calendar月份从0开始）
        int day = Integer.parseInt(dateParts[2]); // 获取日
        calendar.set(year, month, day, 0, 0, 0); // 设置Calendar对象的时间为指定日期的第一个时刻
        calendar.set(Calendar.MILLISECOND, 0); // 将毫秒数设置为0
        milliseconds = calendar.getTimeInMillis(); // 获取时间戳
    }

    private void initTimePickerData(String time)
    {
        String[] timeParts = time.split(":"); // 分割时间字符串
        hour = Integer.parseInt(timeParts[0]); // 获取小时
        minute = Integer.parseInt(timeParts[1]); // 获取分钟
    }

    private void initAmountTextAndToggleButton(Double amount)
    {
        isIncome = amount > 0;
        this.amount = Math.abs(amount);
    }

    private void initRemarkText(String remark)
    {
        this.remark = remark;
    }

    public String[] getAcctTypeArray()
    {
        return acctTypeArray;
    }

    public String[] getTxnTypeArray()
    {
        return txnTypeArray;
    }

    public long getMilliseconds()
    {
        return milliseconds;
    }

    public int getHour()
    {
        return hour;
    }

    public int getMinute()
    {
        return minute;
    }

    public boolean isIncome()
    {
        return isIncome;
    }

    public String getRemark()
    {
        return remark;
    }

    public double getAmount()
    {
        return amount;
    }
}