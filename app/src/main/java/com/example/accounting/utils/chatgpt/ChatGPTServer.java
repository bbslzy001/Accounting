package com.example.accounting.utils.chatgpt;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatGPTServer
{
    private static final String API_KEY = "";
    private static final String API_URL = "";

    public static void getResponse(String prompt, String message, final OnPostInfoReceivedListener listener)
    {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Handler mainHandler = new Handler(Looper.getMainLooper());

        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> message1 = new HashMap<>();
        message1.put("role", "system");
        message1.put("content", prompt);
        messages.add(message1);
        Map<String, String> message2 = new HashMap<>();
        message2.put("role", "user");
        message2.put("content", message);
        messages.add(message2);

        // Constructing the reqBody object
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("messages", messages);
        reqBody.put("temperature", 0.8);
        reqBody.put("top_p", 0.95);
        reqBody.put("frequency_penalty", 0);
        reqBody.put("presence_penalty", 0);
        reqBody.put("max_tokens", 800);
        reqBody.put("stop", null);

        Gson gson = new Gson();
        String jsonReqBody = gson.toJson(reqBody);

        RequestBody requestBody = RequestBody.create(jsonReqBody, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Content-Type", "application/json")
                .addHeader("api-key", API_KEY)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e)
            {
                mainHandler.post(() -> listener.onError(e));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response)
            {
                {
                    try
                    {
                        if (response.isSuccessful())
                        {
                            String jsonString = Objects.requireNonNull(response.body()).string();
                            JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
                            JsonArray choicesArray = jsonObject.getAsJsonArray("choices");
                            if (choicesArray != null && choicesArray.size() > 0)
                            {
                                JsonObject choiceObject = choicesArray.get(0).getAsJsonObject();
                                if (choiceObject != null)
                                {
                                    JsonObject messageObject = choiceObject.getAsJsonObject("message");
                                    if (messageObject != null)
                                    {
                                        mainHandler.post(() -> listener.onPostInfoReceived(messageObject.get("content").getAsString()));
                                    }
                                }
                            }
                            mainHandler.post(() -> listener.onError(new IOException("Unexpected response format.")));
                        }
                        else
                        {
                            mainHandler.post(() -> listener.onError(new IOException("Unexpected response code: " + response.code())));
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public interface OnPostInfoReceivedListener
    {
        void onPostInfoReceived(String response);

        void onError(Exception e);
    }
}