package com.example.accounting.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.R;
import com.example.accounting.application.MyApplication;
import com.example.accounting.databinding.RvHeaderAcctBinding;
import com.example.accounting.databinding.RvItemAcctBinding;
import com.example.accounting.model.entity.AcctRvHeader;
import com.example.accounting.model.room.bean.AcctType;

import java.util.ArrayList;
import java.util.List;

public class AcctRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public static final int HEADER = 0;  // 头
    public static final int ITEM = 1;  // 项

    private AcctRvHeader header;
    private List<AcctType> itemList = new ArrayList<>();

    public AcctRvAdapter()
    {
    }

    private void calculateHeader()
    {
        double netAmount = 0.0;
        double totalAmount = 0.0;
        double negativeAmount = 0.0;

        for (AcctType acctType : itemList)
        {
            netAmount += acctType.getAmount();
            if (acctType.getAmount() > 0)
            {
                totalAmount += acctType.getAmount();
            }
            else
            {
                negativeAmount += acctType.getAmount();
            }
        }

        this.header = new AcctRvHeader(netAmount, totalAmount, negativeAmount);
    }

    public void setItemList(List<AcctType> itemList)
    {
        this.itemList = itemList;

        calculateHeader();

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return itemList.size() + 1;  // 添加 Header
    }

    @Override
    public int getItemViewType(int position)
    {
        if (position == 0) return HEADER;
        else return ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == HEADER)
        {
            RvHeaderAcctBinding binding = DataBindingUtil.inflate(inflater, R.layout.rv_header_acct, parent, false);
            return new HeaderViewHolder(binding);
        }
        else
        {
            RvItemAcctBinding binding = DataBindingUtil.inflate(inflater, R.layout.rv_item_acct, parent, false);
            return new ItemViewHolder(binding);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position)
    {
        if (position == 0)
        {
            HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
            viewHolder.binding.setAcctRvHeader(header);
            viewHolder.binding.executePendingBindings();
        }
        else
        {
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.binding.setAcctType(itemList.get(position - 1));  // 去掉头部
            viewHolder.binding.executePendingBindings();
        }

    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder
    {
        private final RvHeaderAcctBinding binding;

        public HeaderViewHolder(RvHeaderAcctBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private final RvItemAcctBinding binding;

        public ItemViewHolder(RvItemAcctBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);  // 整个 ItemViewHolder 的点击事件绑定
        }

        @Override
        public void onClick(View view)
        {
            Toast.makeText(MyApplication.getContext(), "点击了Item", Toast.LENGTH_SHORT).show();
        }
    }
}