package com.example.nearbyplacesapi.service;

import com.example.nearbyplacesapi.model.Place;
import com.example.nearbyplacesapi.model.PlaceResponse;
import com.example.nearbyplacesapi.model.PlaceResult;
import com.example.nearbyplacesapi.repository.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

class GooglePlacesServiceTest {

    private final PlaceRepository placeRepository = Mockito.mock(PlaceRepository.class);
    private final RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

    private final GooglePlacesService googlePlacesService = new GooglePlacesService(restTemplate, placeRepository);

    @Test
    void testGetNearbyPlaces_WhenPlacesExistInCache() {
        // Arrange
        String queryKey = "37.774900:-122.419400:500.00";
        Place place = new Place();
        place.setQueryKey(queryKey);
        Mockito.when(placeRepository.findByQueryKey(queryKey))
                .thenReturn(Optional.of(Collections.singletonList(place)));

        // Act
        List<Object> result = googlePlacesService.getNearbyPlaces(37.7749, -122.4194, 500.0);

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof List);
        Mockito.verify(placeRepository).findByQueryKey(queryKey);
    }

    @Test
    void testGetNearbyPlaces_WhenPlacesNotInCache() {
        // Arrange
        String queryKey = "37.774900:-122.419400:500.00";
        Mockito.when(placeRepository.findByQueryKey(queryKey))
                .thenReturn(Optional.empty());

        PlaceResponse placeResponse = new PlaceResponse();
        placeResponse.setStatus("OK");

        PlaceResult placeResult = new PlaceResult();
        PlaceResult.Geometry geometry = new PlaceResult.Geometry();
        PlaceResult.Location location = new PlaceResult.Location();
        location.setLat(37.7749);
        location.setLng(-122.4194);
        geometry.setLocation(location);
        placeResult.setGeometry(geometry);
        placeResult.setName("Test Place");
        placeResult.setVicinity("Test Vicinity");

        placeResponse.setResults(Collections.singletonList(placeResult));

        Mockito.when(restTemplate.getForObject(any(String.class), eq(PlaceResponse.class)))
                .thenReturn(placeResponse);

        // Act
        List<Object> result = googlePlacesService.getNearbyPlaces(37.7749, -122.4194, 500.0);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(1, ((List<?>) result.get(0)).size());
        Mockito.verify(placeRepository).findByQueryKey(queryKey);
        Mockito.verify(restTemplate).getForObject(any(String.class), eq(PlaceResponse.class));
    }

}
