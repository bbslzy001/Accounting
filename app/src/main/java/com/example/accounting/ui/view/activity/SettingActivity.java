package com.example.accounting.ui.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.accounting.R;
import com.example.accounting.databinding.ActivitySettingBinding;
import com.example.accounting.ui.viewmodel.activity.SettingActViewModel;

public class SettingActivity extends AppCompatActivity
{
    private ActivitySettingBinding binding;
    private SettingActViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);  // 全屏布局

        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(SettingActViewModel.class);
        binding.setViewModel(viewModel);
    }
}