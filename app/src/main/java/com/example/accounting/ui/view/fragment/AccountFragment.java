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
import com.example.accounting.databinding.FragmentAccountBinding;
import com.example.accounting.ui.viewmodel.fragment.AccountFragmentViewModel;
import com.example.accounting.utils.adapter.AccountRecyclerViewAdapter;

public class AccountFragment extends Fragment
{
    private FragmentAccountBinding binding;
    private AccountFragmentViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(AccountFragmentViewModel.class);
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
        binding.recyclerView.setAdapter(new AccountRecyclerViewAdapter());
    }

    /**
     * 初始化浮动按钮
     */
    private void initFloatingButton()
    {
        binding.floatingButton.setOnClickListener(view ->
        {
            viewModel.addAccountType();
            Toast.makeText(MyApplication.getContext(), "点击了floatButton", Toast.LENGTH_SHORT).show();
        });
    }
}