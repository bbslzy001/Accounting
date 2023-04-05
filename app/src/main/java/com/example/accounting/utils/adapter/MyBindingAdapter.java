package com.example.accounting.utils.adapter;

import static android.graphics.Color.rgb;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.model.room.bean.AccountType;
import com.example.accounting.model.room.bean.HomeRecyclerViewItem;

import java.util.List;
import java.util.Locale;

public class MyBindingAdapter
{
    @BindingAdapter({"app:homeRecyclerViewDataList"})
    public static void setHomeRecyclerViewDataList(RecyclerView recyclerView, LiveData<List<HomeRecyclerViewItem>> dataList)
    {
        HomeRecyclerViewAdapter adapter = (HomeRecyclerViewAdapter) recyclerView.getAdapter();
        if (adapter != null && dataList != null && dataList.getValue() != null)
        {
            adapter.setItemList(dataList.getValue());
        }
    }

    @BindingAdapter({"app:accountRecyclerViewDataList"})
    public static void setAccountRecyclerViewDataList(RecyclerView recyclerView, LiveData<List<AccountType>> dataList)
    {
        AccountRecyclerViewAdapter adapter = (AccountRecyclerViewAdapter) recyclerView.getAdapter();
        if (adapter != null && dataList != null && dataList.getValue() != null)
        {
            adapter.setItemList(dataList.getValue());
        }
    }

    @BindingAdapter({"app:recyclerViewItemLayout"})
    public static void setRecyclerViewItemLayout(RecyclerView recyclerView, int itemLayoutId)
    {
        Object adapter = recyclerView.getAdapter();
        if (adapter != null)
        {
            if (adapter instanceof HomeRecyclerViewAdapter) ((HomeRecyclerViewAdapter) adapter).setItemLayoutId(itemLayoutId);
            else ((AccountRecyclerViewAdapter) adapter).setItemLayoutId(itemLayoutId);
        }
    }

    @BindingAdapter({"app:cardAmount"})
    public static void setCardAmount(TextView textView, double amount)
    {
        if (amount >= 0)
        {
            textView.setText(String.format(Locale.CHINA, "%.2f", amount));
        }
        else
        {
            textView.setText(String.format(Locale.CHINA, "- %.2f", amount));
        }
    }

    @BindingAdapter({"app:amount"})
    public static void setAmount(TextView textView, double amount)
    {
        if (amount >= 0)
        {
            textView.setText(String.format(Locale.CHINA, "+ %.2f", amount));
            textView.setTextColor(rgb(0, 255, 0));
        }
        else
        {
            textView.setText(String.format(Locale.CHINA, "- %.2f", amount));
            textView.setTextColor(rgb(255, 0, 0));
        }
    }

    @BindingAdapter({"app:marginBottom"})
    public static void setMarginBottom(View view, LiveData<Integer> marginBottom)
    {
        if (marginBottom != null && marginBottom.getValue() != null)
        {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            layoutParams.bottomMargin = marginBottom.getValue();
            view.setLayoutParams(layoutParams);
        }
    }

    @BindingAdapter({"app:paddingTop"})
    public static void setPaddingTop(View view, LiveData<Integer> paddingTop)
    {
        if (paddingTop != null && paddingTop.getValue() != null)
        {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + paddingTop.getValue(), view.getPaddingRight(), view.getPaddingBottom());
        }
    }
}