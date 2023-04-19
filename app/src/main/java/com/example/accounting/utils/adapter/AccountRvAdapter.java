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
import com.example.accounting.databinding.RvHeaderAccountBinding;
import com.example.accounting.databinding.RvItemAccountBinding;
import com.example.accounting.model.entity.AccountRvHeader;
import com.example.accounting.model.room.bean.AccountType;

import java.util.ArrayList;
import java.util.List;

public class AccountRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public static final int HEADER = 0;  // 头
    public static final int ITEM = 1;  // 项

    private AccountRvHeader header;
    private List<AccountType> itemList = new ArrayList<>();

    public AccountRvAdapter()
    {
    }

    private void calculateHeader()
    {
        double netAmount = 0.0;
        double totalAmount = 0.0;
        double negativeAmount = 0.0;

        for (AccountType accountType : itemList)
        {
            netAmount += accountType.getAmount();
            if (accountType.getAmount() > 0)
            {
                totalAmount += accountType.getAmount();
            }
            else
            {
                negativeAmount += accountType.getAmount();
            }
        }

        this.header = new AccountRvHeader(netAmount, totalAmount, negativeAmount);
    }

    public void setItemList(List<AccountType> itemList)
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
            RvHeaderAccountBinding binding = DataBindingUtil.inflate(inflater, R.layout.rv_header_account, parent, false);
            return new HeaderViewHolder(binding);
        }
        else
        {
            RvItemAccountBinding binding = DataBindingUtil.inflate(inflater, R.layout.rv_item_account, parent, false);
            return new ItemViewHolder(binding);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position)
    {
        if (position == 0)
        {
            HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
            viewHolder.binding.setAccountRvHeader(header);
            viewHolder.binding.executePendingBindings();
        }
        else
        {
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.binding.setAccountType(itemList.get(position - 1));  // 去掉头部
            viewHolder.binding.executePendingBindings();
        }

    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder
    {
        private final RvHeaderAccountBinding binding;

        public HeaderViewHolder(RvHeaderAccountBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private final RvItemAccountBinding binding;

        public ItemViewHolder(RvItemAccountBinding binding)
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