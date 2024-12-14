package com.example.nearbyplacesapi.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleApiConfig {

    @Value("${google.api.key}")
    private String googleApiKey;

    @Bean
    public String googleApiKey() {
        return googleApiKey;
    }
}
