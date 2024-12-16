package com.example.nearbyplacesapi.repository;

import com.example.nearbyplacesapi.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<List<Place>> findByLatitudeAndLongitudeAndRadius(Double latitude, Double longitude, Double radius);

    Optional<List<Place>> findByQueryKey(String queryKey);

    boolean existsByQueryKey(String queryKey);
}



