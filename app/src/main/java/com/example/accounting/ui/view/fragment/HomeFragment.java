package com.example.accounting.ui.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.accounting.R;
import com.example.accounting.databinding.FragmentHomeBinding;
import com.example.accounting.ui.view.activity.MainActivity;
import com.example.accounting.ui.view.activity.SearchActivity;
import com.example.accounting.ui.viewmodel.MainActivityViewModel;

import java.util.Objects;

public class HomeFragment extends Fragment
{
    private FragmentHomeBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding.setViewModel(viewModel);

        init();

        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void init()
    {
        binding.floatingButton.setOnClickListener(view ->
        {
        });

        binding.topAppBar.setNavigationOnClickListener(view -> ((MainActivity) requireActivity()).openDrawer());

        binding.topAppBar.setOnMenuItemClickListener(item ->
        {
            int id = item.getItemId();
            if (id == R.id.search) startActivity(new Intent(getActivity(), SearchActivity.class));
            else if (id == R.id.more) requireActivity().openOptionsMenu();
            else return false;
            return true;
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
    {
        // 调用父类的方法
        super.onCreateOptionsMenu(menu, inflater);
        // 将xml文件中定义的menu资源转换为menu对象，并添加到menu参数中
        inflater.inflate(R.menu.top_bar_more_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.wechat)
        {
        }
        else if (id == R.id.alipay)
        {
        }
        else
        {
            super.onOptionsItemSelected(item);
            return false;
        }
        return true;
    }
}