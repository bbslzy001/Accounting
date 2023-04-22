package com.example.accounting.ui.view.activity;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseApplication;
import com.example.accounting.base.BaseActivity;
import com.example.accounting.databinding.ActivityMainBinding;
import com.example.accounting.ui.viewmodel.ShareViewModel;
import com.example.accounting.ui.viewmodel.activity.MainActViewModel;
import com.example.accounting.utils.adapter.MainVpAdapter;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActViewModel>
{
    private ShareViewModel shareViewModel;

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

        shareViewModel = new ViewModelProvider(this).get(ShareViewModel.class);

        initTopAppBar();
        initDrawer();
        initViewPager();
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
                MenuItem menuItem = binding.topAppBar.getMenu().findItem(R.id.display);
                if (Boolean.TRUE.equals(shareViewModel.getButtonState().getValue()))
                    menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_list));
                else menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_cal));
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
                binding.viewPager.setCurrentItem(0, false);
                updateTopAppBar(0);
            }
            else if (itemId == R.id.navigation_analyse)
            {
                binding.viewPager.setCurrentItem(1, false);
                updateTopAppBar(1);
            }
            else if (itemId == R.id.navigation_statistics)
            {
                binding.viewPager.setCurrentItem(2, false);
                updateTopAppBar(2);
            }
            else if (itemId == R.id.navigation_account)
            {
                binding.viewPager.setCurrentItem(3, false);
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
        String[] titles = new String[]{this.getString(R.string.app_name), this.getString(R.string.anal), this.getString(R.string.stats), this.getString(R.string.acct)};
        viewModel.getTopAppBarTitle().setValue(titles[position]);
        binding.topAppBar.getMenu().clear();
        if (position == 0) binding.topAppBar.inflateMenu(R.menu.home_top_bar_menu);
        else if (position == 2) binding.topAppBar.inflateMenu(R.menu.stats_top_bar_menu);
    }

    /**
     * 初始化新增交易按钮
     */
    private void initAddTradeButton()
    {
        binding.addTradeButton.setOnClickListener(view ->
        {
            viewModel.addTradeInfo();
            Toast.makeText(BaseApplication.getContext(), "点击了Button", Toast.LENGTH_SHORT).show();
        });
    }
}