package com.example.nearbyplacesapi.exception;

public class RateLimitExceededException extends RuntimeException {

    // Message to be displayed when rate limit is exceeded
    public RateLimitExceededException(String message) {
        super(message);
    }
}
