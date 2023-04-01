package com.example.accounting.ui.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.accounting.R;
import com.example.accounting.application.MyApplication;
import com.example.accounting.databinding.FragmentHomeBinding;
import com.example.accounting.ui.view.activity.MainActivity;
import com.example.accounting.ui.view.activity.SearchActivity;
import com.example.accounting.ui.viewmodel.fragment.HomeFragmentViewModel;
import com.example.accounting.utils.adapter.RecyclerViewAdapter;

public class HomeFragment extends Fragment
{
    private FragmentHomeBinding binding;
    private HomeFragmentViewModel viewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        binding.setViewModel(viewModel);

        init();

        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    private void init()
    {
        binding.floatingButton.setOnClickListener(view ->
        {
            viewModel.addTradeInfo();
            Toast.makeText(MyApplication.getContext(), "点击了floatButton", Toast.LENGTH_SHORT).show();
        });
        /* 通过点击TopAppBar的菜单按钮打开侧边栏，和滑动打开侧边栏不冲突 */
        binding.topAppBar.setNavigationOnClickListener(view -> ((MainActivity) requireActivity()).openDrawer());
        binding.topAppBar.setOnMenuItemClickListener(item ->
        {
            int id = item.getItemId();
            if (id == R.id.search) startActivity(new Intent(getActivity(), SearchActivity.class));
            else if (id == R.id.more) openMoreMenu(binding.topAppBar.findViewById(R.id.more));
            else return false;
            return true;
        });

        binding.recyclerView.setAdapter(new RecyclerViewAdapter());
    }

    private void openMoreMenu(View view)
    {
        PopupMenu popupMenu = new PopupMenu(requireActivity(), view);
        popupMenu.inflate(R.menu.top_bar_more_menu);
        popupMenu.setOnMenuItemClickListener(item ->
        {
            int id = item.getItemId();
            if (id == R.id.wechat) Toast.makeText(MyApplication.getContext(), "点击了wechat", Toast.LENGTH_SHORT).show();
            else if (id == R.id.alipay) Toast.makeText(MyApplication.getContext(), "点击了alipay", Toast.LENGTH_SHORT).show();
            else return false;
            return true;
        });
        popupMenu.show();
    }
}