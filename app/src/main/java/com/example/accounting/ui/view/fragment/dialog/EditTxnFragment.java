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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
        binding.txnAmount.setText(String.valueOf(viewModel.getAmount()));
        binding.close.setOnClickListener(view -> dismiss());
        binding.save.setOnClickListener(view -> dismiss());
        binding.txnDate.setOnClickListener(view ->
        {
            String dateString = "2022/01/01"; // 指定日期字符串
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")); // 创建UTC时区的Calendar对象
            String[] dateParts = dateString.split("/"); // 分割日期字符串
            int year = Integer.parseInt(dateParts[0]); // 获取年份
            int month = Integer.parseInt(dateParts[1]) - 1; // 获取月份（Calendar月份从0开始）
            int day = Integer.parseInt(dateParts[2]); // 获取日
            calendar.set(year, month, day, 0, 0, 0); // 设置Calendar对象的时间为指定日期的第一个时刻
            calendar.set(Calendar.MILLISECOND, 0); // 将毫秒数设置为0
            long milliseconds = calendar.getTimeInMillis(); // 获取时间戳

            // 创建MaterialDatePicker
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("选择日期")
                    .setSelection(milliseconds)
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
        binding.txnTime.setOnClickListener(view ->
        {
            // 创建 MaterialTimePicker
            MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(12)
                    .setMinute(0)
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
}