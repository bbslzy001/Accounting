package com.example.accounting.model.room.bean;

import androidx.room.Ignore;

public class CardInfo
{
    // 近一日收支
    private double dayIncome;
    private double dayExpense;
    private double dayAmount;
    // 近三日收支
    private double threeDayIncome;
    private double threeDayExpense;
    private double threeDayAmount;
    // 近七日收支
    private double weekIncome;
    private double weekExpense;
    private double weekAmount;
    // 近一月收支
    private double monthIncome;
    private double monthExpense;
    private double monthAmount;
    // 近三月收支
    private double threeMonthIncome;
    private double threeMonthExpense;
    private double threeMonthAmount;
    // 近六月收支
    private double sixMonthIncome;
    private double sixMonthExpense;
    private double sixMonthAmount;
    // 近一年收支
    private double yearIncome;
    private double yearExpense;
    private double yearAmount;
    // 近三年收支
    private double threeYearIncome;
    private double threeYearExpense;
    private double threeYearAmount;
    // 近五年收支
    private double fiveYearIncome;
    private double fiveYearExpense;
    private double fiveYearAmount;

    @Ignore
    public CardInfo()
    {
    }

    public CardInfo(double dayIncome, double dayExpense, double dayAmount, double threeDayIncome, double threeDayExpense, double threeDayAmount, double weekIncome, double weekExpense, double weekAmount, double monthIncome, double monthExpense, double monthAmount, double threeMonthIncome, double threeMonthExpense, double threeMonthAmount, double sixMonthIncome, double sixMonthExpense, double sixMonthAmount, double yearIncome, double yearExpense, double yearAmount, double threeYearIncome, double threeYearExpense, double threeYearAmount, double fiveYearIncome, double fiveYearExpense, double fiveYearAmount)
    {
        this.dayIncome = dayIncome;
        this.dayExpense = dayExpense;
        this.dayAmount = dayAmount;
        this.threeDayIncome = threeDayIncome;
        this.threeDayExpense = threeDayExpense;
        this.threeDayAmount = threeDayAmount;
        this.weekIncome = weekIncome;
        this.weekExpense = weekExpense;
        this.weekAmount = weekAmount;
        this.monthIncome = monthIncome;
        this.monthExpense = monthExpense;
        this.monthAmount = monthAmount;
        this.threeMonthIncome = threeMonthIncome;
        this.threeMonthExpense = threeMonthExpense;
        this.threeMonthAmount = threeMonthAmount;
        this.sixMonthIncome = sixMonthIncome;
        this.sixMonthExpense = sixMonthExpense;
        this.sixMonthAmount = sixMonthAmount;
        this.yearIncome = yearIncome;
        this.yearExpense = yearExpense;
        this.yearAmount = yearAmount;
        this.threeYearIncome = threeYearIncome;
        this.threeYearExpense = threeYearExpense;
        this.threeYearAmount = threeYearAmount;
        this.fiveYearIncome = fiveYearIncome;
        this.fiveYearExpense = fiveYearExpense;
        this.fiveYearAmount = fiveYearAmount;
    }

    public double getDayIncome()
    {
        return dayIncome;
    }

    public double getDayExpense()
    {
        return dayExpense;
    }

    public double getDayAmount()
    {
        return dayAmount;
    }

    public double getThreeDayIncome()
    {
        return threeDayIncome;
    }

    public double getThreeDayExpense()
    {
        return threeDayExpense;
    }

    public double getThreeDayAmount()
    {
        return threeDayAmount;
    }

    public double getWeekIncome()
    {
        return weekIncome;
    }

    public double getWeekExpense()
    {
        return weekExpense;
    }

    public double getWeekAmount()
    {
        return weekAmount;
    }

    public double getMonthIncome()
    {
        return monthIncome;
    }

    public double getMonthExpense()
    {
        return monthExpense;
    }

    public double getMonthAmount()
    {
        return monthAmount;
    }

    public double getThreeMonthIncome()
    {
        return threeMonthIncome;
    }

    public double getThreeMonthExpense()
    {
        return threeMonthExpense;
    }

    public double getThreeMonthAmount()
    {
        return threeMonthAmount;
    }

    public double getSixMonthIncome()
    {
        return sixMonthIncome;
    }

    public double getSixMonthExpense()
    {
        return sixMonthExpense;
    }

    public double getSixMonthAmount()
    {
        return sixMonthAmount;
    }

    public double getYearIncome()
    {
        return yearIncome;
    }

    public double getYearExpense()
    {
        return yearExpense;
    }

    public double getYearAmount()
    {
        return yearAmount;
    }

    public double getThreeYearIncome()
    {
        return threeYearIncome;
    }

    public double getThreeYearExpense()
    {
        return threeYearExpense;
    }

    public double getThreeYearAmount()
    {
        return threeYearAmount;
    }

    public double getFiveYearIncome()
    {
        return fiveYearIncome;
    }

    public double getFiveYearExpense()
    {
        return fiveYearExpense;
    }

    public double getFiveYearAmount()
    {
        return fiveYearAmount;
    }
}