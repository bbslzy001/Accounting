package com.example.accounting.ui.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.WindowCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.accounting.R;
import com.example.accounting.application.MyApplication;
import com.example.accounting.ui.viewmodel.ShareViewModel;
import com.example.accounting.ui.viewmodel.activity.MainActivityViewModel;
import com.example.accounting.utils.adapter.MainActivityViewPagerAdapter;
import com.example.accounting.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private ShareViewModel shareViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);  // 全屏布局

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding.setViewModel(viewModel);

        shareViewModel = new ViewModelProvider(this).get(ShareViewModel.class);

        initTopAppBar();
        initDrawer();
        initNavigation();
        initAddTradeButton();

        viewModel.fakeData();
    }

    /**
     * 初始化顶部应用栏
     */
    private void initTopAppBar()
    {
        /* 通过点击TopAppBar的菜单按钮打开侧边栏，和滑动打开侧边栏不冲突 */
        binding.topAppBar.setNavigationOnClickListener(view -> binding.drawerLayout.openDrawer(GravityCompat.START));
        binding.topAppBar.setOnMenuItemClickListener(item ->
        {
            int id = item.getItemId();
            if (id == R.id.dashboard) startActivity(new Intent(this, DashboardActivity.class));
            else if (id == R.id.display)
            {
                ActionMenuItemView itemView = findViewById(R.id.display);
                if (Boolean.TRUE.equals(shareViewModel.getButtonState().getValue())) itemView.setIcon(ContextCompat.getDrawable(this,R.drawable.ic_list));
                else itemView.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_calendar));
                shareViewModel.setButtonState();
            }
            else if (id == R.id.search) startActivity(new Intent(this, SearchActivity.class));
            else return false;
            return true;
        });
    }

    /**
     * 初始化侧边栏
     */
    private void initDrawer()
    {
        binding.drawer.setNavigationItemSelectedListener(item ->
        {
            int itemId = item.getItemId();
            if (itemId == R.id.drawer_setting)
                startActivity(new Intent(this, SettingActivity.class));
            else if (itemId == R.id.drawer_about)
                startActivity(new Intent(this, AboutActivity.class));
            else return false;
            return true;
        });
    }

    /**
     * 初始化侧边栏————打开侧边栏后按返回键优先关闭侧边栏
     */
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

    /**
     * 初始化底部导航栏
     */
    private void initNavigation()
    {
        binding.viewPager.setAdapter(new MainActivityViewPagerAdapter(this));

//        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback()  // 由于设置了不可滑动，用户无法改变页面，故不需要覆写回调方法
//        {
//            @Override
//            public void onPageSelected(int position)
//            {
//                binding.bottomNavigation.getMenu().getItem(position).setChecked(true);
//                updateTopAppBar(position);
//            }
//        });

        binding.viewPager.setUserInputEnabled(false);  // 设置 viewpager 不可滑动

        updateTopAppBar(0);  // 由于没有覆写viewpager回调方法，且启动程序时不会触发bottomNavigation的监听方法，故需要手动初始化

        // 设置空白标签不可选中且不可用
        MenuItem menuItem = binding.bottomNavigation.getMenu().getItem(2);
        menuItem.setCheckable(false);
        menuItem.setEnabled(false);

        binding.bottomNavigation.setOnItemSelectedListener(item ->
        {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home)
            {
                binding.viewPager.setCurrentItem(0);
                updateTopAppBar(0);
            }
            else if (itemId == R.id.navigation_analyse)
            {
                binding.viewPager.setCurrentItem(1);
                updateTopAppBar(1);
            }
            else if (itemId == R.id.navigation_statistics)
            {
                binding.viewPager.setCurrentItem(2);
                updateTopAppBar(2);
            }
            else if (itemId == R.id.navigation_account)
            {
                binding.viewPager.setCurrentItem(3);
                updateTopAppBar(3);
            }
            else return false;
            return true;
        });
    }

    /**
     * 初始化底部导航栏————根据当前 Fragment 自动调整 TopAppBar 的 title 和 menu
     */
    private void updateTopAppBar(int position)
    {
        String[] titles = new String[]{this.getString(R.string.app_name), this.getString(R.string.analyse), this.getString(R.string.statistics), this.getString(R.string.account)};
        viewModel.getTopAppBarTitle().setValue(titles[position]);
        binding.topAppBar.getMenu().clear();
        if (position == 0) binding.topAppBar.inflateMenu(R.menu.home_top_bar_menu);
        else if (position == 2) binding.topAppBar.inflateMenu(R.menu.statistics_top_bar_menu);
    }

    /**
     * 初始化新增交易按钮
     */
    private void initAddTradeButton()
    {
        binding.addTradeButton.setOnClickListener(view ->
        {
            viewModel.addTradeInfo();
            Toast.makeText(MyApplication.getContext(), "点击了Button", Toast.LENGTH_SHORT).show();
        });
    }
}