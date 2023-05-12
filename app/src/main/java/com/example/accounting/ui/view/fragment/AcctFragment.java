package com.example.accounting.ui.view.fragment;

import android.content.Intent;
import android.widget.Toast;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseApplication;
import com.example.accounting.base.BaseFragment;
import com.example.accounting.databinding.FragmentAcctBinding;
import com.example.accounting.ui.view.activity.AcctDetailsActivity;
import com.example.accounting.ui.viewmodel.fragment.AcctFragViewModel;
import com.example.accounting.utils.adapter.AcctRvAdapter;

public class AcctFragment extends BaseFragment<FragmentAcctBinding, AcctFragViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_acct;
    }

    @Override
    protected Class<AcctFragViewModel> getViewModelClass()
    {
        return AcctFragViewModel.class;
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

        initRecyclerView();
        initFloatingButton();
    }

    /**
     * 初始化滚动视图
     */
    private void initRecyclerView()
    {
        AcctRvAdapter adapter = new AcctRvAdapter();
        adapter.setOnItemClickListener(acctId -> startActivity(new Intent(requireActivity(), AcctDetailsActivity.class).putExtra("acctId", acctId)));
        binding.recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化浮动按钮
     */
    private void initFloatingButton()
    {
        binding.floatingButton.setOnClickListener(view ->
        {
            viewModel.addAcctType();
            Toast.makeText(BaseApplication.getContext(), "点击了floatButton", Toast.LENGTH_SHORT).show();
        });
    }
}