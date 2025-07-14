package com.fooddelivery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // allow all endpoints
                .allowedOrigins("http://localhost:3000", "http://localhost:3001") // both React ports
                .allowedMethods("*") // GET, POST, etc.
                .allowedHeaders("*");
    }
}
