package com.example.accounting.ui.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.accounting.R;
import com.example.accounting.databinding.ActivitySearchBinding;
import com.example.accounting.ui.viewmodel.activity.SearchActivityViewModel;

public class SearchActivity extends AppCompatActivity
{
    private ActivitySearchBinding binding;
    private SearchActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);  // 全屏布局

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(SearchActivityViewModel.class);
        binding.setViewModel(viewModel);
    }
}