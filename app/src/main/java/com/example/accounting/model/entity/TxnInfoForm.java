package com.example.accounting.model.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class TxnInfoForm
{
    private int incomeOrExpense;
    private String acctType;
    private String txnType;
    private String amountText;
    private String date;
    private String time;
    private String remark;
    private long milliseconds;
    private int hour;
    private int minute;

    public TxnInfoForm(int incomeOrExpense, String acctType, String txnType, String amountText, String date, String time, String remark)
    {
        this.incomeOrExpense = incomeOrExpense;
        this.acctType = acctType;
        this.txnType = txnType;
        this.amountText = amountText;
        this.date = date;
        this.time = time;
        this.remark = remark;

        initDatePickerData();
        initTimePickerData();
    }

    public TxnInfoForm(int incomeOrExpense, String acctType, String txnType, String amountText, Calendar calendar, String remark)
    {
        this.incomeOrExpense = incomeOrExpense;
        this.acctType = acctType;
        this.txnType = txnType;
        this.amountText = amountText;
        this.date = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(calendar.getTime());
        this.time = new SimpleDateFormat("HH:mm",Locale.getDefault()).format(calendar.getTime());
        this.remark = remark;

        initDatePickerData(calendar);
        initTimePickerData(calendar);
    }

    public int getIncomeOrExpense()
    {
        return incomeOrExpense;
    }

    public String getAcctType()
    {
        return acctType;
    }

    public String getTxnType()
    {
        return txnType;
    }

    public String getAmountText()
    {
        return amountText;
    }

    public String getDate()
    {
        return date;
    }

    public String getTime()
    {
        return time;
    }

    public String getRemark()
    {
        return remark;
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

    public void setIncomeOrExpense(int incomeOrExpense)
    {
        this.incomeOrExpense = incomeOrExpense;
    }

    public void setAcctType(String acctType)
    {
        this.acctType = acctType;
    }

    public void setTxnType(String txnType)
    {
        this.txnType = txnType;
    }

    public void setAmountText(String amountText)
    {
        this.amountText = amountText;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public void setMilliseconds(long milliseconds)
    {
        this.milliseconds = milliseconds;
    }

    public void setHour(int hour)
    {
        this.hour = hour;
    }

    public void setMinute(int minute)
    {
        this.minute = minute;
    }

    private void initDatePickerData()
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")); // 创建UTC时区的Calendar对象
        String[] dateParts = date.split("/"); // 分割日期字符串
        int year = Integer.parseInt(dateParts[0]); // 获取年份
        int month = Integer.parseInt(dateParts[1]) - 1; // 获取月份（Calendar月份从0开始）
        int day = Integer.parseInt(dateParts[2]); // 获取日
        calendar.set(year, month, day, 0, 0, 0); // 设置Calendar对象的时间为指定日期的第一个时刻
        calendar.set(Calendar.MILLISECOND, 0); // 将毫秒数设置为0
        milliseconds =  calendar.getTimeInMillis(); // 获取时间戳
    }

    private void initDatePickerData(Calendar calendar)
    {
        milliseconds = calendar.getTimeInMillis();
    }

    private void initTimePickerData()
    {
        String[] timeParts = time.split(":"); // 分割时间字符串
        hour = Integer.parseInt(timeParts[0]); // 获取小时
        minute = Integer.parseInt(timeParts[1]); // 获取分钟
    }

    private void initTimePickerData(Calendar calendar)
    {
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }
}