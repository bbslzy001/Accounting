package com.example.accounting.utils.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.model.room.bean.HomeRecyclerViewItem;

import java.util.List;

public class MyBindingAdapter
{
    @BindingAdapter({"app:dataList"})
    public static void setDataList(RecyclerView recyclerView, LiveData<List<HomeRecyclerViewItem>> dataList)
    {
        RecyclerViewAdapter adapter = (RecyclerViewAdapter) recyclerView.getAdapter();
        if (adapter != null && dataList != null && dataList.getValue() != null)
        {
            adapter.setDataList(dataList.getValue());
        }
    }

    @BindingAdapter({"app:itemLayout"})
    public static void setItemLayout(RecyclerView recyclerView, int itemLayoutId)
    {
        RecyclerViewAdapter adapter = (RecyclerViewAdapter) recyclerView.getAdapter();
        if (adapter != null)
        {
            adapter.setItemLayoutId(itemLayoutId);
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