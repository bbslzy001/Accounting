package com.example.accounting.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.R;
import com.example.accounting.databinding.RvHeaderTxnForOneDayBinding;
import com.example.accounting.databinding.RvItemTxnForOneDayBinding;
import com.example.accounting.model.entity.TxnForOneDayRvHeader;
import com.example.accounting.model.room.bean.TxnRvItem;

import java.util.ArrayList;
import java.util.List;

public class TxnForOneDayRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public static final int HEADER = 0;  // 头
    public static final int ITEM = 1;  // 项

    private TxnForOneDayRvHeader header;
    private List<TxnRvItem> itemList = new ArrayList<>();

    private TxnForOneDayRvAdapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener
    {
        void onItemClick(int txnInfoId);

        void onItemLongClick(int txnInfoId);
    }

    public void setOnItemClickListener(TxnForOneDayRvAdapter.OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    public TxnForOneDayRvAdapter()
    {
    }

    private void calculateHeader(String date)
    {
        double income = 0.0;
        double expense = 0.0;

        for (TxnRvItem txnRvItem : itemList)
        {
            if (txnRvItem.getAmount() > 0) income += txnRvItem.getAmount();
            else expense += txnRvItem.getAmount();
        }

        this.header = new TxnForOneDayRvHeader(date, income, expense);
    }

    public void setRvData(String date, List<TxnRvItem> itemList)
    {
        this.itemList = itemList;

        calculateHeader(date);

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
            RvHeaderTxnForOneDayBinding binding = DataBindingUtil.inflate(inflater, R.layout.rv_header_txn_for_one_day, parent, false);
            return new HeaderViewHolder(binding);
        }
        else
        {
            RvItemTxnForOneDayBinding binding = DataBindingUtil.inflate(inflater, R.layout.rv_item_txn_for_one_day, parent, false);
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
        private final RvHeaderTxnForOneDayBinding binding;

        public HeaderViewHolder(RvHeaderTxnForOneDayBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        private final RvItemTxnForOneDayBinding binding;

        public ItemViewHolder(RvItemTxnForOneDayBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);  // 整个 ItemViewHolder 的点击事件绑定
            this.binding.getRoot().setOnLongClickListener(this);  // 整个 ItemViewHolder 的长按点击事件绑定
        }

        @Override
        public void onClick(View view)
        {
            if (onItemClickListener != null)
            {
                int position = getAdapterPosition();  // 获取ViewHolder的位置
                onItemClickListener.onItemClick(itemList.get(position - 1).getTxnInfoId());
            }
        }

        @Override
        public boolean onLongClick(View v)
        {
            if (onItemClickListener != null)
            {
                int position = getAdapterPosition();  // 获取ViewHolder的位置
                onItemClickListener.onItemLongClick(itemList.get(position).getTxnInfoId());
                return true;
            }
            return false;
        }
    }
}