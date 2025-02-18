package com.tarot.tarot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // This will allow any endpoint to be accessed from any origin
                .allowedOrigins("http://127.0.0.1:55552")
                .allowedOrigins("http://43.138.65.118:55552")
                .allowedOrigins("http://18.224.70.95:55552")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}


