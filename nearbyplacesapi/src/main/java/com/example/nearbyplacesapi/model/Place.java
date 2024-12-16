package com.example.nearbyplacesapi.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Place {

    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Columns
    private Double latitude;
    private Double longitude;
    private Double radius;
    private String name;
    private String vicinity;

    // queryKey is a unique key that is used to identify a place
    @Column(unique = true)
    private String queryKey;

    public String getQueryKey() {
        return queryKey;
    }

    public void setQueryKey(String queryKey) {
        this.queryKey = queryKey;
    }

    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Double getRadius() {
        return radius;
    }
    public void setRadius(Double radius) {
        this.radius = radius;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getVicinity() {
        return vicinity;
    }
    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    // We use this method because we want to compare the objects based on the queryKey
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(latitude, place.latitude) &&
                Objects.equals(longitude, place.longitude) &&
                Objects.equals(radius, place.radius);
    }

    // We use this method because we want to compare the objects based on the queryKey
    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, radius);
    }
}
