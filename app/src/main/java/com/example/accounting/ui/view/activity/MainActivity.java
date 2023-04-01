package com.example.accounting.ui.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.core.view.WindowCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.accounting.R;
import com.example.accounting.ui.viewmodel.activity.MainActivityViewModel;
import com.example.accounting.utils.adapter.ViewPagerAdapter;
import com.example.accounting.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);  // 全屏布局

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding.setViewModel(viewModel);

        initNavigation();

        viewModel.fakeData();
    }

    private void initNavigation()
    {
        binding.viewPager.setAdapter(new ViewPagerAdapter(this));
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback()
        {
            @Override
            public void onPageSelected(int position)
            {
                binding.bottomNavigation.getMenu().getItem(position).setChecked(true);
            }
        });

        binding.bottomNavigation.setOnItemSelectedListener(item ->
        {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) binding.viewPager.setCurrentItem(0);
            else if (itemId == R.id.navigation_statistics) binding.viewPager.setCurrentItem(1);
            else if (itemId == R.id.navigation_account) binding.viewPager.setCurrentItem(2);
            else return false;
            return true;
        });

        binding.drawer.setNavigationItemSelectedListener(item ->
        {
            int itemId = item.getItemId();
            if(itemId==R.id.drawer_setting) startActivity(new Intent(this, SettingActivity.class));
            else if(itemId==R.id.drawer_about) startActivity(new Intent(this, AboutActivity.class));
            else return false;
            return true;
        });
    }

    public void openDrawer()
    {
        binding.drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed()
    {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
}