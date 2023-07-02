package com.example.accounting.utils.chatgpt;

import com.example.accounting.model.room.bean.PostInfo;

public class Message
{
    public static String getSuggestionMessage(PostInfo postInfo)
    {
        return String.format(
                "{'salary':'%s','bonus':'%s','dining':'%s','transportation':'%s','shopping':'%s','entertainment':'%s','investment':'%s'}",
                postInfo.getSalary(),
                postInfo.getBonus(),
                postInfo.getDining(),
                postInfo.getTransportation(),
                postInfo.getShopping(),
                postInfo.getEntertainment(),
                postInfo.getInvestment()
        );
    }
}