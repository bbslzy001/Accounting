package com.example.accounting.utils.adapter;

import static android.graphics.Color.rgb;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accounting.R;
import com.example.accounting.model.entity.YearAndMonth;
import com.example.accounting.model.room.bean.Acct;
import com.example.accounting.model.room.bean.TxnRvItem;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;
import java.util.Locale;

public class MyBindingAdapter
{
    @BindingAdapter({"txnForDayRvItemList"})
    public static void setTxnForDayRvItemList(RecyclerView recyclerView, LiveData<List<TxnRvItem>> itemList)
    {
        TxnForDayRvAdapter adapter = (TxnForDayRvAdapter) recyclerView.getAdapter();
        if (adapter != null && itemList != null && itemList.getValue() != null)
        {
            adapter.setRvData(itemList.getValue());
        }
    }

    @BindingAdapter({"txnForMonthRvItemList"})
    public static void setTxnForMonthRvItemList(RecyclerView recyclerView, LiveData<List<TxnRvItem>> itemList)
    {
        TxnForMonthRvAdapter adapter = (TxnForMonthRvAdapter) recyclerView.getAdapter();
        if (adapter != null && itemList != null && itemList.getValue() != null)
        {
            adapter.setRvData(itemList.getValue());
        }
    }

    @BindingAdapter({"txnForOneDayCurrentDate", "txnForOneDayRvItemList"})
    public static void setTxnForOneDayRvItemList(RecyclerView recyclerView, String date, LiveData<List<TxnRvItem>> itemList)
    {
        TxnForOneDayRvAdapter adapter = (TxnForOneDayRvAdapter) recyclerView.getAdapter();
        if (adapter != null && itemList != null && itemList.getValue() != null)
        {
            adapter.setRvData(date, itemList.getValue());
        }
    }

    @BindingAdapter({"accountRvItemList"})
    public static void setAccountRvItemList(RecyclerView recyclerView, LiveData<List<Acct>> itemList)
    {
        AcctRvAdapter adapter = (AcctRvAdapter) recyclerView.getAdapter();
        if (adapter != null && itemList != null && itemList.getValue() != null)
        {
            adapter.setRvData(itemList.getValue());
        }
    }

    @BindingAdapter({"cardAmount"})
    public static void setCardAmount(TextView textView, double amount)
    {
        if (amount >= 0)
        {
            textView.setText(String.format(Locale.getDefault(), "%.2f", amount));
        }
        else
        {
            textView.setText(String.format(Locale.getDefault(), "- %.2f", Math.abs(amount)));  // 去掉原有的负号，手动绘制
        }
    }

    @BindingAdapter({"itemAmount"})
    public static void setItemAmount(TextView textView, double amount)
    {
        textView.setText(String.format(Locale.getDefault(), "%.2f", amount));
    }

    @BindingAdapter({"headerItemAmount"})
    public static void setHeaderItemAmount(TextView textView, double amount)
    {
        textView.setText(String.format(Locale.getDefault(), "%.2f", Math.abs(amount)));
    }

    @BindingAdapter({"headerItemToggle"})
    public static void setHeaderItemToggle(ImageView imageView, boolean isExpanded)
    {
        if (isExpanded) imageView.setImageResource(R.drawable.ic_expand_less);
        else imageView.setImageResource(R.drawable.ic_expand_more);
    }

    @BindingAdapter({"subItemAmount"})
    public static void setSubItemAmount(TextView textView, double amount)
    {
        if (amount >= 0)
        {
            textView.setText(String.format(Locale.getDefault(), "+ %.2f", amount));
            textView.setTextColor(rgb(0, 200, 83));
        }
        else
        {
            textView.setText(String.format(Locale.getDefault(), "- %.2f", Math.abs(amount)));  // 去掉原有的负号，手动绘制
            textView.setTextColor(rgb(213, 0, 0));
        }
    }

