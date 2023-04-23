package com.example.accounting.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseActivity<B extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity
{
    protected B binding;
    protected VM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);  // Full-screen layout

        binding = DataBindingUtil.setContentView(this, getLayoutId());
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(getViewModelClass());
        binding.setVariable(getViewModelVariableId(), viewModel);

        initView();
    }

    protected abstract int getLayoutId();

    protected abstract Class<VM> getViewModelClass();

    protected abstract int getViewModelVariableId();

    /**
     * Override this method to initialize views in the activity
     */
    protected void initView()
    {
    }
}