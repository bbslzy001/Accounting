package com.example.accounting.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.R;
import com.example.accounting.databinding.RvHeaderAcctBinding;
import com.example.accounting.databinding.RvItemAcctBinding;
import com.example.accounting.model.entity.AcctRvHeader;
import com.example.accounting.model.room.bean.Acct;

import java.util.ArrayList;
import java.util.List;

public class AcctRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public static final int HEADER = 0;  // 头
    public static final int ITEM = 1;  // 项

    private AcctRvHeader header;
    private List<Acct> itemList = new ArrayList<>();

    private AcctRvAdapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener
    {
        void onItemClick(int txnId);
    }

    public void setOnItemClickListener(AcctRvAdapter.OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    public AcctRvAdapter()
    {
    }

    private void calculateHeader()
    {
        double netAmount = 0.0;
        double totalAmount = 0.0;
        double negativeAmount = 0.0;

        for (Acct acct : itemList)
        {
            netAmount += acct.getAmount();
            if (acct.getAmount() > 0)
            {
                totalAmount += acct.getAmount();
            }
            else
            {
                negativeAmount += acct.getAmount();
            }
        }

        this.header = new AcctRvHeader(netAmount, totalAmount, negativeAmount);
    }

    public void setRvData(List<Acct> itemList)
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
            viewHolder.binding.setHeader(header);
            viewHolder.binding.executePendingBindings();
        }
        else
        {
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.binding.setItem(itemList.get(position - 1));  // 去掉头部
            viewHolder.binding.executePendingBindings();
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder
    {
        private final RvHeaderAcctBinding binding;

        public HeaderViewHolder(RvHeaderAcctBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
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
            if (onItemClickListener != null)
            {
                int position = getAdapterPosition();  // 获取ViewHolder的位置
                onItemClickListener.onItemClick(itemList.get(position - 1).getId());
            }
        }
    }
}