    @BindingAdapter({"currentTitle", "statsFragState"})
    public static void setCurrentTitle(MaterialToolbar toolbar, LiveData<Integer> titleId, LiveData<Integer> statsFragState)
    {
        if (titleId != null && titleId.getValue() != null)
        {
            int title = titleId.getValue();
            toolbar.setTitle(title);
            toolbar.getMenu().clear();
            if (title == R.string.app_name) toolbar.inflateMenu(R.menu.home_top_bar_menu);
            else if (title == R.string.stats)
            {
                toolbar.inflateMenu(R.menu.stats_top_bar_menu);
                MenuItem menuItem = toolbar.getMenu().findItem(R.id.display);
                if (statsFragState != null && statsFragState.getValue() != null)
                {
                    int state = statsFragState.getValue();
                    if (state == R.string.list_stats) menuItem.setIcon(R.drawable.ic_list);
                    else menuItem.setIcon(R.drawable.ic_cal);
                }
            }
        }
    }

    @BindingAdapter({"currentYearAndMonth"})
    public static void setCurrentYearAndMonth(Button button, LiveData<YearAndMonth> currentYearAndMonth)
    {
        if (currentYearAndMonth != null && currentYearAndMonth.getValue() != null)
        {
            if (button.getVisibility() == View.INVISIBLE) button.setVisibility(View.VISIBLE);
            YearAndMonth yearAndMonth = currentYearAndMonth.getValue();
            String date = String.format(Locale.getDefault(), "%s%s ▼", yearAndMonth.getYear(), yearAndMonth.getMonth());
            button.setText(date);
        }
        else
        {
            button.setVisibility(View.INVISIBLE);
        }
    }

    @BindingAdapter({"monthIncome", "monthExpenditure"})
    public static void setMonthAmount(MaterialTextView textView, LiveData<Double> income, LiveData<Double> expenditure)
    {
        if (income != null && income.getValue() != null && expenditure != null && expenditure.getValue() != null)
        {
            textView.setText(String.format(Locale.getDefault(), "收：%.2f    支：%.2f", income.getValue(), expenditure.getValue()));
        }
    }

    @BindingAdapter({"acctDetailedTitle"})
    public static void setAcctDetailedTitle(MaterialToolbar toolbar, LiveData<Acct> acct)
    {
        if (acct != null && acct.getValue() != null)
        {
            toolbar.setTitle(acct.getValue().getName());
        }
    }

    @BindingAdapter({"incomeOrExpense"})
    public static void setIncomeOrExpense(MaterialButtonToggleGroup group, int incomeOrExpense)
    {
        if (group != null)
        {
            group.check(group.getChildAt(incomeOrExpense).getId());
        }
    }

    @BindingAdapter({"marginBottom"})
    public static void setMarginBottom(View view, LiveData<Integer> marginBottom)
    {
        if (marginBottom != null && marginBottom.getValue() != null)
        {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            layoutParams.bottomMargin = marginBottom.getValue();
            view.setLayoutParams(layoutParams);
        }
    }

    @BindingAdapter({"emptyViewHeight"})
    public static void setEmptyViewHeight(View view, LiveData<Integer> height)
    {
        if (height != null && height.getValue() != null)
        {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = height.getValue();
            view.setLayoutParams(layoutParams);
        }
    }

    @BindingAdapter({"drawerPaddingTop", "drawerPaddingBottom"})
    public static void setPaddingBottom(View view, LiveData<Integer> paddingTop, LiveData<Integer> paddingBottom)
    {
        if (paddingTop != null && paddingTop.getValue() != null && paddingBottom != null && paddingBottom.getValue() != null)
        {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + paddingTop.getValue(), view.getPaddingRight(), view.getPaddingBottom() + paddingBottom.getValue());
        }
    }

    @BindingAdapter({"paddingTop"})
    public static void setPaddingTop(View view, LiveData<Integer> paddingTop)
    {
        if (paddingTop != null && paddingTop.getValue() != null)
        {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + paddingTop.getValue(), view.getPaddingRight(), view.getPaddingBottom());
        }
    }
}