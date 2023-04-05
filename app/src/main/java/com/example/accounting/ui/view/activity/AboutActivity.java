package com.example.accounting.ui.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.accounting.R;
import com.example.accounting.databinding.ActivityAboutBinding;
import com.example.accounting.ui.viewmodel.activity.AboutActivityViewModel;

public class AboutActivity extends AppCompatActivity
{
    private ActivityAboutBinding binding;
    private AboutActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);  // 全屏布局

        binding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(AboutActivityViewModel.class);
        binding.setViewModel(viewModel);
    }
}