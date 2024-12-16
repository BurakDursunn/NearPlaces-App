package com.example.nearbyplacesapi.model;

// PlaceResult class that represents the result of a place search
public class PlaceResult {

    // Geometry object that contains the location of the place in latitude and longitude
    private Geometry geometry;
    private String name;
    private String vicinity;

    public Geometry getGeometry() {
        return geometry;
    }
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
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

    // Geometry inner class for location information
    public static class Geometry {
        private Location location;

        public Location getLocation() {
            return location;
        }
        public void setLocation(Location location) {
            this.location = location;
        }
    }

    // Location inner class for latitude and longitude information
    public static class Location {
        private Double lat;
        private Double lng;

        public Double getLat() {
            return lat;
        }
        public void setLat(Double lat) {
            this.lat = lat;
        }
        public Double getLng() {
            return lng;
        }
        public void setLng(Double lng) {
            this.lng = lng;
        }
    }
}

