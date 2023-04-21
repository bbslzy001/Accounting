package com.example.accounting.base;

import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseActivityViewModel extends ViewModel
{
    private final MutableLiveData<Integer> statusHeight = new MutableLiveData<>();
    private final MutableLiveData<Integer> navigationHeight = new MutableLiveData<>();

    public BaseActivityViewModel()
    {
        statusHeight.setValue(DisplayUtils.getStatusBarHeight());
        navigationHeight.setValue(DisplayUtils.getNavigationBarHeight());
    }

    public LiveData<Integer> getStatusHeight()
    {
        return statusHeight;
    }

    public LiveData<Integer> getNavigationHeight()
    {
        return navigationHeight;
    }

    private static class DisplayUtils
    {
        /**
         * Get the top status bar height of the phone
         */
        public static int getStatusBarHeight()
        {
            int height = 0;
            Resources resources = BaseApplication.getContext().getResources();
            int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) height = resources.getDimensionPixelSize(resourceId);
            return height;
        }

        /**
         * Get the bottom navigation bar height of the phone
         */
        public static int getNavigationBarHeight()
        {
            int height = 0;
            Resources resources = BaseApplication.getContext().getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) height = resources.getDimensionPixelSize(resourceId);
            return height;
        }
    }
}