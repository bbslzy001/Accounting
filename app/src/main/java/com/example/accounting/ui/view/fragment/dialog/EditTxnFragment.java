package com.example.accounting.ui.view.fragment.dialog;

import android.widget.ArrayAdapter;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseDialogFragment;
import com.example.accounting.databinding.FragmentEditTxnBinding;
import com.example.accounting.ui.viewmodel.fragment.dialog.EditTxnFragViewModel;

public class EditTxnFragment extends BaseDialogFragment<FragmentEditTxnBinding, EditTxnFragViewModel>
{
    private final int txnInfoId;

    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_edit_txn;
    }

    @Override
    protected Class<EditTxnFragViewModel> getViewModelClass()
    {
        return EditTxnFragViewModel.class;
    }

    @Override
    protected int getViewModelVariableId()
    {
        return BR.viewModel;
    }

    public EditTxnFragment(int txnInfoId)
    {
        super();
        this.txnInfoId = txnInfoId;
    }

    @Override
    protected void initView()
    {
        super.initView();
        viewModel.setTxnInfoId(txnInfoId);
        ArrayAdapter<String> adapterForTvnAcct = new ArrayAdapter<>(requireContext(), R.layout.text_field_list_item, viewModel.getTxnAcctList());
        binding.txnAcct.setAdapter(adapterForTvnAcct);
        binding.txnAcct.setText(viewModel.getTxnAcctList()[0], false);
        ArrayAdapter<String> adapterForTvnType = new ArrayAdapter<>(requireContext(), R.layout.text_field_list_item, viewModel.getTxnTypeList());
        binding.txnType.setAdapter(adapterForTvnType);
        binding.txnType.setText(viewModel.getTxnTypeList()[2], false);
    }
}