package com.example.accounting.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.accounting.application.MyApplication;

public class DisplayUtils
{
    /**
     * 将px转换为dp
     */
    public static int px2dp(float pxValue)
    {
        Resources resources = Resources.getSystem();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        float density = displayMetrics.density;
        return (int) (pxValue / density + 0.5f);
    }

    /**
     * 将dp转换为px
     */
    public static int dp2px(float dpValue)
    {
        Resources resources = Resources.getSystem();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        float density = displayMetrics.density;
        return (int) (dpValue * density + 0.5f);
    }

    /**
     * 将px转换为sp
     */
    public static int px2sp(float pxValue)
    {
        Resources resources = Resources.getSystem();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        float scaledDensity = displayMetrics.scaledDensity;
        return (int) (pxValue / scaledDensity + 0.5f);
    }

    /**
     * 将sp转换为px
     */
    public static int sp2px(float spValue)
    {
        Resources resources = Resources.getSystem();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        float scaledDensity = displayMetrics.scaledDensity;
        return (int) (spValue * scaledDensity + 0.5f);
    }

    /**
     * 获取屏幕宽度
     */
    public static int getWindowWidth()
    {
        Resources resources = Resources.getSystem();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     */
    public static int getWindowHeight()
    {
        Resources resources = Resources.getSystem();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    /**
     * 获取手机顶部状态栏高度
     */
    public static int getStatusBarHeight()
    {
        int height = 0;
        Resources resources = MyApplication.getContext().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * 获取手机底部导航栏高度
     */
    public static int getNavigationBarHeight()
    {
        int height = 0;
        Resources resources = MyApplication.getContext().getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * 检测是否存在底部导航栏
     */
    public static boolean hasNavigationBar()
    {
        Resources resources = MyApplication.getContext().getResources();
        int resourceId = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        return resourceId > 0 && resources.getBoolean (resourceId);
    }
}