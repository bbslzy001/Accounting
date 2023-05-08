package com.example.accounting.base.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRvAdapter<G extends BaseRvGroup> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public static final int HEADER_ITEM = 0;  // 列表头
    public static final int SUB_ITEM = 1;  // 列表项
    protected List<ItemInfo> itemInfoList = new ArrayList<>(); // 所有 Item 的信息所构成的 List
    protected List<G> groupList = new ArrayList<>(); // 所有 Group 所构成的 List

    public BaseRvAdapter()
    {
    }

    public List<G> getGroupList()
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
     * @param group    列表头所在的 Group
     * @param position 列表头在所有 Item 中的位置，从 0 开始计数
     */
    public void onHeaderClick(G group, int position)
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
        notifyItemChanged(position); // 更新列表头的图标显示
        calculateItemInfoList(); // 重新计算所有 Item 的信息
    }

    /**
     * 计算所有 Item 的信息，并更新到私有属性 itemInfoList 上
     */
    protected void calculateItemInfoList()
    {
        List<ItemInfo> itemInfoList = new ArrayList<>();
        for (int groupIndex = 0; groupIndex < groupList.size(); ++groupIndex)
        {
            // 记录列表头
            itemInfoList.add(new ItemInfo(groupIndex, HEADER_ITEM, -1, -1));

            // 记录对应的列表项
            G group = groupList.get(groupIndex);
            if (group.isExpanded())
            {
                for (int subItemIndex = 0; subItemIndex < group.getItemCount() - 1; ++subItemIndex)
                {
                    itemInfoList.add(new ItemInfo(groupIndex, SUB_ITEM, subItemIndex, group.getSubItem(subItemIndex).getSubItemId()));
                }
            }
        }
        this.itemInfoList = itemInfoList;
    }

    protected void setGroupList(List<G> groupList)
    {
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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext()); // 必须设置为 parent，否则匹配不上
        if (viewType == HEADER_ITEM)
        {
            return onCreateHeaderViewHolder(inflater, parent);
        }
        else
        {
            return onCreateSubItemViewHolder(inflater, parent);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position)
    {
        ItemInfo itemInfo = itemInfoList.get(position); // 获取当前 position 对应的 Item 的信息
        G group = groupList.get(itemInfo.groupIndex); // 获取当前 Item 对应的 Group
        if (itemInfo.itemType == HEADER_ITEM)
        {
            onBindHeaderViewHolder(viewHolder, group, position);
        }
        else
        {
            onBindSubItemViewHolder(viewHolder, group, position, itemInfo);
        }
    }

    protected abstract RecyclerView.ViewHolder onCreateHeaderViewHolder(LayoutInflater inflater, ViewGroup parent);

    protected abstract RecyclerView.ViewHolder onCreateSubItemViewHolder(LayoutInflater inflater, ViewGroup parent);

    protected abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, G group, int position);

    protected abstract void onBindSubItemViewHolder(RecyclerView.ViewHolder viewHolder, G group, int position, ItemInfo itemInfo);

    /**
     * @param groupIndex   Group 在 groupList 中的下标
     * @param itemType     HEADER_ITEM 或 SUB_ITEM
     * @param subItemIndex 如果是列表项，表示在当前 Group 中的下标
     * @param txnInfoId    如果是列表项，表示对应的 txnInfoId
     */
    protected record ItemInfo(int groupIndex, int itemType, int subItemIndex, int txnInfoId)
    {
    }
}
