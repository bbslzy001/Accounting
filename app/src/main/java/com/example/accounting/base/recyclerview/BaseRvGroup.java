package com.example.accounting.base.recyclerview;

import java.util.List;

public class BaseRvGroup<H extends BaseRvHeaderItem, S extends BaseRvSubItem>
{
    private final H headerItem;
    private final List<S> subItemList;
    private boolean isExpanded = false;

    public BaseRvGroup(H headerItem, List<S> subItemList)
    {
        this.headerItem = headerItem;
        this.subItemList = subItemList;
    }

    public int getItemCount()
    {
        return subItemList.size() + 1;
    }

    public H getHeaderItem()
    {
        return headerItem;
    }

    public S getSubItem(int position)
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