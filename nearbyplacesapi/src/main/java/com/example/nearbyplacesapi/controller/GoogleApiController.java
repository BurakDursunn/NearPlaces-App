package com.example.nearbyplacesapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoogleApiController {

    @Value("${google.maps.api.key}")
    private String googleApiKey;

    @GetMapping("/api/google-api-key")
    public ResponseEntity<String> getGoogleApiKey() {
        return ResponseEntity.ok(googleApiKey);
    }
}

