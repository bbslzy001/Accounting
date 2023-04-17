package com.example.accounting.model.entity;

import java.util.List;

public class HomeRvGroup
{
    private final HomeRvHeaderItem headerItem;
    private final List<HomeRvSubItem> subItemList;
    private boolean isExpanded = false;  // 是否展开

    public HomeRvGroup(HomeRvHeaderItem headerItem, List<HomeRvSubItem> subItemList)
    {
        this.headerItem = headerItem;
        this.subItemList = subItemList;
    }

    public int getItemCount()
    {
        return subItemList.size() + 1; // 分组标题占据一项
    }

    public HomeRvHeaderItem getHeaderItem()
    {
        return headerItem;
    }

    public HomeRvSubItem getSubItem(int position)
    {
        return subItemList.get(position);
    }

    public boolean isExpanded()
    {
        return isExpanded;
    }

    public void setExpanded(boolean expanded)
    {
        isExpanded = expanded;
    }
}