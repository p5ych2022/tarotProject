package com.tarot.tarot.config;

import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.zhipu.ZhipuAiChatModel;
import dev.langchain4j.model.zhipu.chat.ChatCompletionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.cdimascio.dotenv.Dotenv;



import java.util.Collections;
import java.util.List;
import java.time.Duration;


@Configuration
public class ZhipuAiChatModelConfig {
    @Bean
    public ZhipuAiChatModel zhipuAiChatModel() {

        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("ZHIPU_API_KEY");
        // System.out.println("Loaded ZHIPU_API_KEY: " + apiKey);

        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("Failed to load API key from .env file");
        }


//        String baseUrl = "https://open.bigmodel.cn/"; // 或者您的自定义URL
//        Double temperature = 0.0;
//        Double topP = 0.7; // 如果不需要，可以设置为 null
//        String model = ChatCompletionModel.GLM_4_FLASH.toString(); // 或者您需要的模型
//        List<String> stops = null; // 停止词列表，如果不需要，可以设置为 null
//        Integer maxRetries = 3;
//        Integer maxTokens = 2048;
//        Boolean logRequests = false;
//        Boolean logResponses = false;
//        List<ChatModelListener> listeners = Collections.emptyList(); // 如果有监听器，可以添加
//        Duration callTimeout = Duration.ofSeconds(30);
//        Duration connectTimeout = Duration.ofSeconds(10);
//        Duration readTimeout = Duration.ofSeconds(30);
//        Duration writeTimeout = Duration.ofSeconds(30);
//
//
//            ZhipuAiChatModel zhipuAiChatModel = new ZhipuAiChatModel(
//                    baseUrl,
//                    apiKey,
//                    temperature,
//                    topP,
//                    model,
//                    stops,
//                    maxRetries,
//                    maxTokens,
//                    logRequests,
//                    logResponses,
//                    listeners,
//                    callTimeout,
//                    connectTimeout,
//                    readTimeout,
//                    writeTimeout
//            );


            return new ZhipuAiChatModel(null, apiKey, 0.0, 0.70, null, null, 3, 2048, null, null, null);
    }
}
