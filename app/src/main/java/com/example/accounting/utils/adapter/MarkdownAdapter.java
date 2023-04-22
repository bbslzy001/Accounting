package com.example.accounting.utils.adapter;

import com.example.accounting.base.BaseApplication;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MarkdownAdapter
{
    public static String readMarkdownFromAssets(String fileName)
    {
        String markdown = "";

        try
        {
            InputStream inputStream = BaseApplication.getContext().getAssets().open(fileName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            markdown = new String(buffer, StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return markdown;
    }
}
