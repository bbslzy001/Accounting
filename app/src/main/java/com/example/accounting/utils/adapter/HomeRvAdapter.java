package com.example.accounting.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.R;
import com.example.accounting.application.MyApplication;
import com.example.accounting.databinding.RvHeaderItemHomeBinding;
import com.example.accounting.databinding.RvSubItemHomeBinding;
import com.example.accounting.model.entity.HomeRvGroup;
import com.example.accounting.model.entity.HomeRvHeaderItem;
import com.example.accounting.model.entity.HomeRvSubItem;
import com.example.accounting.model.room.bean.HomeRvItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class HomeRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public static final int HEADER_ITEM = 0;  // 列表头
    public static final int SUB_ITEM = 1;  // 列表项
    private List<HomeRvGroup> groupList = new ArrayList<>();  // 所有 Group 所构成的 List
    private List<ItemInfo> itemInfoList = new ArrayList<>();  // 所有 Item 的信息 所构成的 List

    public HomeRvAdapter()
    {
    }

    public List<HomeRvGroup> getGroupList()
    {
        return groupList;
    }

    public int getGroupIndex(int position)
    {
        return itemInfoList.get(position).groupIndex;
    }

    public int getSubItemIndex(int position)
    {
        return itemInfoList.get(position).subItemIndex;
    }

    /**
     * 列表头/悬浮列表头 点击事件：
     * 更改 group 的 isExpanded 属性；
     * RecyclerView 视图局部刷新；
     * 重新计算所有 Item 的信息；
     * 刷新 列表头/悬浮列表头 的视图。
     *
     * @param view     列表头对应的 View
     * @param group    列表头所在的 Group
     * @param position 列表头在所有 Item 中的位置，从 0 开始计数
     */
    public void onHeaderClick(View view, HomeRvGroup group, int position)
    {
        if (group.isExpanded())
        {
            group.setExpanded(false);
            notifyItemRangeRemoved(position + 1, group.getItemCount() - 1);
        }
        else
        {
            group.setExpanded(true);
            notifyItemRangeInserted(position + 1, group.getItemCount() - 1);
        }
        calculateItemInfoList();  // 重新计算所有 Item 的信息
        ImageView imageView = view.findViewById(R.id.header_toggle);
        imageView.setImageResource(group.isExpanded() ? R.drawable.ic_expand_less : R.drawable.ic_expand_more);
    }

    /**
     * 计算所有 Item 的信息，并更新到私有属性 itemInfoList 上
     */
    private void calculateItemInfoList()
    {
        List<ItemInfo> itemInfoList = new ArrayList<>();
        for (int groupIndex = 0; groupIndex < groupList.size(); ++groupIndex)
        {
            // 记录列表头
            itemInfoList.add(new ItemInfo(groupIndex, HEADER_ITEM, -1));

            // 记录对应的列表项
            HomeRvGroup group = groupList.get(groupIndex);
            if (group.isExpanded())
            {
                for (int subItemIndex = 0; subItemIndex < group.getItemCount() - 1; ++subItemIndex)
                    itemInfoList.add(new ItemInfo(groupIndex, SUB_ITEM, subItemIndex));
            }
        }
        this.itemInfoList = itemInfoList;
    }

    public void setItemList(List<HomeRvItem> itemList)
    {
        List<HomeRvGroup> groupList = new ArrayList<>();

        // 将 itemList 按照日期分组
        Map<String, List<HomeRvItem>> map = itemList.stream().collect(Collectors.groupingBy(HomeRvItem::getDate, LinkedHashMap::new, Collectors.toList()));

        // 遍历每个日期分组
        for (String date : map.keySet())
        {
            List<HomeRvItem> subList = map.get(date);
            List<HomeRvSubItem> subItemList = new ArrayList<>();

            // 遍历每个交易信息，将其转换为子项
            for (HomeRvItem item : Objects.requireNonNull(subList))
            {
                HomeRvSubItem subItem = new HomeRvSubItem(item.getTradeType(), item.getTime(), item.getAmount());
                subItemList.add(subItem);
            }

            // 计算该日期的总支出和总收入
            double expenditure = Objects.requireNonNull(subList).stream().filter(item -> item.getAmount() < 0).mapToDouble(HomeRvItem::getAmount).sum();
            double income = Objects.requireNonNull(subList).stream().filter(item -> item.getAmount() > 0).mapToDouble(HomeRvItem::getAmount).sum();

            // 创建日期头部项
            HomeRvHeaderItem headerItem = new HomeRvHeaderItem(date, expenditure, income);

            // 将日期头部项和子项列表构建为分组项，加入 groupList 中
            HomeRvGroup group = new HomeRvGroup(headerItem, subItemList);
            groupList.add(group);
        }

        this.groupList = groupList;

        calculateItemInfoList();

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return itemInfoList.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return itemInfoList.get(position).itemType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());  // 必须设置为 parent，否则匹配不上
        if (viewType == HEADER_ITEM)
        {
            RvHeaderItemHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.rv_header_item_home, parent, false);
            return new HeaderItemViewHolder(binding);
        }
        else
        {
            RvSubItemHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.rv_sub_item_home, parent, false);
            return new SubItemViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position)
    {
        ItemInfo itemInfo = itemInfoList.get(position);  // 获取当前 position 对应的 Item 的信息
        HomeRvGroup group = groupList.get(itemInfo.groupIndex);  // 获取当前 Item 对应的 Group
        if (itemInfo.itemType == HEADER_ITEM)
        {
            HeaderItemViewHolder headerItemViewHolder = (HeaderItemViewHolder) viewHolder;
            headerItemViewHolder.binding.setHomeRvHeaderItem(group.getHeaderItem());
            headerItemViewHolder.binding.executePendingBindings();
        }
        else
        {
            SubItemViewHolder subItemViewHolder = (SubItemViewHolder) viewHolder;
            subItemViewHolder.binding.setHomeRvSubItem(group.getSubItem(itemInfo.subItemIndex));
            subItemViewHolder.binding.executePendingBindings();
        }
    }

    private class HeaderItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private final RvHeaderItemHomeBinding binding;

        public HeaderItemViewHolder(RvHeaderItemHomeBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);  // 整个 HeaderItemViewHolder 的长点击事件绑定
        }

        @Override
        public void onClick(View view)
        {
            int position = getAdapterPosition();  // 获取ViewHolder的位置
            HomeRvGroup group = groupList.get(itemInfoList.get(position).groupIndex);  // 获取对应的 Group
            onHeaderClick(view, group, position);
        }
    }

    private class SubItemViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener
    {
        private final RvSubItemHomeBinding binding;

        public SubItemViewHolder(RvSubItemHomeBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnLongClickListener(this);  // 整个ItemViewHolder的长点击事件绑定
        }

        @Override
        public boolean onLongClick(View view)
        {
            Toast.makeText(MyApplication.getContext(), "当前列表项金额：null", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private static class ItemInfo
    {
        private final int groupIndex;
        private final int itemType;
        private final int subItemIndex;

        /**
         * ItemInfo 的构造函数，用于保存一个 Item 的信息
         *
         * @param groupIndex   当前 Item 属于第几个 Group，从 0 开始计数
         * @param itemType     当前 Item 属于 列表头类型 还是 列表项类型
         * @param subItemIndex 当前 Item 属于 当前 Group 的第几个列表项：列表头值为 -1，列表项从 0 开始计数
         */
        public ItemInfo(int groupIndex, int itemType, int subItemIndex)
        {
            this.groupIndex = groupIndex;
            this.itemType = itemType;
            this.subItemIndex = subItemIndex;
        }
    }
}