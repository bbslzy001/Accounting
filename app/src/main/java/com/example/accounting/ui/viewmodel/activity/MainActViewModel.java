package com.example.accounting.ui.viewmodel.activity;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.viewpager2.widget.ViewPager2;

import com.example.accounting.R;
import com.example.accounting.base.BaseActivityViewModel;
import com.example.accounting.model.repository.AcctRepository;
import com.example.accounting.model.repository.ChipRepository;
import com.example.accounting.model.repository.TxnRepository;
import com.example.accounting.model.repository.TxnTypeRepository;
import com.example.accounting.model.room.bean.Acct;
import com.example.accounting.model.room.bean.Chip;
import com.example.accounting.model.room.bean.Txn;
import com.example.accounting.model.room.bean.TxnType;

public class MainActViewModel extends BaseActivityViewModel
{
    private final MutableLiveData<Integer> topAppBarTitle = new MutableLiveData<>(R.string.app_name);
    private final MutableLiveData<Integer> statsFragState = new MutableLiveData<>(R.string.list_stats);
    private final TxnTypeRepository txnTypeRepository = new TxnTypeRepository();
    private final ChipRepository chipRepository = new ChipRepository();
    private final TxnRepository txnRepository = new TxnRepository();
    private final AcctRepository acctRepository = new AcctRepository();

    public MainActViewModel()
    {
        super();
    }

    public MutableLiveData<Integer> getTopAppBarTitle()
    {
        return topAppBarTitle;
    }

