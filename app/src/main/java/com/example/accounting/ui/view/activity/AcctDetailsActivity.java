package com.example.accounting.ui.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.accounting.R;
import com.example.accounting.databinding.ActivityAcctDetailsBinding;
import com.example.accounting.ui.viewmodel.activity.AcctDetailsActViewModel;

public class AcctDetailsActivity extends AppCompatActivity
{
    private ActivityAcctDetailsBinding binding;
    private AcctDetailsActViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);  // 全屏布局

        binding = DataBindingUtil.setContentView(this, R.layout.activity_acct_details);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(AcctDetailsActViewModel.class);
        binding.setViewModel(viewModel);
    }
}