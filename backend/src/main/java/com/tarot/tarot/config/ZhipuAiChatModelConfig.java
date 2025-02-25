package com.tarot.tarot.config;

import dev.langchain4j.model.zhipu.ZhipuAiChatModel;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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


        return new ZhipuAiChatModel(null, apiKey, 0.0, 0.92, null, null, 3, 4095, null, null, null);
    }
}
