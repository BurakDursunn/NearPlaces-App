package com.example.nearbyplacesapi.controller;

import com.example.nearbyplacesapi.exception.RateLimitExceededException;
import com.example.nearbyplacesapi.service.GooglePlacesService;
import com.example.nearbyplacesapi.config.RateLimitConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlacesController {

    // Inject GooglePlacesService and RateLimitConfig
    private final GooglePlacesService googlePlacesService;
    private final RateLimitConfig rateLimitConfig;


    public PlacesController(GooglePlacesService googlePlacesService, RateLimitConfig rateLimitConfig) {
        this.googlePlacesService = googlePlacesService;
        this.rateLimitConfig = rateLimitConfig;
    }

    @GetMapping("/places/nearby")
    public List<Object> getNearbyPlaces(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam Double radius) {

        // Check if the request is allowed
        if (!rateLimitConfig.isRequestAllowed(rateLimitConfig.bucket())) {
            throw new RateLimitExceededException("Rate limit exceeded. Please try again later.");
        }

        // Call GooglePlacesService to get nearby places
        return googlePlacesService.getNearbyPlaces(latitude, longitude, radius);
    }
}
