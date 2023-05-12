package com.example.accounting.ui.viewmodel.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.accounting.base.BaseActivityViewModel;
import com.example.accounting.model.repository.AcctTypeRepository;
import com.example.accounting.model.repository.TxnInfoRepository;
import com.example.accounting.model.repository.TxnTypeRepository;
import com.example.accounting.model.room.bean.AcctType;
import com.example.accounting.model.room.bean.TxnInfo;
import com.example.accounting.model.room.bean.TxnRvItem;
import com.example.accounting.model.room.bean.TxnType;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

public class EditTxnActViewModel extends BaseActivityViewModel
{
    private String[] acctTypeArray;
    private String[] txnTypeArray;
    private long milliseconds;
    private int hour;
    private int minute;
    private TxnRvItem txnRvItem;
    private LiveData<List<TxnType>> txnTypeListLiveData;
    private LiveData<List<AcctType>> acctTypeListLiveData;

    private final AcctTypeRepository acctTypeRepository = new AcctTypeRepository();
    private final TxnTypeRepository txnTypeRepository = new TxnTypeRepository();
    private final TxnInfoRepository txnInfoRepository = new TxnInfoRepository();
    private MutableLiveData<Integer> isAllCompleted = new MutableLiveData<>(2);

    public MutableLiveData<Integer> getIsAllCompleted()
    {
        return isAllCompleted;
    }

    public EditTxnActViewModel()
    {
        super();
    }

    public void initTxnInfo()
    {
        initDatePickerData();
        initTimePickerData();

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
                    for (int i = 0; i < txnTypes.size(); i++)
                        txnTypeArray[i] = txnTypes.get(i).getType();
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
                    for (int i = 0; i < acctTypes.size(); i++)
                        acctTypeArray[i] = acctTypes.get(i).getType();
                    isAllCompleted.setValue(isAllCompleted.getValue() - 1);
                    acctTypeListLiveData.removeObserver(this);
                }
            }
        });
    }

    private void initDatePickerData()
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")); // 创建UTC时区的Calendar对象
        String[] dateParts = txnRvItem.getDate().split("/"); // 分割日期字符串
        int year = Integer.parseInt(dateParts[0]); // 获取年份
        int month = Integer.parseInt(dateParts[1]) - 1; // 获取月份（Calendar月份从0开始）
        int day = Integer.parseInt(dateParts[2]); // 获取日
        calendar.set(year, month, day, 0, 0, 0); // 设置Calendar对象的时间为指定日期的第一个时刻
        calendar.set(Calendar.MILLISECOND, 0); // 将毫秒数设置为0
        milliseconds = calendar.getTimeInMillis(); // 获取时间戳
    }

    private void initTimePickerData()
    {
        String time = txnRvItem.getTime().substring(0, 5);
        String[] timeParts = time.split(":"); // 分割时间字符串
        hour = Integer.parseInt(timeParts[0]); // 获取小时
        minute = Integer.parseInt(timeParts[1]); // 获取分钟
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

    public TxnRvItem getTxnRvItem()
    {
        return txnRvItem;
    }

    public void setTxnRvItem(TxnRvItem txnRvItem)
    {
        this.txnRvItem = txnRvItem;
    }

    public void update(int incomeOrExpense, String amountText, String date, String time, String remark, String acctType, String txnType)
    {
        double amount = incomeOrExpense == 0 ? Double.parseDouble(amountText) : -Double.parseDouble(amountText);
        int acctTypeId = 0;
        int txnTypeId = 0;
        for (AcctType type : Objects.requireNonNull(acctTypeListLiveData.getValue()))
        {
            if (type.getType().equals(acctType))
            {
                acctTypeId = type.getId();
                break;
            }
        }
        for (TxnType type : Objects.requireNonNull(txnTypeListLiveData.getValue()))
        {
            if (type.getType().equals(txnType))
            {
                txnTypeId = type.getId();
                break;
            }
        }
        txnInfoRepository.update(new TxnInfo(txnRvItem.getTxnInfoId(), amount, date, time, remark, acctTypeId, txnTypeId));
    }
}