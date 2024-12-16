package com.example.nearbyplacesapi.model;

import java.util.List;

public class PlaceResponse {

    // status is a string that indicates the status of the request
    private String status;
    // results is a list of PlaceResult objects
    private List<PlaceResult> results;


    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<PlaceResult> getResults() {
        return results;
    }
    public void setResults(List<PlaceResult> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "PlaceResponse{" +
                "status='" + status + '\'' +
                ", results=" + results +
                '}';
    }
}
