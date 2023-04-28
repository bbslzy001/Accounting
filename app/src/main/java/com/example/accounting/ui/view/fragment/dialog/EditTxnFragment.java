package com.example.accounting.ui.view.fragment.dialog;

import android.widget.ArrayAdapter;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseDialogFragment;
import com.example.accounting.databinding.FragmentEditTxnBinding;
import com.example.accounting.ui.viewmodel.fragment.dialog.EditTxnFragViewModel;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
        viewModel.initTxnInfo(txnInfoId);
        viewModel.getIsAllCompleted().observe(this, isAllCompleted ->
        {
            if (isAllCompleted == 0)
            {
                initUI();
            }
        });
    }

    private void initUI()
    {
        initTopBar();
        initDatePicker();
        initTimePicker();
        initSpinner();
        initToggleButton();
        initAmountText();
        initRemarkText();
    }

    private void initRemarkText()
    {
        binding.txnRemark.setText(viewModel.getRemark());
    }

    private void initSpinner()
    {
        ArrayAdapter<String> adapterForTvnAcct = new ArrayAdapter<>(requireContext(), R.layout.text_field_list_item, viewModel.getAcctTypeArray());
        binding.txnAcct.setAdapter(adapterForTvnAcct);
        binding.txnAcct.setText(viewModel.getAcctTypeArray()[0], false);
        ArrayAdapter<String> adapterForTvnType = new ArrayAdapter<>(requireContext(), R.layout.text_field_list_item, viewModel.getTxnTypeArray());
        binding.txnType.setAdapter(adapterForTvnType);
        binding.txnType.setText(viewModel.getTxnTypeArray()[2], false);
    }

    private void initDatePicker()
    {
        binding.txnDate.setOnClickListener(view ->
        {
            // 创建MaterialDatePicker
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("选择日期")
                    .setSelection(viewModel.getMilliseconds())
                    //.setSelection(MaterialDatePicker.todayInUtcMilliseconds())  设置为当前日期
                    .build();

            // 设置日期选择器的回调函数
            datePicker.addOnPositiveButtonClickListener(selection ->
            {
                // 将选择的日期转换为yyyy-MM-dd格式的字符串
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                String selectedDate = sdf.format(new Date(selection));

                // 将选择的日期设置为输入框的文本值
                binding.txnDate.setText(selectedDate);
            });

            // 显示日期选择器
            datePicker.show(requireActivity().getSupportFragmentManager(), "datePicker");
        });
    }

    private void initTimePicker()
    {
        binding.txnTime.setOnClickListener(view ->
        {
            // 创建 MaterialTimePicker
            MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(viewModel.getHour())
                    .setMinute(viewModel.getMinute())
                    .setTitleText("选择时间")
                    .build();

            // 设置时间选择器的回调函数
            timePicker.addOnPositiveButtonClickListener(v ->
            {
                // 获取选择的小时数和分钟数
                int selectedHour = timePicker.getHour();
                int selectedMinute = timePicker.getMinute();

                // 将选择的时间设置为输入框的文本值
                String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
                binding.txnTime.setText(selectedTime);
            });

            // 显示时间选择器
            timePicker.show(requireActivity().getSupportFragmentManager(), "timePicker");
        });
    }

    private void initToggleButton()
    {
        if (viewModel.isIncome()) binding.toggleButton.check(binding.toggleButton.getChildAt(0).getId());
        else binding.toggleButton.check(binding.toggleButton.getChildAt(1).getId());
    }

    private void initAmountText()
    {
        binding.txnAmount.setText(String.valueOf(viewModel.getAmount()));
    }

    private void initTopBar()
    {
        binding.close.setOnClickListener(view -> dismiss());
        binding.save.setOnClickListener(view -> dismiss());
    }
}