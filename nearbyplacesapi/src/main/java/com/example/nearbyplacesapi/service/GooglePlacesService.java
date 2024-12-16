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
        String uniqueKey = generateQueryKey(latitude, longitude, radius);

        Optional<List<Place>> existingPlaces = placeRepository.findByQueryKey(uniqueKey);

        if (existingPlaces.isPresent() && !existingPlaces.get().isEmpty()) {
            System.out.println("Returning cached places from DB for key: " + uniqueKey);
            return Collections.singletonList(existingPlaces.get());
        }

        String url = String.format(Locale.US,
                "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%.4f,%.4f&radius=%.4f&key=%s",
                latitude, longitude, radius, apiKey);

        System.out.println("Fetching data from Google Places API with URL: " + url);

        PlaceResponse placeResponse = restTemplate.getForObject(url, PlaceResponse.class);

        if (placeResponse != null && "OK".equals(placeResponse.getStatus())) {
            System.out.println("Google Places API Response: " + placeResponse);  // API yanıtını logla

            List<Place> places = placeResponse.getResults().stream().map(result -> {
                Place place = new Place();

                Double placeLatitude = result.getGeometry().getLocation().getLat();
                Double placeLongitude = result.getGeometry().getLocation().getLng();
                place.setLatitude(placeLatitude);
                place.setLongitude(placeLongitude);

                place.setRadius(radius);
                place.setName(result.getName());

                String vicinity = result.getVicinity();
                if (vicinity == null || vicinity.isEmpty()) {
                    vicinity = "Unknown";
                }

                place.setVicinity(vicinity);
                place.setQueryKey(generateQueryKey(placeLatitude, placeLongitude, radius, result.getName())); // QueryKey ile name'i kullan

                return place;
            }).collect(Collectors.toList());

            places.forEach(place -> {
                boolean exists = placeRepository.existsByQueryKey(place.getQueryKey());
                if (!exists) {
                    placeRepository.save(place);
                    System.out.println("Saving place with key: " + place.getQueryKey());
                } else {
                    System.out.println("Place with key " + place.getQueryKey() + " already exists, skipping save.");
                }
            });

            return Collections.singletonList(places);
        } else {
            System.out.println("No response or invalid status from Google Places API.");
        }

        return List.of();
    }

    private String generateQueryKey(Double latitude, Double longitude, Double radius) {
        return String.format(Locale.US, "%.6f:%.6f:%.2f", latitude, longitude, radius);
    }

    private String generateQueryKey(Double latitude, Double longitude, Double radius, String name) {
        if (name == null || name.isEmpty()) {
            name = "Unknown";
        }
        return String.format(Locale.US, "%.6f:%.6f:%.2f:%s", latitude, longitude, radius, name);
    }
}
