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
import com.example.accounting.model.room.bean.AcctType;
import com.example.accounting.model.room.bean.TxnRvItem;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;
import java.util.Locale;

public class MyBindingAdapter
{
    @BindingAdapter({"app:homeRvItemList"})
    public static void setHomeRvItemList(RecyclerView recyclerView, LiveData<List<TxnRvItem>> itemList)
    {
        TxnRvAdapter adapter = (TxnRvAdapter) recyclerView.getAdapter();
        if (adapter != null && itemList != null && itemList.getValue() != null)
        {
            adapter.setItemList(itemList.getValue());
        }
    }

    @BindingAdapter({"app:accountRvItemList"})
    public static void setAccountRvItemList(RecyclerView recyclerView, LiveData<List<AcctType>> itemList)
    {
        AcctRvAdapter adapter = (AcctRvAdapter) recyclerView.getAdapter();
        if (adapter != null && itemList != null && itemList.getValue() != null)
        {
            adapter.setItemList(itemList.getValue());
        }
    }

    @BindingAdapter({"app:cardAmount"})
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

    @BindingAdapter({"app:itemAmount"})
    public static void setItemAmount(TextView textView, double amount)
    {
        textView.setText(String.format(Locale.getDefault(), "%.2f", amount));
    }

    @BindingAdapter({"app:headerItemAmount"})
    public static void setHeaderItemAmount(TextView textView, double amount)
    {
        textView.setText(String.format(Locale.getDefault(), "%.2f", Math.abs(amount)));  // 去掉原有的负号，手动绘制
    }

    @BindingAdapter({"app:headerItemToggle"})
    public static void setHeaderItemToggle(ImageView imageView, boolean isExpanded)
    {
        if (isExpanded) imageView.setImageResource(R.drawable.ic_expand_less);
        else imageView.setImageResource(R.drawable.ic_expand_more);
    }

    @BindingAdapter({"app:subItemAmount"})
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

    @BindingAdapter({"app:currentTitle", "app:statsFragState"})
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

    @BindingAdapter({"app:currentYearAndMonth"})
    public static void setCurrentDate(Button button, LiveData<YearAndMonth> currentYearAndMonth)
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

    @BindingAdapter({"app:monthIncome", "app:monthExpenditure"})
    public static void setMonthAmount(MaterialTextView textView, LiveData<Double> income, LiveData<Double> expenditure)
    {
        if (income != null && income.getValue() != null && expenditure != null && expenditure.getValue() != null)
        {
            textView.setText(String.format(Locale.getDefault(), "收入：%.2f    支出：%.2f", income.getValue(), expenditure.getValue()));
        }
    }

    @BindingAdapter({"app:marginBottom"})
    public static void setMarginBottom(View view, LiveData<Integer> marginBottom)
    {
        if (marginBottom != null && marginBottom.getValue() != null)
        {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            layoutParams.bottomMargin = marginBottom.getValue();
            view.setLayoutParams(layoutParams);
        }
    }

    @BindingAdapter({"app:emptyViewHeight"})
    public static void setEmptyViewHeight(View view, LiveData<Integer> height)
    {
        if (height != null && height.getValue() != null)
        {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = height.getValue();
            view.setLayoutParams(layoutParams);
        }
    }

    @BindingAdapter({"app:drawerPaddingTop", "app:drawerPaddingBottom"})
    public static void setPaddingBottom(View view, LiveData<Integer> paddingTop, LiveData<Integer> paddingBottom)
    {
        if (paddingTop != null && paddingTop.getValue() != null && paddingBottom != null && paddingBottom.getValue() != null)
        {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + paddingTop.getValue(), view.getPaddingRight(), view.getPaddingBottom() + paddingBottom.getValue());
        }
    }

    @BindingAdapter({"app:paddingTop"})
    public static void setPaddingTop(View view, LiveData<Integer> paddingTop)
    {
        if (paddingTop != null && paddingTop.getValue() != null)
        {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + paddingTop.getValue(), view.getPaddingRight(), view.getPaddingBottom());
        }
    }
}