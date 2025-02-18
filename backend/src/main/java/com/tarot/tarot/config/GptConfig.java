package com.tarot.tarot.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * GPT连接配置类
 */
@Component
public class GptConfig {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    Dotenv dotenv = Dotenv.load();

    private   String API_KEY = dotenv.get("GPT_API_KEY");

    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS) // 连接超时：60s
            .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)    // 读取超时：60s
            .writeTimeout(60, java.util.concurrent.TimeUnit.SECONDS)   // 写入超时：60s
            .build();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String getResponse(String prompt) throws IOException {


        // 设置代理
//        String proxyHost = "127.0.0.1";
//        int proxyPort = 7890;          // Clash的http代理端口
//        System.setProperty("http.proxyHost", proxyHost);
//        System.setProperty("http.proxyPort", String.valueOf(proxyPort));
//        System.setProperty("https.proxyHost", proxyHost);
//        System.setProperty("https.proxyPort", String.valueOf(proxyPort));

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("model", "gpt-4o");
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);
        // 创建消息列表
        List<Map<String, String>> messages = new ArrayList<>();
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


            return response.body().string();
        }
    }
    public String extractMessageContent(String response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
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