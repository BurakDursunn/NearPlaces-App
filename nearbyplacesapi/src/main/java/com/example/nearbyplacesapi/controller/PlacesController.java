package com.example.nearbyplacesapi.controller;

import com.example.nearbyplacesapi.service.GooglePlacesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlacesController {

    private final GooglePlacesService googlePlacesService;

    public PlacesController(GooglePlacesService googlePlacesService) {
        this.googlePlacesService = googlePlacesService;
    }

    @GetMapping("/places/nearby")
    public List<Object> getNearbyPlaces(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam Double radius) {

        return googlePlacesService.getNearbyPlaces(latitude, longitude, radius);
    }
}
