package com.example.nearbyplacesapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // to give permission to frontend url
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**") // to give permission all url
                .allowedOrigins("http://localhost:3000") // frontend url
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // allowed methods
                .allowedHeaders("*") // allowed headers
                .allowCredentials(true)
                .maxAge(3600); // caching time
    }
}
