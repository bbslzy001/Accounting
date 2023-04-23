package com.example.accounting.model.entity;

import java.util.List;

public class TxnRvGroup
{
    private final TxnRvHeaderItem headerItem;
    private final List<TxnRvSubItem> subItemList;
    private boolean isExpanded = false;  // 是否展开

    public TxnRvGroup(TxnRvHeaderItem headerItem, List<TxnRvSubItem> subItemList)
    {
        this.headerItem = headerItem;
        this.subItemList = subItemList;
    }

    public int getItemCount()
    {
        return subItemList.size() + 1; // 分组标题占据一项
    }

    public TxnRvHeaderItem getHeaderItem()
    {
        return headerItem;
    }

    public TxnRvSubItem getSubItem(int position)
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