package com.example.accounting.utils;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.Locale;

public class PercentageValueFormatter extends ValueFormatter
{
    @Override
    public String getFormattedValue(float value)
    {
        return String.format(Locale.getDefault(), "%.0f%%", value * 100);
    }
}