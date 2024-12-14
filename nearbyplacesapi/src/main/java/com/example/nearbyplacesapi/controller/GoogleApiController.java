package com.example.nearbyplacesapi.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoogleApiController {

    private final String googleApiKey;

    @Autowired
    public GoogleApiController(String googleApiKey) {
        this.googleApiKey = googleApiKey;
    }

    @GetMapping("/api/google-api-key")
    public String getGoogleApiKey() {
        return googleApiKey;
    }
}

