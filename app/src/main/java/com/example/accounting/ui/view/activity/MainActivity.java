package com.example.accounting.ui.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import com.example.accounting.R;
import com.example.accounting.adapter.ViewPagerAdapter;
import com.example.accounting.databinding.ActivityMainBinding;
import com.example.accounting.model.repository.AccountTypeRepository;
import com.example.accounting.model.room.bean.AccountType;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding binding;

    private final AccountTypeRepository accountTypeRepository = new AccountTypeRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        initNavigation();
        fakeData();
    }

    private void initNavigation()
    {
        binding.viewPager.setAdapter(new ViewPagerAdapter(this));
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback()
        {
            @Override
            public void onPageSelected(int position)
            {
                binding.navigation.getMenu().getItem(position).setChecked(true);
            }
        });

        binding.navigation.setOnItemSelectedListener(item ->
        {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) binding.viewPager.setCurrentItem(0);
            else if (itemId == R.id.navigation_statistics) binding.viewPager.setCurrentItem(1);
            else if (itemId == R.id.navigation_individual) binding.viewPager.setCurrentItem(2);
            else return false;
            return true;
        });
    }

    private void fakeData()
    {
        accountTypeRepository.deleteAll();
        accountTypeRepository.insert(new AccountType(0,"工商银行储蓄卡"));
        accountTypeRepository.insert(new AccountType(0,"微信"));
        accountTypeRepository.insert(new AccountType(0,"支付宝"));
    }
}