package com.example.accounting.utils.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.R;
import com.example.accounting.databinding.RvItemAccountBinding;
import com.example.accounting.model.room.bean.AccountType;

import java.util.ArrayList;
import java.util.List;

public class AccountRvAdapter extends RecyclerView.Adapter<AccountRvAdapter.ViewHolder>
{
    private List<AccountType> itemList = new ArrayList<>();

    public AccountRvAdapter()
    {
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItemList(List<AccountType> itemList)
    {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AccountRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RvItemAccountBinding binding = DataBindingUtil.inflate(inflater, R.layout.rv_item_account, parent, false);
        return new AccountRvAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(AccountRvAdapter.ViewHolder viewHolder, final int position)
    {
        viewHolder.binding.setAccountType(itemList.get(position));
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount()
    {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final RvItemAccountBinding binding;

        public ViewHolder(RvItemAccountBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}