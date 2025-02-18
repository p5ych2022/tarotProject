package com.tarot.tarot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DeepSeek连接配置类
 */
@Component
public class DeepSeekConfig_2 {
    private static final String API_URL = "https://api.deepseek.com/chat/completions";
    private static final String API_KEY = "sk-e38fab8470de4d87b606797e793aa4d0";
    public List<Map<String, String>> messages = new ArrayList<>();
    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS) // 连接超时：60s
            .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)    // 读取超时：60s
            .writeTimeout(60, java.util.concurrent.TimeUnit.SECONDS)   // 写入超时：60s
            .build();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String getResponse(String prompt) throws IOException {

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("model", "deepseek-chat");
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);
// 创建消息列表


        messages.add(message);
        requestBody.put("messages", messages);
        String jsonBody = objectMapper.writeValueAsString(requestBody);

        // 构建HTTP请求
        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(jsonBody, MediaType.get("application/json")))
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("error: " + response);
            }
            String responseBody = response.body().string();
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);
            // 提取 content
            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseMap.get("choices");
            if (choices != null && !choices.isEmpty()) {
                Map<String, String> ansMap = (Map<String, String>) choices.get(0).get("message");
                messages.add(ansMap);
                return ansMap.get("content").toString();
            } else {
                return "没有获取到内容";
            }

        }
    }

}