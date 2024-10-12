package com.tarot.tarot.config;

import dev.langchain4j.model.zhipu.ZhipuAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

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

        return new ZhipuAiChatModel(null, apiKey, 0.0, 0.70, null, null, 3,
                2048, null, null, null);
    }
}
