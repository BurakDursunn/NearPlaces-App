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

    // inject RestTemplate and PlaceRepository beans into the service
    private final RestTemplate restTemplate;
    private final PlaceRepository placeRepository;

    // constructor injection
    public GooglePlacesService(RestTemplate restTemplate, PlaceRepository placeRepository) {
        this.restTemplate = restTemplate;
        this.placeRepository = placeRepository;
    }

    // getNearbyPlaces method to fetch nearby places from Google Places API and save them
    // to the database if they don't exist already and return the list of places.
    public List<Object> getNearbyPlaces(Double latitude, Double longitude, Double radius) {

        // generate a unique key for the query
        String uniqueKey = generateQueryKey(latitude, longitude, radius);

        // check if the places already exist in the database
        Optional<List<Place>> existingPlaces = placeRepository.findByQueryKey(uniqueKey);

        // if the places exist in the database, return them
        if (existingPlaces.isPresent() && !existingPlaces.get().isEmpty()) {
            System.out.println("Returning cached places from DB for key: " + uniqueKey);
            return Collections.singletonList(existingPlaces.get());
        }

        // if the places don't exist in the database, fetch them from Google Places API and save them
        // to the database and we use Locale.US to format the URL with the correct decimal separator.
        String url = String.format(Locale.US,
                "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%.4f,%.4f&radius=%.4f&key=%s",
                latitude, longitude, radius, apiKey);

        System.out.println("Fetching data from Google Places API with URL: " + url);

        // fetch the places from Google Places API
        PlaceResponse placeResponse = restTemplate.getForObject(url, PlaceResponse.class);

        // if the response is not null and the status is OK, log the API response and map the results to Place objects
        if (placeResponse != null && "OK".equals(placeResponse.getStatus())) {
            System.out.println("Google Places API Response: " + placeResponse);  // API yanıtını logla

            // map the results to Place objects and save them to the database
            List<Place> places = placeResponse.getResults().stream().map(result -> {
                Place place = new Place();

                // set the latitude, longitude, radius, name, vicinity, and query key for the place
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

                // set the vicinity and query key for the place
                place.setVicinity(vicinity);
                place.setQueryKey(generateQueryKey(placeLatitude, placeLongitude, radius, result.getName())); // QueryKey ile name'i kullan

                // return the place
                return place;
            }).collect(Collectors.toList());

            // save the places to the database and log the places that are saved
            places.forEach(place -> {
                boolean exists = placeRepository.existsByQueryKey(place.getQueryKey());
                if (!exists) {
                    placeRepository.save(place);
                    System.out.println("Saving place with key: " + place.getQueryKey());
                } else {
                    System.out.println("Place with key " + place.getQueryKey() + " already exists, skipping save.");
                }
            });

            // return the list of places
            return Collections.singletonList(places);
        } else {
            System.out.println("No response or invalid status from Google Places API.");
        }

        // return an empty list if the response is null or the status is not OK
        return List.of();
    }

    // generateQueryKey method to generate a unique key for the query based on the latitude, longitude, and radius
    private String generateQueryKey(Double latitude, Double longitude, Double radius) {
        return String.format(Locale.US, "%.6f:%.6f:%.2f", latitude, longitude, radius);
    }

    // generateQueryKey method to generate a unique key for the query based on the latitude, longitude, radius, and name
    private String generateQueryKey(Double latitude, Double longitude, Double radius, String name) {
        if (name == null || name.isEmpty()) {
            name = "Unknown";
        }
        return String.format(Locale.US, "%.6f:%.6f:%.2f:%s", latitude, longitude, radius, name);
    }
}
