package com.tarot.tarot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.*;
import org.springframework.stereotype.Component;


import com.fasterxml.jackson.core.type.TypeReference;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DeepSeekConfig {
    Dotenv dotenv = Dotenv.load();
    private static final String API_URL = "https://api.deepseek.com/chat/completions";
    private String API_KEY = dotenv.get("API_KEY");
    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            .build();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String getResponse(String prompt) throws IOException {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "deepseek-chat");
        List<Map<String, String>> messages = new ArrayList<>();

        // system message
//        Map<String, String> systemMessage = new HashMap<>();
//        String systemPrompt = """
//                 ## Role: 塔罗牌咨询师
//                 ## Goals: 根据用户给出的具体问题，结合塔罗牌结果，提供深刻且人性化的塔罗解读。解读内容需帮助用户理解当前的情感、生活、工作、学习、健康、财运等状态，并展望未来发展可能性，同时提供温暖且实用的建议。
//                """;
//        systemMessage.put("role", "system");
//        systemMessage.put("content", systemPrompt);
//        messages.add(systemMessage);

        // user message
        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);
        messages.add(userMessage);

        requestBody.put("messages", messages);

        String jsonBody = objectMapper.writeValueAsString(requestBody);

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(jsonBody, MediaType.get("application/json")))
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("DeepSeek API error: " + response);
            }
            return response.body().string();
        }
    }

    public String extractMessageContent(String response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(response);
        Map<String, Object> responseMap = objectMapper.readValue(response, new TypeReference<Map<String, Object>>() {
        });
        List<Map<String, Object>> choices = (List<Map<String, Object>>) responseMap.get("choices");
        if (choices != null && !choices.isEmpty()) {
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            return (String) message.get("content");
        }
        throw new IOException("No message content found in response");
    }

}
