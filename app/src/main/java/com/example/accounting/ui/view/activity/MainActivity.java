package com.example.accounting.ui.view.activity;

import android.content.Intent;
import android.view.MenuItem;

import androidx.core.view.GravityCompat;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseActivity;
import com.example.accounting.base.BaseApplication;
import com.example.accounting.databinding.ActivityMainBinding;
import com.example.accounting.ui.viewmodel.activity.MainActViewModel;
import com.example.accounting.utils.adapter.MainVpAdapter;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_main;
    }

    @Override
    protected Class<MainActViewModel> getViewModelClass()
    {
        return MainActViewModel.class;
    }

    @Override
    protected int getViewModelVariableId()
    {
        return BR.viewModel;
    }

    @Override
    protected void initView()
    {
        super.initView();

        initTopAppBar();
        initDrawer();
        initViewPager();
        initNavigation();
        initAddTradeButton();

        /* 如果是第一次启动应用，初始化数据 */
        if (((BaseApplication)getApplication()).isFirstTimeLaunch())
        {
            viewModel.initData();
            ((BaseApplication)getApplication()).setFirstTimeLaunch(false);
        }

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
            else if (id == R.id.display) viewModel.updateStatsFragState();
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
     * 初始化 ViewPager2
     */
    private void initViewPager()
    {
        binding.viewPager.setAdapter(new MainVpAdapter(this));

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
    }

    /**
     * 初始化底部导航栏
     */
    private void initNavigation()
    {
        // 设置空白标签不可选中且不可用
        MenuItem menuItem = binding.bottomNavigation.getMenu().getItem(2);
        menuItem.setCheckable(false);
        menuItem.setEnabled(false);

        binding.bottomNavigation.setOnItemSelectedListener(item ->
        {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home)
            {
                binding.viewPager.setCurrentItem(0, false);
                viewModel.getTopAppBarTitle().setValue(R.string.app_name);
            }
            else if (itemId == R.id.navigation_analyse)
            {
                binding.viewPager.setCurrentItem(1, false);
                viewModel.getTopAppBarTitle().setValue(R.string.anal);
            }
            else if (itemId == R.id.navigation_statistics)
            {
                binding.viewPager.setCurrentItem(2, false);
                viewModel.getTopAppBarTitle().setValue(R.string.stats);
            }
            else if (itemId == R.id.navigation_account)
            {
                binding.viewPager.setCurrentItem(3, false);
                viewModel.getTopAppBarTitle().setValue(R.string.acct);
            }
            else return false;
            return true;
        });
    }

    /**
     * 初始化新增交易按钮
     */
    private void initAddTradeButton()
    {
        binding.addTradeButton.setOnClickListener(view -> startActivity(new Intent(this, AddTxnActivity.class)));
    }
}