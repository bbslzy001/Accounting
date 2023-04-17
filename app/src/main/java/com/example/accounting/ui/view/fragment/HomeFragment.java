package com.example.accounting.ui.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.accounting.ui.viewmodel.fragment.HomeFragmentViewModel;
import com.example.accounting.utils.HomeRvItemDecoration;
import com.example.accounting.utils.adapter.HomeRvAdapter;

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

        initRecyclerView();
        initFloatingButton();

        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化滚动视图
     */
    private void initRecyclerView()
    {
        HomeRvAdapter adapter = new HomeRvAdapter();
        binding.recyclerView.setAdapter(adapter);
        HomeRvItemDecoration itemDecoration = new HomeRvItemDecoration(this.getContext(), adapter);
        binding.recyclerView.addItemDecoration(itemDecoration);
        binding.recyclerView.addOnItemTouchListener(itemDecoration);
    }

    /**
     * 初始化浮动按钮
     */
    private void initFloatingButton()
    {
        binding.extendedFloatingButton.setOnClickListener(view ->
        {
            viewModel.addTradeInfo();
            Toast.makeText(MyApplication.getContext(), "点击了extendedFloatingButton", Toast.LENGTH_SHORT).show();
        });
    }
}