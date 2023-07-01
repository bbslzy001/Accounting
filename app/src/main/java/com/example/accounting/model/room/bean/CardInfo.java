package com.example.accounting.model.room.bean;

public class CardInfo
{
    // 近一日收支
    private double dayIncome;
    private double dayExpense;
    // 近三日收支
    private double threeDayIncome;
    private double threeDayExpense;
    // 近七日收支
    private double weekIncome;
    private double weekExpense;
    // 近一月收支
    private double monthIncome;
    private double monthExpense;
    // 近三月收支
    private double threeMonthIncome;
    private double threeMonthExpense;
    // 近六月收支
    private double sixMonthIncome;
    private double sixMonthExpense;
    // 近一年收支
    private double yearIncome;
    private double yearExpense;
    // 近三年收支
    private double threeYearIncome;
    private double threeYearExpense;
    // 近五年收支
    private double fiveYearIncome;
    private double fiveYearExpense;

    public CardInfo()
    {
    }

    public CardInfo(double dayIncome, double dayExpense, double threeDayIncome, double threeDayExpense, double weekIncome, double weekExpense, double monthIncome, double monthExpense, double threeMonthIncome, double threeMonthExpense, double sixMonthIncome, double sixMonthExpense, double yearIncome, double yearExpense, double threeYearIncome, double threeYearExpense, double fiveYearIncome, double fiveYearExpense)
    {
        this.dayIncome = dayIncome;
        this.dayExpense = dayExpense;
        this.threeDayIncome = threeDayIncome;
        this.threeDayExpense = threeDayExpense;
        this.weekIncome = weekIncome;
        this.weekExpense = weekExpense;
        this.monthIncome = monthIncome;
        this.monthExpense = monthExpense;
        this.threeMonthIncome = threeMonthIncome;
        this.threeMonthExpense = threeMonthExpense;
        this.sixMonthIncome = sixMonthIncome;
        this.sixMonthExpense = sixMonthExpense;
        this.yearIncome = yearIncome;
        this.yearExpense = yearExpense;
        this.threeYearIncome = threeYearIncome;
        this.threeYearExpense = threeYearExpense;
        this.fiveYearIncome = fiveYearIncome;
        this.fiveYearExpense = fiveYearExpense;
    }

    public double getDayIncome()
    {
        return dayIncome;
    }

    public double getDayExpense()
    {
        return dayExpense;
    }

    public double getThreeDayIncome()
    {
        return threeDayIncome;
    }

    public double getThreeDayExpense()
    {
        return threeDayExpense;
    }

    public double getWeekIncome()
    {
        return weekIncome;
    }

    public double getWeekExpense()
    {
        return weekExpense;
    }

    public double getMonthIncome()
    {
        return monthIncome;
    }

    public double getMonthExpense()
    {
        return monthExpense;
    }

    public double getThreeMonthIncome()
    {
        return threeMonthIncome;
    }

    public double getThreeMonthExpense()
    {
        return threeMonthExpense;
    }

    public double getSixMonthIncome()
    {
        return sixMonthIncome;
    }

    public double getSixMonthExpense()
    {
        return sixMonthExpense;
    }

    public double getYearIncome()
    {
        return yearIncome;
    }

    public double getYearExpense()
    {
        return yearExpense;
    }

    public double getThreeYearIncome()
    {
        return threeYearIncome;
    }

    public double getThreeYearExpense()
    {
        return threeYearExpense;
    }

    public double getFiveYearIncome()
    {
        return fiveYearIncome;
    }

    public double getFiveYearExpense()
    {
        return fiveYearExpense;
    }

    public void setDayIncome(double dayIncome)
    {
        this.dayIncome = dayIncome;
    }

    public void setDayExpense(double dayExpense)
    {
        this.dayExpense = dayExpense;
    }

    public void setThreeDayIncome(double threeDayIncome)
    {
        this.threeDayIncome = threeDayIncome;
    }

    public void setThreeDayExpense(double threeDayExpense)
    {
        this.threeDayExpense = threeDayExpense;
    }

    public void setWeekIncome(double weekIncome)
    {
        this.weekIncome = weekIncome;
    }

    public void setWeekExpense(double weekExpense)
    {
        this.weekExpense = weekExpense;
    }

    public void setMonthIncome(double monthIncome)
    {
        this.monthIncome = monthIncome;
    }

    public void setMonthExpense(double monthExpense)
    {
        this.monthExpense = monthExpense;
    }

    public void setThreeMonthIncome(double threeMonthIncome)
    {
        this.threeMonthIncome = threeMonthIncome;
    }

    public void setThreeMonthExpense(double threeMonthExpense)
    {
        this.threeMonthExpense = threeMonthExpense;
    }

    public void setSixMonthIncome(double sixMonthIncome)
    {
        this.sixMonthIncome = sixMonthIncome;
    }

    public void setSixMonthExpense(double sixMonthExpense)
    {
        this.sixMonthExpense = sixMonthExpense;
    }

    public void setYearIncome(double yearIncome)
    {
        this.yearIncome = yearIncome;
    }

    public void setYearExpense(double yearExpense)
    {
        this.yearExpense = yearExpense;
    }

    public void setThreeYearIncome(double threeYearIncome)
    {
        this.threeYearIncome = threeYearIncome;
    }

    public void setThreeYearExpense(double threeYearExpense)
    {
        this.threeYearExpense = threeYearExpense;
    }

    public void setFiveYearIncome(double fiveYearIncome)
    {
        this.fiveYearIncome = fiveYearIncome;
    }

    public void setFiveYearExpense(double fiveYearExpense)
    {
        this.fiveYearExpense = fiveYearExpense;
    }
}