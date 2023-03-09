package com.example.accounting.adapter;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;

import com.example.accounting.model.room.bean.AccountType;

import java.util.List;

public class TextViewBindingAdapter
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
}