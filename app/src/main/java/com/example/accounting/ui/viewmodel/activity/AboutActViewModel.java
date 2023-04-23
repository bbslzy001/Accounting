package com.example.accounting.ui.viewmodel.activity;

import android.text.Spanned;

import androidx.lifecycle.MutableLiveData;

import com.example.accounting.base.BaseApplication;
import com.example.accounting.base.BaseActivityViewModel;
import com.example.accounting.utils.adapter.MarkdownAdapter;

import io.noties.markwon.Markwon;

public class AboutActViewModel extends BaseActivityViewModel
{
    private final MutableLiveData<Spanned> markdownToHtml = new MutableLiveData<>();

    public AboutActViewModel()
    {
        super();

        Markwon markwon = Markwon.create(BaseApplication.getContext());
        String markdown = MarkdownAdapter.readMarkdownFromAssets("about_us.md");
        Spanned spanned = markwon.toMarkdown(markdown);
        markdownToHtml.setValue(spanned);
    }

    public MutableLiveData<Spanned> getMarkdownToHtml()
    {
        return markdownToHtml;
    }
}