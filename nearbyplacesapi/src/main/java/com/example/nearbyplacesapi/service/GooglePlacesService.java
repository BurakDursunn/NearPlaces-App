package com.example.nearbyplacesapi.service;

import com.example.nearbyplacesapi.model.Place;
import com.example.nearbyplacesapi.model.PlaceResponse;
import com.example.nearbyplacesapi.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GooglePlacesService {

    @Value("${google.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final PlaceRepository placeRepository;

    public GooglePlacesService(RestTemplate restTemplate, PlaceRepository placeRepository) {
        this.restTemplate = restTemplate;
        this.placeRepository = placeRepository;
    }

    public List<Object> getNearbyPlaces(Double latitude, Double longitude, Double radius) {
        // Google API Request URL
        String url = String.format(Locale.US,
                "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%.4f,%.4f&radius=%.4f&key=%s",
                latitude, longitude, radius, apiKey
        );

        System.out.println("Google API Request URL: " + url);


        // Check if there are places in the database with this latitude, longitude and radius
        Optional<List<Place>> existingPlaces = placeRepository.findByLatitudeAndLongitudeAndRadius(latitude, longitude, radius);

        if (existingPlaces.isPresent() && !existingPlaces.get().isEmpty()) {

            // If there are already places in the database for the same query, return them
            System.out.println("Returning cached places from DB");
            return Collections.singletonList(existingPlaces.get());
        }

        // If it is not in the database, get the data from the API
        PlaceResponse placeResponse = restTemplate.getForObject(url, PlaceResponse.class);

        if (placeResponse != null && placeResponse.getStatus().equals("OK")) {

            // Convert each result to a Place object before saving to the database correctly
            List<Place> places = placeResponse.getResults().stream().map(result -> {
                Place place = new Place();
                place.setLatitude(result.getGeometry().getLocation().getLat());
                place.setLongitude(result.getGeometry().getLocation().getLng());
                place.setRadius(radius);  // Save the radius to the database to check if the same query is made again
                place.setName(result.getName());
                place.setVicinity(result.getVicinity());
                return place;
            }).collect(Collectors.toList());

            // Save the new places to the database
            placeRepository.saveAll(places);

            // Return the places
            return Collections.singletonList(places);
        } else {
            System.out.println("No response or invalid status from Google Places API.");
        }
        // Return an empty list if there is no data
        return List.of();
    }
}
