package com.example.accounting.ui.viewmodel.activity;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.viewpager2.widget.ViewPager2;

import com.example.accounting.R;
import com.example.accounting.base.BaseActivityViewModel;
import com.example.accounting.model.repository.AcctTypeRepository;
import com.example.accounting.model.repository.TxnInfoRepository;
import com.example.accounting.model.repository.TxnTypeRepository;
import com.example.accounting.model.room.bean.AcctType;
import com.example.accounting.model.room.bean.TxnInfo;
import com.example.accounting.model.room.bean.TxnType;

public class MainActViewModel extends BaseActivityViewModel
{
    private final MutableLiveData<Integer> topAppBarTitle = new MutableLiveData<>(R.string.app_name);
    private final MutableLiveData<Integer> statsFragState = new MutableLiveData<>(R.string.list_stats);
    private final AcctTypeRepository acctTypeRepository = new AcctTypeRepository();
    private final TxnTypeRepository txnTypeRepository = new TxnTypeRepository();
    private final TxnInfoRepository txnInfoRepository = new TxnInfoRepository();

    public MainActViewModel()
    {
        super();
    }

    public MutableLiveData<Integer> getTopAppBarTitle()
    {
        return topAppBarTitle;
    }

    public void fakeData()
    {
        txnTypeRepository.insert(new TxnType(0, "吃饭"));
        acctTypeRepository.insert(new AcctType(0, "工商银行储蓄卡", 100.0));
        acctTypeRepository.insert(new AcctType(0, "微信", 100.0));
        acctTypeRepository.insert(new AcctType(0, "支付宝", 100.0));
    }

    public void addTradeInfo()
    {
        txnInfoRepository.insert(new TxnInfo(0, 10, "2022/04/01", "20:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, 10, "2022/04/03", "20:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, 10, "2022/04/11", "20:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, 10, "2022/04/19", "20:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, 10, "2022/04/23", "20:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, 10, "2022/04/24", "20:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, 10, "2022/05/02", "21:55:55", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, 10, "2022/05/22", "21:55:55", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, 10, "2022/05/12", "21:55:55", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, 10, "2022/05/16", "21:55:55", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, 10, "2023/04/03", "22:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, 10, "2023/04/08", "22:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, 10, "2023/04/09", "22:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, 10, "2023/04/01", "22:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, -10, "2020/06/03", "22:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, -10, "2020/06/06", "22:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, -10, "2020/06/07", "22:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, -10, "2020/06/22", "22:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, -10, "2023/01/19", "22:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, -10, "2023/01/01", "22:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, -10, "2023/01/09", "22:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, -10, "2021/08/03", "22:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, -10, "2021/08/13", "22:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, -10, "2021/08/23", "22:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, -10, "2021/08/31", "22:55:00", "nothing", 1, 1));
        txnInfoRepository.insert(new TxnInfo(0, -10, "2021/08/29", "22:55:00", "nothing", 1, 1));
    }

    public void updateStatsFragState()
    {
        Integer state = statsFragState.getValue();
        state = state == null || state == R.string.list_stats ? R.string.cal_stats : R.string.list_stats;
        statsFragState.setValue(state);
    }

    public MutableLiveData<Integer> getStatsFragState()
    {
        return statsFragState;
    }

    public void observeStatsFragState(LifecycleOwner owner, ViewPager2 viewPager)
    {
        statsFragState.observe(owner, s ->
        {
            if (s == R.string.list_stats) viewPager.setCurrentItem(0, false);
            else viewPager.setCurrentItem(1, false);
        });
    }
}