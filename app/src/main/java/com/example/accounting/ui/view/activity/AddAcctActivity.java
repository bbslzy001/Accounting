package com.example.accounting.ui.view.activity;

import android.widget.Toast;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseActivity;
import com.example.accounting.databinding.ActivityAddAcctBinding;
import com.example.accounting.ui.viewmodel.activity.AddAcctActViewModel;

public class AddAcctActivity extends BaseActivity<ActivityAddAcctBinding, AddAcctActViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return  R.layout.activity_add_acct;
    }

    @Override
    protected Class<AddAcctActViewModel> getViewModelClass()
    {
        return AddAcctActViewModel.class;
    }

    @Override
    protected int getViewModelVariableId()
    {
        return BR.viewModel;
    }

    @Override
    protected void initView()
    {
        super.initView();

        viewModel.initFormData();
        initTopAppBar();
    }

    /**
     * 初始化顶部应用栏
     */
    private void initTopAppBar()
    {
        binding.topAppBar.setNavigationOnClickListener(view -> finish());
        binding.topAppBar.setOnMenuItemClickListener(menuItem ->
        {
            if (menuItem.getItemId() == R.id.add)
            {
                int result = viewModel.insertAcctType();
                if (result == 1) finish();
                else
                {
                    String[] toastInfo = new String[]{"未知错误", "账户名称不能为空", "账户金额不能为空", "请填写正确的账户金额"};
                    Toast.makeText(this, toastInfo[Math.abs(result)], Toast.LENGTH_SHORT).show();
                }
            }
            return false;
        });
    }
}