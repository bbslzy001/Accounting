package com.example.accounting.utils.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.databinding.RecyclerViewGroupTitleHomeBinding;
import com.example.accounting.databinding.RecyclerViewItemHomeBinding;
import com.example.accounting.model.entity.HomeRecyclerViewGroupTitle;
import com.example.accounting.model.room.bean.HomeRecyclerViewItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private final Map<String, HomeRecyclerViewGroupTitle> dateMap = new HashMap<>();
    private final List<Object> dataList = new ArrayList<>();
    private int[] itemLayoutIdArray;

    public HomeRecyclerViewAdapter()
    {
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItemList(List<HomeRecyclerViewItem> itemList)
    {
        /* 清除上一次的数据 */
        dateMap.clear();
        dataList.clear();

        /* 初始化 dataList，主要用于添加标题行 */
        for (HomeRecyclerViewItem item : itemList)
        {
            String date = item.getDate();
            double amount = item.getAmount();
            if (!dateMap.containsKey(date))  // 添加空标题行
            {
                dateMap.put(date, new HomeRecyclerViewGroupTitle(date, 0.0, 0.0));
                dataList.add(new HomeRecyclerViewGroupTitle(date, 0.0, 0.0));
            }
            dataList.add(item);  // 添加具体数据
            HomeRecyclerViewGroupTitle title = dateMap.get(date);
            if (amount < 0)  // 为标题行属性赋值，暂时存到 Map 中
            {
                Objects.requireNonNull(title).setExpenditure(title.getExpenditure() + amount);
            }
            else
            {
                Objects.requireNonNull(title).setIncome(title.getIncome() + amount);
            }
        }

        /* 为标题行的属性赋值 */
        for (Object item : dataList)
        {
            if (item instanceof HomeRecyclerViewGroupTitle title)
            {
                HomeRecyclerViewGroupTitle titleFromMap = dateMap.get(title.getDate());
                title.setExpenditure(Objects.requireNonNull(titleFromMap).getExpenditure());
                title.setIncome(Objects.requireNonNull(titleFromMap).getIncome());
            }
        }

        notifyDataSetChanged();
    }

    public void setItemLayoutIdArray(int[] layoutIdArray)
    {
        this.itemLayoutIdArray = layoutIdArray;
    }

    @Override
    public int getItemViewType(int position)
    {
        Object obj = dataList.get(position);
        if(obj instanceof HomeRecyclerViewGroupTitle) return itemLayoutIdArray[0];
        else return itemLayoutIdArray[1];
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == itemLayoutIdArray[0])
        {
            RecyclerViewGroupTitleHomeBinding binding = DataBindingUtil.inflate(inflater, itemLayoutIdArray[0], parent, false);
            return new GroupTitleViewHolder(binding);
        }
        else
        {
            RecyclerViewItemHomeBinding binding = DataBindingUtil.inflate(inflater, itemLayoutIdArray[1], parent, false);
            return new ItemViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position)
    {
        if (viewHolder instanceof GroupTitleViewHolder)
        {
            GroupTitleViewHolder holder = (GroupTitleViewHolder) viewHolder;
            holder.binding.setHomeRecyclerViewGroupTitle((HomeRecyclerViewGroupTitle) dataList.get(position));
            holder.binding.executePendingBindings();
        }
        else
        {
            ItemViewHolder holder = (ItemViewHolder) viewHolder;
            holder.binding.setHomeRecyclerViewItems((HomeRecyclerViewItem) dataList.get(position));
            holder.binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }

    public static class GroupTitleViewHolder extends RecyclerView.ViewHolder
    {
        private final RecyclerViewGroupTitleHomeBinding binding;

        public GroupTitleViewHolder(RecyclerViewGroupTitleHomeBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder
    {
        private final RecyclerViewItemHomeBinding binding;

        public ItemViewHolder(RecyclerViewItemHomeBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}