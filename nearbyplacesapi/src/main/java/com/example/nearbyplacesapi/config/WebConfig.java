package com.example.nearbyplacesapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // for static resources like HTML, CSS, JS
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");  // Static kaynaklar için path

        // for resources like images, etc.
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/resources/");  // Alternatif bir dizin kullanıyorsanız
    }
}

