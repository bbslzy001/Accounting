package com.example.accounting.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseFragment<B extends ViewDataBinding, VM extends ViewModel> extends Fragment
{
    protected B binding;
    protected VM viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(getViewModelClass());
        binding.setVariable(getViewModelVariableId(), viewModel);

        initView();

        return binding.getRoot();
    }

    // In BaseFragment, I didn't override the onCreate method because it's not necessary for the fragment to initialize anything in the onCreate method. Unlike activities,
    // fragments don't have a separate onCreate method for initializing views and data. Instead, you can initialize them in the onCreateView method, which is called when the fragment is created and its view hierarchy is being inflated.
    //
    // If your fragment needs to perform some initialization that doesn't involve its view hierarchy,
    // you can override the onCreate method in your fragment class and call super.onCreate(savedInstanceState) to allow the BaseFragment to perform its own initialization.

    protected abstract int getLayoutId();

    protected abstract Class<VM> getViewModelClass();

    protected abstract int getViewModelVariableId();

    /**
     * Override this method to initialize views in the fragment
     */
    protected void initView()
    {
    }
}