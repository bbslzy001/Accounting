package com.example.accounting.utils.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardRvAdapter extends RecyclerView.Adapter<CardRvAdapter.ViewHolder>
{
    private List<Item> items;

    public CustomAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CardRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardRvAdapter.ViewHolder holder, int position)
    {
        Item item = items.get(position);

        // Set card width and height based on item properties
        ViewGroup.LayoutParams layoutParams = holder.cardView.getLayoutParams();
        layoutParams.width = item.width;
        layoutParams.height = item.height;
        holder.cardView.setLayoutParams(layoutParams);

        // Set other card properties and data
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        Item item = items.get(position);
        // 根据 item 的属性返回一个整数值，表示该 item 对应的布局类型
        // 在这个例子中，我们假设 item 类有一个名为 layoutType 的整数属性
        return item.layoutType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        public ViewHolder(@NonNull android.view.View itemView)
        {

            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
