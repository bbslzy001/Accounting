package com.example.accounting.ui.view.activity;

import android.widget.ArrayAdapter;
import android.widget.Toast;

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

        viewModel.initFormData((TxnRvItem) getIntent().getSerializableExtra("txnRvItem"));
        viewModel.initDropdownData();
        viewModel.isAllCompleted().observe(this, isAllCompleted ->
        {
            if (isAllCompleted == 0)
            {
                initTopAppBar();
                initToggleButton();
                initSpinner();
                initDatePicker();
                initTimePicker();
            }
        });
    }

    /**
     * 初始化顶部应用栏
     */
    private void initTopAppBar()
    {
        binding.topAppBar.setNavigationOnClickListener(view -> finish());
        binding.topAppBar.setOnMenuItemClickListener(menuItem ->
        {
            if (menuItem.getItemId() == R.id.save)
            {
                int result = viewModel.updateTxnInfo();
                if (result == 1) finish();
                else
                {
                    String[] toastInfo = new String[]{"未知错误", "交易金额不能为空", "交易账户不能为空", "交易类型不能为空", "请填写正确的交易金额"};
                    Toast.makeText(this, toastInfo[Math.abs(result)], Toast.LENGTH_SHORT).show();
                }
            }
            return false;
        });
    }

    private void initToggleButton()
    {
        binding.toggleButton.addOnButtonCheckedListener((group, checkedId, isChecked) ->
        {
            int incomeOrExpense = checkedId == R.id.button1 && isChecked ? 0 : 1;
            Objects.requireNonNull(viewModel.getFormData().getValue()).setIncomeOrExpense(incomeOrExpense);
        });
    }

    private void initSpinner()
    {
        ArrayAdapter<String> adapterForTvnAcct = new ArrayAdapter<>(this, R.layout.text_field_list_item, viewModel.getAcctTypeArray());
        binding.txnAcct.setAdapter(adapterForTvnAcct);
        ArrayAdapter<String> adapterForTvnType = new ArrayAdapter<>(this, R.layout.text_field_list_item, viewModel.getTxnTypeArray());
        binding.txnType.setAdapter(adapterForTvnType);
    }

    private void initDatePicker()
    {
        binding.txnDate.setOnClickListener(view ->
        {
            // 创建MaterialDatePicker
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("选择日期")
                    .setSelection(viewModel.getMilliseconds())
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
        binding.txnTime.setOnClickListener(view ->
        {
            // 创建 MaterialTimePicker
            MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                    .setTitleText("选择时间")
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(viewModel.getHour())
                    .setMinute(viewModel.getMinute())
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
}