package com.tarot.tarot.config;

import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


import com.volcengine.ark.runtime.model.completion.chat.ChatCompletionRequest;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessage;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;

import com.volcengine.ark.runtime.service.ArkService;
import org.springframework.stereotype.Component;


@Component
public class DeepSeekConfig {
    Dotenv dotenv = Dotenv.load();
    String apiKey = dotenv.get("HS_API_KEY");
    private static final String MODEL_NAME = "ep-20250225180056-t9tl8";
    private final ArkService service = ArkService.builder()
                .apiKey(apiKey)
                .timeout(Duration.ofSeconds(120))
                .connectTimeout(Duration.ofSeconds(20))
                .retryTimes(2)
                .build();
    // 获取与 DeepSeek 大模型的响应
    public String getResponse(String prompt) throws IOException {
        // 构建消息列表
        List<ChatMessage> messages = new ArrayList<>();

        // 系统消息（可选，若不需要可以注释掉）
        ChatMessage systemMessage = ChatMessage.builder()
                .role(ChatMessageRole.SYSTEM)
                .content("你是一个塔罗牌咨询师，擅长为用户提供详细的塔罗牌解读。")
                .build();
        messages.add(systemMessage);

        // 用户消息
        ChatMessage userMessage = ChatMessage.builder()
                .role(ChatMessageRole.USER)
                .content(prompt)
                .build();
        messages.add(userMessage);

        // 构建请求
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model(MODEL_NAME)
                .messages(messages)
                .build();

        // 调用火山引擎 API 获取响应
        Object result = service.createChatCompletion(chatCompletionRequest)
                .getChoices()
                .stream()
                .map(choice -> choice.getMessage().getContent())  // 提取每个消息的内容
                .findFirst()  // 获取第一个结果（如果有多个，可以根据需求修改）
                .orElse("No content found");// 使用换行符连接所有内容
        return (String) result;

    }
}



