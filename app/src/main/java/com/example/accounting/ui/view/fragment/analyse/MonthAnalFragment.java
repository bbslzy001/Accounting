package com.example.accounting.ui.view.fragment.analyse;

import androidx.lifecycle.LiveData;

import com.example.accounting.BR;
import com.example.accounting.R;
import com.example.accounting.base.BaseFragment;
import com.example.accounting.databinding.FragmentAnalMonthBinding;
import com.example.accounting.model.room.bean.PostInfo;
import com.example.accounting.ui.viewmodel.fragment.analyse.MonthAnalFragViewModel;
import com.example.accounting.utils.chatgpt.ChatGPTServer;
import com.example.accounting.utils.chatgpt.Message;
import com.example.accounting.utils.chatgpt.Prompt;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

public class MonthAnalFragment extends BaseFragment<FragmentAnalMonthBinding, MonthAnalFragViewModel>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.fragment_anal_month;
    }

    @Override
    protected Class<MonthAnalFragViewModel> getViewModelClass()
    {
        return MonthAnalFragViewModel.class;
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
        initAiSuggestion();
        initIncomeChart();
        initExpenseChart();
    }

    private void initAiSuggestion()
    {
        LiveData<PostInfo> postInfoLiveData = viewModel.getPostInfo();
        postInfoLiveData.observe(this, postInfo ->
        {
            if (postInfo != null)
            {
                ChatGPTServer.getResponse(Prompt.monthPrompt, Message.getSuggestionMessage(postInfo), new ChatGPTServer.OnPostInfoReceivedListener()
                {
                    @Override
                    public void onPostInfoReceived(String response)
                    {
                        binding.aiText.setText(response);
                    }

                    @Override
                    public void onError(Exception e)
                    {
                        e.printStackTrace();
                        binding.aiText.setText(e.toString());
                    }
                });
            }
        });
    }

    private void initIncomeChart()
    {
        // 设置x轴
        XAxis xAxis = binding.incomeChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"二月", "三月", "四月", "五月", "六月", "七月"}));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(6);

        // 填充数据
        binding.incomeChart.setData(viewModel.getIncomeData());

        // 设置空白描述
        Description description = new Description();
        description.setText("");
        binding.incomeChart.setDescription(description);
    }

    private void initExpenseChart()
    {
        // 设置x轴
        XAxis xAxis = binding.expenseChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"二月", "三月","四月","五月","六月","七月"}));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(6);

        // 填充数据
        binding.expenseChart.setData(viewModel.getExpenseData());

        // 设置空白描述
        Description description = new Description();
        description.setText("");
        binding.expenseChart.setDescription(description);
    }
}