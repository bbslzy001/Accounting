package com.example.accounting.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.R;
import com.example.accounting.base.recyclerview.BaseRvItemDecoration;
import com.example.accounting.model.entity.TxnForMonthRvGroup;
import com.example.accounting.utils.adapter.TxnForMonthRvAdapter;

import java.util.Locale;

public class TxnForMonthRvItemDecoration extends BaseRvItemDecoration<TxnForMonthRvAdapter, TxnForMonthRvGroup>
{
    public TxnForMonthRvItemDecoration(Context context, TxnForMonthRvAdapter adapter)
    {
        super(context, adapter);
    }

    @Override
    protected View createHeaderView(RecyclerView recyclerView, TxnForMonthRvGroup group)
    {
        View headerView = LayoutInflater.from(context).inflate(R.layout.rv_header_item_txn_for_month, recyclerView, false);
        TextView dateTextView = headerView.findViewById(R.id.header_date);
        TextView incomeTextView = headerView.findViewById(R.id.header_income);
        TextView expenseTextView = headerView.findViewById(R.id.header_expenditure);
        ImageView imageView = headerView.findViewById(R.id.header_toggle);
        dateTextView.setText(group.getHeaderItem().getDate());
        incomeTextView.setText(String.format(Locale.getDefault(), "%.2f", group.getHeaderItem().getIncome()));
        expenseTextView.setText(String.format(Locale.getDefault(), "%.2f", group.getHeaderItem().getExpenditure()));
        imageView.setImageResource(group.isExpanded() ? R.drawable.ic_expand_less : R.drawable.ic_expand_more);
        return headerView;
    }
}