package com.example.accounting.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.R;
import com.example.accounting.base.recyclerview.BaseRvAdapter;
import com.example.accounting.databinding.RvHeaderItemTxnForDayBinding;
import com.example.accounting.databinding.RvSubItemTxnForDayBinding;
import com.example.accounting.model.entity.TxnForDayRvGroup;
import com.example.accounting.model.entity.TxnForDayRvHeaderItem;
import com.example.accounting.model.entity.TxnForDayRvSubItem;
import com.example.accounting.model.room.bean.TxnRvItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TxnForDayRvAdapter extends BaseRvAdapter<TxnForDayRvGroup>
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
    protected HeaderItemViewHolder onCreateHeaderItemViewHolder(LayoutInflater inflater, ViewGroup parent)
    {
        RvHeaderItemTxnForDayBinding binding = DataBindingUtil.inflate(inflater, R.layout.rv_header_item_txn_for_day, parent, false);
        return new HeaderItemViewHolder(binding);
    }

    @Override
    protected SubItemViewHolder onCreateSubItemViewHolder(LayoutInflater inflater, ViewGroup parent)
    {
        RvSubItemTxnForDayBinding binding = DataBindingUtil.inflate(inflater, R.layout.rv_sub_item_txn_for_day, parent, false);
        return new SubItemViewHolder(binding);
    }

    @Override
    protected void onBindHeaderItemViewHolder(RecyclerView.ViewHolder viewHolder, TxnForDayRvGroup group, int position)
    {
        HeaderItemViewHolder headerItemViewHolder = (HeaderItemViewHolder) viewHolder;
        headerItemViewHolder.binding.setGroup(group);
        headerItemViewHolder.binding.setHeaderItem(group.getHeaderItem());
        headerItemViewHolder.binding.executePendingBindings();
    }

    @Override
    protected void onBindSubItemViewHolder(RecyclerView.ViewHolder viewHolder, TxnForDayRvGroup group, int position, BaseRvAdapter.ItemInfo itemInfo)
    {
        SubItemViewHolder subItemViewHolder = (SubItemViewHolder) viewHolder;
        subItemViewHolder.binding.setSubItem(group.getSubItem(itemInfo.getSubItemIndex()));
        subItemViewHolder.binding.executePendingBindings();
    }

    public void setItemList(List<TxnRvItem> itemList)
    {
        List<TxnForDayRvGroup> groupList = new ArrayList<>();

        // 将 itemList 按照日期分组
        Map<String, List<TxnRvItem>> map = itemList.stream().collect(Collectors.groupingBy(TxnRvItem::getDate, LinkedHashMap::new, Collectors.toList()));

        // 遍历每个日期分组
        for (String date : map.keySet())
        {
            List<TxnRvItem> subList = map.get(date);
            List<TxnForDayRvSubItem> subItemList = new ArrayList<>();

            // 遍历每个交易信息，将其转换为子项
            for (TxnRvItem item : Objects.requireNonNull(subList))
            {
                TxnForDayRvSubItem subItem = new TxnForDayRvSubItem(item.getTxnInfoId(), item.getTxnType(), item.getTime(), item.getAmount());
                subItemList.add(subItem);
            }

            // 计算该日期的总支出和总收入
            double expenditure = Objects.requireNonNull(subList).stream().filter(item -> item.getAmount() < 0).mapToDouble(TxnRvItem::getAmount).sum();
            double income = Objects.requireNonNull(subList).stream().filter(item -> item.getAmount() > 0).mapToDouble(TxnRvItem::getAmount).sum();

            // 创建日期头部项
            TxnForDayRvHeaderItem headerItem = new TxnForDayRvHeaderItem(date, expenditure, income);

            // 将日期头部项和子项列表构建为分组项，加入 groupList 中
            TxnForDayRvGroup group = new TxnForDayRvGroup(headerItem, subItemList);
            groupList.add(group);
        }

        setGroupList(groupList);
    }

    private class HeaderItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private final RvHeaderItemTxnForDayBinding binding;

        public HeaderItemViewHolder(RvHeaderItemTxnForDayBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);  // 整个 HeaderItemViewHolder 的点击事件绑定
        }

        @Override
        public void onClick(View view)
        {
            int position = getAdapterPosition();  // 获取ViewHolder的位置
            TxnForDayRvGroup group = groupList.get(itemInfoList.get(position).getGroupIndex());  // 获取对应的 Group
            onHeaderClick(group, position);
        }
    }

    private class SubItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        private final RvSubItemTxnForDayBinding binding;

        public SubItemViewHolder(RvSubItemTxnForDayBinding binding)
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
                onSubItemClickListener.onItemClick(itemInfoList.get(position).getTxnInfoId());
            }
        }

        @Override
        public boolean onLongClick(View view)
        {
            if (onSubItemClickListener != null)
            {
                int position = getAdapterPosition();  // 获取ViewHolder的位置
                onSubItemClickListener.onItemLongClick(itemInfoList.get(position).getTxnInfoId());
                return true;
            }
            return false;
        }
    }
}