    public void initData()
    {
        String[] txnTypeNames = {"餐饮", "交通", "购物", "娱乐", "工资", "奖金", "投资", "其他"};
        for (String name : txnTypeNames)
        {
            txnTypeRepository.insert(new TxnType(0, name));
        }

        for (int i = 0; i < 15; ++i)
        {
            chipRepository.insert(new Chip(0, true));
        }

        acctRepository.insert(new Acct(0, "微信", 0.0));
        acctRepository.insert(new Acct(0, "支付宝", 0.0));
        acctRepository.insert(new Acct(0, "工商银行储蓄卡", 0.0));


        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        txnRepository.insert(new Txn(0, -128.5, "2023/07/02", "12:15", "", 1, 1));
        txnRepository.insert(new Txn(0, -28.3, "2023/07/02", "13:24", "", 3, 2));
        txnRepository.insert(new Txn(0, 3500.0, "2023/07/02", "15:42", "", 1, 5));
        txnRepository.insert(new Txn(0, -47.6, "2023/07/02", "16:37", "", 2, 3));
        txnRepository.insert(new Txn(0, -18.8, "2023/07/02", "19:22", "", 3, 4));
        txnRepository.insert(new Txn(0, 1200.0, "2023/07/02", "20:03", "", 2, 6));

        txnRepository.insert(new Txn(0, -37.4, "2023/07/01", "10:33", "", 1, 1));
        txnRepository.insert(new Txn(0, -58.9, "2023/07/01", "14:10", "", 3, 3));
        txnRepository.insert(new Txn(0, -15.3, "2023/07/01", "16:28", "", 1, 4));
        txnRepository.insert(new Txn(0, -25.7, "2023/07/01", "18:12", "", 2, 2));
        txnRepository.insert(new Txn(0, -9.5, "2023/07/01", "20:36", "", 3, 1));
        txnRepository.insert(new Txn(0, 3600.0, "2023/07/01", "21:58", "", 1, 5));
        txnRepository.insert(new Txn(0, -46.1, "2023/07/01", "22:15", "", 2, 3));
        txnRepository.insert(new Txn(0, -24.6, "2023/07/01", "23:47", "", 3, 4));

        txnRepository.insert(new Txn(0, -32.8, "2023/06/30", "09:05", "", 1, 2));
        txnRepository.insert(new Txn(0, -17.2, "2023/06/30", "11:17", "", 2, 3));
        txnRepository.insert(new Txn(0, -12.9, "2023/06/30", "14:25", "", 3, 4));
        txnRepository.insert(new Txn(0, -5.5, "2023/06/30", "18:03", "", 1, 1));
        txnRepository.insert(new Txn(0, -19.6, "2023/06/30", "19:09", "", 2, 1));
        txnRepository.insert(new Txn(0, 800.0, "2023/06/30", "20:45", "", 3, 6));

        // 2023/06/29
        txnRepository.insert(new Txn(0, -18.9, "2023/06/29", "08:45", "", 1, 2));
        txnRepository.insert(new Txn(0, -31.6, "2023/06/29", "12:15", "", 2, 1));
        txnRepository.insert(new Txn(0, -24.7, "2023/06/29", "14:35", "", 3, 3));

        // 2023/06/28
        txnRepository.insert(new Txn(0, -12.3, "2023/06/28", "09:30", "", 1, 1));
        txnRepository.insert(new Txn(0, -6.8, "2023/06/28", "15:20", "", 2, 4));
        txnRepository.insert(new Txn(0, -45.9, "2023/06/28", "19:40", "", 3, 2));

        // 2023/06/27
        txnRepository.insert(new Txn(0, -21.2, "2023/06/27", "11:15", "", 1, 1));
        txnRepository.insert(new Txn(0, -11.9, "2023/06/27", "16:30", "", 2, 3));

        // 2023/06/26
        txnRepository.insert(new Txn(0, -34.7, "2023/06/26", "14:05", "", 1, 2));
        txnRepository.insert(new Txn(0, -26.1, "2023/06/26", "18:10", "", 3, 4));

        // 2023/06/23
        txnRepository.insert(new Txn(0, -20.5, "2023/06/23", "08:30", "", 1, 2));
        txnRepository.insert(new Txn(0, -45.6, "2023/06/23", "13:10", "", 2, 1));

        // 2023/06/18
        txnRepository.insert(new Txn(0, -18.9, "2023/06/18", "11:25", "", 1, 1));
        txnRepository.insert(new Txn(0, -32.3, "2023/06/18", "15:40", "", 2, 3));

        // 2023/06/15
        txnRepository.insert(new Txn(0, -27.1, "2023/06/15", "09:15", "", 1, 2));
        txnRepository.insert(new Txn(0, -15.4, "2023/06/15", "17:30", "", 3, 4));

        // 2023/06/10
        txnRepository.insert(new Txn(0, -22.8, "2023/06/10", "11:45", "", 1, 1));
        txnRepository.insert(new Txn(0, -41.3, "2023/06/10", "18:55", "", 2, 2));

        // 2023/06/05
        txnRepository.insert(new Txn(0, -28.7, "2023/06/05", "10:30", "", 1, 1));
        txnRepository.insert(new Txn(0, -14.2, "2023/06/05", "16:20", "", 3, 4));

        // 2023/05/28
        txnRepository.insert(new Txn(0, -23.5, "2023/05/28", "12:45", "", 1, 1));
        txnRepository.insert(new Txn(0, -35.6, "2023/05/28", "16:20", "", 2, 2));

        // 2023/05/15
        txnRepository.insert(new Txn(0, -19.8, "2023/05/15", "11:30", "", 1, 1));
        txnRepository.insert(new Txn(0, -28.3, "2023/05/15", "15:40", "", 2, 3));

        // 2023/04/29
        txnRepository.insert(new Txn(0, -24.1, "2023/04/29", "09:10", "", 1, 2));
        txnRepository.insert(new Txn(0, -16.4, "2023/04/29", "17:25", "", 3, 4));

        // 2023/04/17
        txnRepository.insert(new Txn(0, -25.8, "2023/04/17", "11:50", "", 1, 1));
        txnRepository.insert(new Txn(0, -39.3, "2023/04/17", "18:50", "", 2, 2));

        // 2023/04/03
        txnRepository.insert(new Txn(0, -29.7, "2023/04/03", "10:35", "", 1, 1));
        txnRepository.insert(new Txn(0, -12.2, "2023/04/03", "16:15", "", 3, 4));

        // 2023/03/29
        txnRepository.insert(new Txn(0, -21.5, "2023/03/29", "12:35", "", 1, 1));
        txnRepository.insert(new Txn(0, -33.6, "2023/03/29", "16:10", "", 2, 2));

        // 2023/03/10
        txnRepository.insert(new Txn(0, -17.8, "2023/03/10", "11:20", "", 1, 1));
        txnRepository.insert(new Txn(0, -26.3, "2023/03/10", "15:30", "", 2, 3));

        // 2023/02/15
        txnRepository.insert(new Txn(0, -22.1, "2023/02/15", "09:05", "", 1, 2));
        txnRepository.insert(new Txn(0, -14.4, "2023/02/15", "17:20", "", 3, 4));

        // 2023/01/24
        txnRepository.insert(new Txn(0, -27.8, "2023/01/24", "11:45", "", 1, 1));
        txnRepository.insert(new Txn(0, -41.3, "2023/01/24", "18:45", "", 2, 2));

        // 2023/01/05
        txnRepository.insert(new Txn(0, -31.7, "2023/01/05", "10:30", "", 1, 1));
        txnRepository.insert(new Txn(0, -16.2, "2023/01/05", "16:10", "", 3, 4));

        // 2022/12/25
        txnRepository.insert(new Txn(0, -34.5, "2022/12/25", "12:35", "", 1, 1));
        txnRepository.insert(new Txn(0, -28.6, "2022/12/25", "16:10", "", 2, 2));

        // 2022/10/18
        txnRepository.insert(new Txn(0, -22.8, "2022/10/18", "11:20", "", 1, 1));
        txnRepository.insert(new Txn(0, -31.3, "2022/10/18", "15:30", "", 2, 3));

        // 2022/09/21
        txnRepository.insert(new Txn(0, -25.1, "2022/09/21", "09:05", "", 1, 2));
        txnRepository.insert(new Txn(0, -17.4, "2022/09/21", "17:20", "", 3, 4));

        // 2022/08/14
        txnRepository.insert(new Txn(0, -30.8, "2022/08/14", "11:45", "", 1, 1));
        txnRepository.insert(new Txn(0, -44.3, "2022/08/14", "18:45", "", 2, 2));

        // 2022/07/07
        txnRepository.insert(new Txn(0, -33.7, "2022/07/07", "10:30", "", 1, 1));
        txnRepository.insert(new Txn(0, -18.2, "2022/07/07", "16:10", "", 3, 4));

        // 2022/06/15
        txnRepository.insert(new Txn(0, -36.5, "2022/06/15", "12:30", "", 1, 1));
        txnRepository.insert(new Txn(0, -29.6, "2022/06/15", "16:00", "", 2, 2));

        // 2022/02/12
        txnRepository.insert(new Txn(0, -23.8, "2022/02/12", "11:15", "", 1, 1));
        txnRepository.insert(new Txn(0, -32.3, "2022/02/12", "15:45", "", 2, 3));

        // 2021/11/21
        txnRepository.insert(new Txn(0, -26.1, "2021/11/21", "09:10", "", 1, 2));
        txnRepository.insert(new Txn(0, -18.4, "2021/11/21", "17:25", "", 3, 4));

        // 2021/05/14
        txnRepository.insert(new Txn(0, -31.8, "2021/05/14", "11:50", "", 1, 1));
        txnRepository.insert(new Txn(0, -45.3, "2021/05/14", "18:55", "", 2, 2));

        // 2020/10/07
        txnRepository.insert(new Txn(0, -34.7, "2020/10/07", "10:35", "", 1, 1));
        txnRepository.insert(new Txn(0, -19.2, "2020/10/07", "16:05", "", 3, 4));

        // 2020/06/10
        txnRepository.insert(new Txn(0, -39.5, "2020/06/10", "12:25", "", 1, 1));
        txnRepository.insert(new Txn(0, -30.6, "2020/06/10", "16:15", "", 2, 2));

        // 2020/02/18
        txnRepository.insert(new Txn(0, -25.8, "2020/02/18", "11:10", "", 1, 1));
        txnRepository.insert(new Txn(0, -34.3, "2020/02/18", "15:55", "", 2, 3));

        // 2019/12/11
        txnRepository.insert(new Txn(0, -28.1, "2019/12/11", "09:15", "", 1, 2));
        txnRepository.insert(new Txn(0, -20.4, "2019/12/11", "17:30", "", 3, 4));

        // 2019/08/13
        txnRepository.insert(new Txn(0, -33.8, "2019/08/13", "11:45", "", 1, 1));
        txnRepository.insert(new Txn(0, -47.3, "2019/08/13", "19:05", "", 2, 2));

        // 2018/11/16
        txnRepository.insert(new Txn(0, -37.7, "2018/11/16", "10:40", "", 1, 1));
        txnRepository.insert(new Txn(0, -22.2, "2018/11/16", "16:15", "", 3, 4));
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