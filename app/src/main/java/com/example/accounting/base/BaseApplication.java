package com.example.accounting.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class BaseApplication extends Application
{
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
    }

    public boolean isFirstTimeLaunch()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        return sharedPreferences.getBoolean("is_first_time_launch", true);
    }

    public void setFirstTimeLaunch(boolean isFirstTime)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("is_first_time_launch", isFirstTime).apply();
    }

    public static Context getContext()
    {
        return context;
    }
}