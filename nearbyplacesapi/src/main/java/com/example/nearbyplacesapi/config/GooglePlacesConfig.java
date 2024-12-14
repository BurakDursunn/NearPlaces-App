package com.example.nearbyplacesapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GooglePlacesConfig {

    @Bean
    public RestTemplate restTemplate() {
        // create a new RestTemplate bean
        return new RestTemplate();
    }
}

