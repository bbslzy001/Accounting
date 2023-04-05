package com.example.accounting.utils.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.databinding.ItemAccountRecyclerViewBinding;
import com.example.accounting.model.room.bean.AccountType;

import java.util.ArrayList;
import java.util.List;

public class AccountRecyclerViewAdapter extends RecyclerView.Adapter<AccountRecyclerViewAdapter.ViewHolder>
{
    private List<AccountType> itemList = new ArrayList<>();
    private int itemLayoutId;

    public AccountRecyclerViewAdapter()
    {
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItemList(List<AccountType> itemList)
    {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public void setItemLayoutId(int layoutId)
    {
        this.itemLayoutId = layoutId;
    }

    @NonNull
    @Override
    public AccountRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAccountRecyclerViewBinding binding = DataBindingUtil.inflate(inflater, itemLayoutId, parent, false);
        return new AccountRecyclerViewAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(AccountRecyclerViewAdapter.ViewHolder viewHolder, final int position)
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
        private final ItemAccountRecyclerViewBinding binding;

        public ViewHolder(ItemAccountRecyclerViewBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}