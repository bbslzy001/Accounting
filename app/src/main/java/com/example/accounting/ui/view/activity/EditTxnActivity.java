package com.example.accounting.ui.view.activity;

import android.widget.ArrayAdapter;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseActivity;
import com.example.accounting.databinding.ActivityEditTxnBinding;
import com.example.accounting.model.room.bean.TxnRvItem;
import com.example.accounting.ui.viewmodel.activity.EditTxnActViewModel;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class EditTxnActivity extends BaseActivity<ActivityEditTxnBinding, EditTxnActViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_edit_txn;
    }

    @Override
    protected Class<EditTxnActViewModel> getViewModelClass()
    {
        return EditTxnActViewModel.class;
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
        viewModel.setTxnRvItem((TxnRvItem) getIntent().getSerializableExtra("txnRvItem"));
        viewModel.initTxnInfo();
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
        binding.txnRemark.setText(viewModel.getTxnRvItem().getRemark());
    }

    private void initSpinner()
    {
        ArrayAdapter<String> adapterForTvnAcct = new ArrayAdapter<>(this, R.layout.text_field_list_item, viewModel.getAcctTypeArray());
        binding.txnAcct.setAdapter(adapterForTvnAcct);
        binding.txnAcct.setText(viewModel.getTxnRvItem().getAcctType(), false);
        ArrayAdapter<String> adapterForTvnType = new ArrayAdapter<>(this, R.layout.text_field_list_item, viewModel.getTxnTypeArray());
        binding.txnType.setAdapter(adapterForTvnType);
        binding.txnType.setText(viewModel.getTxnRvItem().getTxnType(), false);
    }

    private void initDatePicker()
    {
        binding.txnDate.setText(viewModel.getTxnRvItem().getDate());
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
            datePicker.show(getSupportFragmentManager(), "datePicker");
        });
    }

    private void initTimePicker()
    {
        binding.txnTime.setText(viewModel.getTxnRvItem().getTime());
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
            timePicker.show(getSupportFragmentManager(), "timePicker");
        });
    }

    private void initToggleButton()
    {
        if (viewModel.getTxnRvItem().getAmount() > 0) binding.toggleButton.check(binding.toggleButton.getChildAt(0).getId());
        else binding.toggleButton.check(binding.toggleButton.getChildAt(1).getId());
    }

    private void initAmountText()
    {
        binding.txnAmount.setText(String.valueOf(Math.abs(viewModel.getTxnRvItem().getAmount())));
    }

    private void initTopBar()
    {
        binding.close.setOnClickListener(view -> finish());
        binding.save.setOnClickListener(view ->
        {
            viewModel.update(
                    binding.toggleButton.getCheckedButtonId() == binding.button1.getId() ? 0 : 1,
                    Objects.requireNonNull(binding.txnAmount.getText()).toString(),
                    Objects.requireNonNull(binding.txnDate.getText()).toString(),
                    Objects.requireNonNull(binding.txnTime.getText()).toString(),
                    Objects.requireNonNull(binding.txnRemark.getText()).toString(),
                    binding.txnAcct.getText().toString(),
                    binding.txnType.getText().toString()
            );
            finish();
        });
    }
}