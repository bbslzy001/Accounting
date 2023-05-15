package com.example.accounting.ui.viewmodel.fragment.statistics;

import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.accounting.base.BaseApplication;
import com.example.accounting.base.BaseFragmentViewModel;
import com.example.accounting.model.repository.TxnRepository;
import com.example.accounting.model.repository.TxnRvItemRepository;
import com.example.accounting.model.room.bean.Txn;
import com.example.accounting.model.room.bean.TxnRvItem;

import org.joda.time.LocalDate;

import java.util.List;

public class CalStatsFragViewModel extends BaseFragmentViewModel
{
    private LiveData<List<TxnRvItem>> itemList;  // 获取实时年月对应的交易记录列表
    private final MutableLiveData<LocalDate> currentDate = new MutableLiveData<>(new LocalDate());
    private final TxnRvItemRepository txnRvItemRepository = new TxnRvItemRepository();
    private final TxnRepository txnRepository = new TxnRepository();

    public CalStatsFragViewModel()
    {
        super();
        initItemList();
    }

    private void initItemList()
    {
        itemList = Transformations.switchMap(currentDate, currentDate ->
        {
            if (currentDate != null)
            {
                return txnRvItemRepository.queryAllByDay(currentDate.toString("yyyy/MM/dd"));
            }
            else
            {
                return txnRvItemRepository.queryAllByDay(new LocalDate().toString("yyyy/MM/dd"));
            }
        });
    }

    public LiveData<List<TxnRvItem>> getItemList()
    {
        return itemList;
    }

    public MutableLiveData<LocalDate> getCurrentDate()
    {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate)
    {
        this.currentDate.setValue(currentDate);
    }

    public void deleteTxn(int id)
    {
        txnRepository.deleteById(id);
    }

    public LiveData<Txn> queryTxn(int id)
    {
        return txnRepository.queryById(id);
    }

    public void insertTxn(Txn txn)
    {
        txnRepository.insert(txn);
    }

    public int getNavigationBarHeight()
    {
        int height = 0;
        Resources resources = BaseApplication.getContext().getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
}