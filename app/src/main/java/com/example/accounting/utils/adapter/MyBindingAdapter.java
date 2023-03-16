package com.example.accounting.utils.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;

import com.example.accounting.model.room.bean.AccountType;

import java.util.List;

public class MyBindingAdapter
{
    @BindingAdapter("app:accountTypes")
    public static void setStudents(TextView textView, LiveData<List<AccountType>> accountTypes)
    {
        if (accountTypes != null && accountTypes.getValue() != null)
        {
            StringBuilder sb = new StringBuilder();
            for (AccountType accountType : accountTypes.getValue())
            {
                sb.append("Id: ").append(accountType.getId()).append("  Type: ").append(accountType.getType()).append("\n");
            }
            textView.setText(sb.toString());
        }
    }

    @BindingAdapter("app:marginBottom")
    public static void setMarginBottom(View view, LiveData<Integer> marginBottom)
    {
        if (marginBottom != null && marginBottom.getValue() != null)
        {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            layoutParams.bottomMargin = marginBottom.getValue();
            view.setLayoutParams(layoutParams);
        }
    }

    @BindingAdapter("app:paddingTop")
    public static void setPaddingTop(View view, LiveData<Integer> paddingTop)
    {
        if (paddingTop != null && paddingTop.getValue() != null)
        {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + paddingTop.getValue(), view.getPaddingRight(), view.getPaddingBottom());
        }
    }
}