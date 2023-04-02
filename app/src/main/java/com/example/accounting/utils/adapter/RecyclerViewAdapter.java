package com.example.accounting.utils.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.databinding.ItemHomeRecyclerViewBinding;
import com.example.accounting.model.room.bean.HomeRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private List<HomeRecyclerViewItem> itemList = new ArrayList<>();
    private int itemLayoutId;

    public RecyclerViewAdapter()
    {
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItemList(List<HomeRecyclerViewItem> itemList)
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemHomeRecyclerViewBinding binding = DataBindingUtil.inflate(inflater, itemLayoutId, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position)
    {
        viewHolder.binding.setHomeRecyclerViewItems(itemList.get(position));
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount()
    {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final ItemHomeRecyclerViewBinding binding;

        public ViewHolder(ItemHomeRecyclerViewBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}