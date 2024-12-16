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

    // rate limit settings
    @Bean
    public Bucket bucket() {
        Bandwidth limit = Bandwidth.simple(2, Duration.ofMinutes(1)); // 2 request in 1 minute limit
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }

    // check if request is allowed
    public boolean isRequestAllowed(Bucket bucket) {
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        return probe.isConsumed();
    }
}
