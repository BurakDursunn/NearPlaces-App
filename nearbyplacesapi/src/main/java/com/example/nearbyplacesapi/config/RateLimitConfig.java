package com.example.nearbyplacesapi.config;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimitConfig {

    @Bean
    public Bucket bucket() {
        Bandwidth limit = Bandwidth.simple(2, Duration.ofMinutes(1)); // 1 dakika içinde 100 istek
        return Bucket.builder()
                .addLimit(limit)  // Limitleri buraya ekliyoruz
                .build();
    }

    public boolean isRequestAllowed(Bucket bucket) {
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1); // 1 istek için
        return probe.isConsumed(); // Eğer istek tükendi ise, rate limit aşılmadı
    }
}
