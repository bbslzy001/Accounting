package com.example.accounting.utils.adapter;

import static android.graphics.Color.rgb;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.R;
import com.example.accounting.model.room.bean.AccountType;
import com.example.accounting.model.room.bean.HomeRvItem;

import java.util.List;
import java.util.Locale;

public class MyBindingAdapter
{
    @BindingAdapter({"app:homeRvItemList"})
    public static void setHomeRvItemList(RecyclerView recyclerView, LiveData<List<HomeRvItem>> itemList)
    {
        HomeRvAdapter adapter = (HomeRvAdapter) recyclerView.getAdapter();
        if (adapter != null && itemList != null && itemList.getValue() != null)
        {
            adapter.setItemList(itemList.getValue());
        }
    }

    @BindingAdapter({"app:accountRvItemList"})
    public static void setAccountRvItemList(RecyclerView recyclerView, LiveData<List<AccountType>> itemList)
    {
        AccountRvAdapter adapter = (AccountRvAdapter) recyclerView.getAdapter();
        if (adapter != null && itemList != null && itemList.getValue() != null)
        {
            adapter.setItemList(itemList.getValue());
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
            textView.setText(String.format(Locale.CHINA, "- %.2f", Math.abs(amount)));  // 去掉原有的负号，手动绘制
        }
    }

    @BindingAdapter({"app:itemAmount"})
    public static void setItemAmount(TextView textView, double amount)
    {
        textView.setText(String.format(Locale.CHINA, "%.2f", amount));
    }

    @BindingAdapter({"app:headerItemAmount"})
    public static void setHeaderItemAmount(TextView textView, double amount)
    {
        textView.setText(String.format(Locale.CHINA, "%.2f", Math.abs(amount)));  // 去掉原有的负号，手动绘制
    }

    @BindingAdapter({"app:headerItemToggle"})
    public static void setHeaderItemToggle(ImageView imageView, boolean isExpanded)
    {
        if (isExpanded) imageView.setImageResource(R.drawable.ic_expand_less);
        else imageView.setImageResource(R.drawable.ic_expand_more);
    }

    @BindingAdapter({"app:subItemAmount"})
    public static void setSubItemAmount(TextView textView, double amount)
    {
        if (amount >= 0)
        {
            textView.setText(String.format(Locale.CHINA, "+ %.2f", amount));
            textView.setTextColor(rgb(0, 200, 83));
        }
        else
        {
            textView.setText(String.format(Locale.CHINA, "- %.2f", Math.abs(amount)));  // 去掉原有的负号，手动绘制
            textView.setTextColor(rgb(213, 0, 0));
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

    @BindingAdapter({"app:drawerPaddingTop", "app:drawerPaddingBottom"})
    public static void setPaddingBottom(View view, LiveData<Integer> paddingTop, LiveData<Integer> paddingBottom)
    {
        if (paddingTop != null && paddingTop.getValue() != null && paddingBottom != null && paddingBottom.getValue() != null)
        {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + paddingTop.getValue(), view.getPaddingRight(), view.getPaddingBottom() + paddingBottom.getValue());
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