package com.example.accounting.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.R;
import com.example.accounting.base.recyclerview.BaseRvAdapter;
import com.example.accounting.databinding.RvHeaderItemTxnBinding;
import com.example.accounting.databinding.RvSubItemTxnBinding;
import com.example.accounting.model.entity.TxnRvGroup;
import com.example.accounting.model.entity.TxnRvHeaderItem;
import com.example.accounting.model.entity.TxnRvSubItem;
import com.example.accounting.model.room.bean.TxnRvItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TxnForDayRvAdapter extends BaseRvAdapter<TxnRvGroup>
{
    private OnSubItemClickListener onSubItemClickListener;

    public interface OnSubItemClickListener
    {
        void onItemClick(int txnInfoId);
        void onItemLongClick(int txnInfoId);
    }

    public void setOnSubItemClickListener(OnSubItemClickListener onSubItemClickListener)
    {
        this.onSubItemClickListener = onSubItemClickListener;
    }

    @Override
    protected HeaderItemViewHolder onCreateHeaderViewHolder(LayoutInflater inflater, ViewGroup parent)
    {
        RvHeaderItemTxnBinding binding = DataBindingUtil.inflate(inflater, R.layout.rv_header_item_txn, parent, false);
        return new HeaderItemViewHolder(binding);
    }

    @Override
    protected SubItemViewHolder onCreateSubItemViewHolder(LayoutInflater inflater, ViewGroup parent)
    {
        RvSubItemTxnBinding binding = DataBindingUtil.inflate(inflater, R.layout.rv_sub_item_txn, parent, false);
        return new SubItemViewHolder(binding);
    }

    @Override
    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, TxnRvGroup group, int position)
    {
        HeaderItemViewHolder headerItemViewHolder = (HeaderItemViewHolder) viewHolder;
        headerItemViewHolder.binding.setBaseRvGroup(group);
        headerItemViewHolder.binding.setTxnRvHeaderItem(group.getHeaderItem());
        headerItemViewHolder.binding.executePendingBindings();
    }

    @Override
    protected void onBindSubItemViewHolder(RecyclerView.ViewHolder viewHolder, TxnRvGroup group, int position, BaseRvAdapter.ItemInfo itemInfo)
    {
        SubItemViewHolder subItemViewHolder = (SubItemViewHolder) viewHolder;
        subItemViewHolder.binding.setTxnRvSubItem(group.getSubItem(itemInfo.subItemIndex()));
        subItemViewHolder.binding.executePendingBindings();
    }

    public void setItemList(List<TxnRvItem> itemList)
    {
        List<TxnRvGroup> groupList = new ArrayList<>();

        // 将 itemList 按照日期分组
        Map<String, List<TxnRvItem>> map = itemList.stream().collect(Collectors.groupingBy(TxnRvItem::getDate, LinkedHashMap::new, Collectors.toList()));

        // 遍历每个日期分组
        for (String date : map.keySet())
        {
            List<TxnRvItem> subList = map.get(date);
            List<TxnRvSubItem> subItemList = new ArrayList<>();

            // 遍历每个交易信息，将其转换为子项
            for (TxnRvItem item : Objects.requireNonNull(subList))
            {
                TxnRvSubItem subItem = new TxnRvSubItem(item.getTxnInfoId(), item.getTxnType(), item.getTime(), item.getAmount());
                subItemList.add(subItem);
            }

            // 计算该日期的总支出和总收入
            double expenditure = Objects.requireNonNull(subList).stream().filter(item -> item.getAmount() < 0).mapToDouble(TxnRvItem::getAmount).sum();
            double income = Objects.requireNonNull(subList).stream().filter(item -> item.getAmount() > 0).mapToDouble(TxnRvItem::getAmount).sum();

            // 创建日期头部项
            TxnRvHeaderItem headerItem = new TxnRvHeaderItem(date, expenditure, income);

            // 将日期头部项和子项列表构建为分组项，加入 groupList 中
            TxnRvGroup group = new TxnRvGroup(headerItem, subItemList);
            groupList.add(group);
        }

        setGroupList(groupList);
    }

    private class HeaderItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private final RvHeaderItemTxnBinding binding;

        public HeaderItemViewHolder(RvHeaderItemTxnBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);  // 整个 HeaderItemViewHolder 的点击事件绑定
        }

        @Override
        public void onClick(View view)
        {
            int position = getAdapterPosition();  // 获取ViewHolder的位置
            TxnRvGroup group = groupList.get(itemInfoList.get(position).groupIndex());  // 获取对应的 Group
            onHeaderClick(group, position);
        }
    }

    private class SubItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        private final RvSubItemTxnBinding binding;

        public SubItemViewHolder(RvSubItemTxnBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);  // 整个 SubItemViewHolder 的点击事件绑定
            this.binding.getRoot().setOnLongClickListener(this);  // 整个 SubItemViewHolder 的长按点击事件绑定
        }

        @Override
        public void onClick(View view)
        {
            if (onSubItemClickListener != null)
            {
                int position = getAdapterPosition();  // 获取ViewHolder的位置
                onSubItemClickListener.onItemClick(itemInfoList.get(position).txnInfoId());
            }
        }

        @Override
        public boolean onLongClick(View view)
        {
            if (onSubItemClickListener != null)
            {
                int position = getAdapterPosition();  // 获取ViewHolder的位置
                onSubItemClickListener.onItemLongClick(itemInfoList.get(position).txnInfoId());
                return true;
            }
            return false;
        }
    }
}