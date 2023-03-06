package com.example.accounting.ui.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.accounting.R;
import com.example.accounting.adapter.ViewPagerAdapter;
import com.example.accounting.databinding.ActivityMainBinding;
import com.example.accounting.ui.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding.setViewModel(viewModel);

        initNavigation();
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
}