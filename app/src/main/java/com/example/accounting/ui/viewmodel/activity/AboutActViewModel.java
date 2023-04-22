package com.example.accounting.ui.viewmodel.activity;

import android.text.Spanned;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.accounting.application.MyApplication;
import com.example.accounting.utils.DisplayUtils;
import com.example.accounting.utils.adapter.MarkdownAdapter;

import io.noties.markwon.Markwon;

public class AboutActViewModel extends ViewModel
{
    private final MutableLiveData<Integer> statusHeight = new MutableLiveData<>();
    private final MutableLiveData<Integer> navigationHeight = new MutableLiveData<>();
    private final MutableLiveData<Spanned> markdownToHtml = new MutableLiveData<>();

    public AboutActViewModel()
    {
        statusHeight.setValue(DisplayUtils.getStatusBarHeight());
        navigationHeight.setValue(DisplayUtils.getNavigationBarHeight());

        Markwon markwon = Markwon.create(MyApplication.getContext());
        String markdown = MarkdownAdapter.readMarkdownFromAssets("about_us.md");
        Spanned spanned = markwon.toMarkdown(markdown);
        markdownToHtml.setValue(spanned);
    }

    public LiveData<Integer> getStatusHeight()
    {
        return statusHeight;
    }

    public LiveData<Integer> getNavigationHeight()
    {
        return navigationHeight;
    }

    public MutableLiveData<Spanned> getMarkdownToHtml()
    {
        return markdownToHtml;
    }
}