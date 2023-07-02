package com.example.accounting.utils.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.R;
import com.example.accounting.databinding.RvItemCard1Binding;
import com.example.accounting.databinding.RvItemCard2Binding;
import com.example.accounting.model.room.bean.CardInfo;
import com.example.accounting.model.room.bean.Chip;

import java.util.ArrayList;
import java.util.List;

public class CardRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public static final int ITEM1 = 1;  // 第一类
    public static final int ITEM2 = 2;  // 第二类

    private List<Chip> chipList = new ArrayList<>();
    private List<Integer> validIndexList = new ArrayList<>();
    private CardInfo cardInfo = new CardInfo();

    public CardRvAdapter()
    {
    }

    public void setRvData(List<Chip> chipList)
    {
        this.chipList = chipList;
        calculateValidIndexList();
        notifyDataSetChanged();
    }

    public void setCardInfo(CardInfo cardInfo)
    {
        this.cardInfo = cardInfo;
        notifyDataSetChanged();
    }

    private void calculateValidIndexList()
    {
        List<Integer> validIndexList = new ArrayList<>();
        for (int i = 0; i < chipList.size(); i++)
        {
            if (chipList.get(i).isState())
            {
                validIndexList.add(i);
            }
        }
        this.validIndexList = validIndexList;
    }

    @Override
    public int getItemCount()
    {
        return validIndexList.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        int actualPosition = validIndexList.get(position);
        if (actualPosition % 3 == 0 || actualPosition > 8) return ITEM1;
        else return ITEM2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == ITEM1)
        {
            RvItemCard1Binding binding = DataBindingUtil.inflate(inflater, R.layout.rv_item_card_1, parent, false);
            return new CardViewHolder1(binding);
        }
        else
        {
            RvItemCard2Binding binding = DataBindingUtil.inflate(inflater, R.layout.rv_item_card_2, parent, false);
            return new CardViewHolder2(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        int actualPosition = validIndexList.get(position);
        if (actualPosition % 3 == 0 || actualPosition > 8)
        {
            CardViewHolder1 viewHolder = (CardViewHolder1) holder;
            switch (actualPosition)
            {
                case 0 ->
                {
                    viewHolder.binding.setTitle("本日收支");
                    viewHolder.binding.setAmount(cardInfo.getDayAmount());
                    viewHolder.binding.setIncome(cardInfo.getDayIncome());
                    viewHolder.binding.setExpense(cardInfo.getDayExpense());
                }
                case 3 ->
                {
                    viewHolder.binding.setTitle("近三日收支");
                    viewHolder.binding.setAmount(cardInfo.getThreeDayAmount());
                    viewHolder.binding.setIncome(cardInfo.getThreeDayIncome());
                    viewHolder.binding.setExpense(cardInfo.getThreeDayExpense());
                }
                case 6 ->
                {
                    viewHolder.binding.setTitle("本周收支");
                    viewHolder.binding.setAmount(cardInfo.getWeekAmount());
                    viewHolder.binding.setIncome(cardInfo.getWeekIncome());
                    viewHolder.binding.setExpense(cardInfo.getWeekExpense());
                }
                case 9 ->
                {
                    viewHolder.binding.setTitle("本月收支");
                    viewHolder.binding.setAmount(cardInfo.getMonthAmount());
                    viewHolder.binding.setIncome(cardInfo.getMonthIncome());
                    viewHolder.binding.setExpense(cardInfo.getMonthExpense());
                }
                case 10 ->
                {
                    viewHolder.binding.setTitle("近三月收支");
                    viewHolder.binding.setAmount(cardInfo.getThreeMonthAmount());
                    viewHolder.binding.setIncome(cardInfo.getThreeMonthIncome());
                    viewHolder.binding.setExpense(cardInfo.getThreeMonthExpense());
                }
                case 11 ->
                {
                    viewHolder.binding.setTitle("近六月收支");
                    viewHolder.binding.setAmount(cardInfo.getSixMonthAmount());
                    viewHolder.binding.setIncome(cardInfo.getSixMonthIncome());
                    viewHolder.binding.setExpense(cardInfo.getSixMonthExpense());
                }
                case 12 ->
                {
                    viewHolder.binding.setTitle("本年收支");
                    viewHolder.binding.setAmount(cardInfo.getYearAmount());
                    viewHolder.binding.setIncome(cardInfo.getYearIncome());
                    viewHolder.binding.setExpense(cardInfo.getYearExpense());
                }
                case 13 ->
                {
                    viewHolder.binding.setTitle("近三年收支");
                    viewHolder.binding.setAmount(cardInfo.getThreeYearAmount());
                    viewHolder.binding.setIncome(cardInfo.getThreeYearIncome());
                    viewHolder.binding.setExpense(cardInfo.getThreeYearExpense());
                }
                case 14 ->
                {
                    viewHolder.binding.setTitle("近五年收支");
                    viewHolder.binding.setAmount(cardInfo.getFiveYearAmount());
                    viewHolder.binding.setIncome(cardInfo.getFiveYearIncome());
                    viewHolder.binding.setExpense(cardInfo.getFiveYearExpense());
                }
            }
            viewHolder.binding.executePendingBindings();
        }
        else
        {
            CardViewHolder2 viewHolder = (CardViewHolder2) holder;
            switch (actualPosition)
            {
                case 1 ->
                {
                    viewHolder.binding.setTitle("本日收入");
                    viewHolder.binding.setAmount(cardInfo.getDayIncome());
                }
                case 2 ->
                {
                    viewHolder.binding.setTitle("本日支出");
                    viewHolder.binding.setAmount(cardInfo.getDayExpense());
                }
                case 4 ->
                {
                    viewHolder.binding.setTitle("近三日收入");
                    viewHolder.binding.setAmount(cardInfo.getThreeDayIncome());
                }
                case 5 ->
                {
                    viewHolder.binding.setTitle("近三日支出");
                    viewHolder.binding.setAmount(cardInfo.getThreeDayExpense());
                }
                case 7 ->
                {
                    viewHolder.binding.setTitle("本周收入");
                    viewHolder.binding.setAmount(cardInfo.getWeekIncome());
                }
                case 8 ->
                {
                    viewHolder.binding.setTitle("本周支出");
                    viewHolder.binding.setAmount(cardInfo.getWeekExpense());
                }
            }
            viewHolder.binding.executePendingBindings();
        }
    }

    private static class CardViewHolder1 extends RecyclerView.ViewHolder
    {
        private final RvItemCard1Binding binding;

        public CardViewHolder1(RvItemCard1Binding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private static class CardViewHolder2 extends RecyclerView.ViewHolder
    {
        private final RvItemCard2Binding binding;

        public CardViewHolder2(RvItemCard2Binding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
