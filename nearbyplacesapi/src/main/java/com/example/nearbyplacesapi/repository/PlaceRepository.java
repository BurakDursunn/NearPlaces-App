package com.example.nearbyplacesapi.repository;

import com.example.nearbyplacesapi.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<List<Place>> findByLatitudeAndLongitudeAndRadius(Double latitude, Double longitude, Double radius);

    // findByQueryKey method that returns a list of places based on the queryKey
    Optional<List<Place>> findByQueryKey(String queryKey);

    // existsByQueryKey method that returns a boolean value based on the existence of a place with the given queryKey
    boolean existsByQueryKey(String queryKey);
}



