import React, { useState, useEffect } from "react";
import SearchForm from "./components/SearchForm";
import PlaceList from "./components/PlaceList";
import GoogleMapComponent from "./components/GoogleMapComponent";
import { LoadScript } from "@react-google-maps/api"; // LoadScript is a component that loads the Google Maps JavaScript API
import placeService from "./services/placeService";

function App() {
  const [apiKey, setApiKey] = useState(null); // State for storing the API key
  const [places, setPlaces] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // Fetch API key from backend on component mount
  useEffect(() => {
    const fetchApiKey = async () => {
      try {
        const response = await fetch("http://localhost:8070/api/google-api-key"); // Make request to your backend to get the API key
        const data = await response.text(); // If it's a plain string response
        setApiKey(data); // Store the API key in the state
      } catch (err) {
        console.error("Error fetching Google API key:", err);
        setError("Unable to fetch Google Maps API key.");
      }
    };
    fetchApiKey();
  }, []); // Empty dependency array ensures this runs only once on component mount

  const handleSearch = async (latitude, longitude, radius) => {
    if (!latitude || !longitude || !radius) {
      setError("Latitude, Longitude, and Radius are required.");
      return;
    }

    setLoading(true);
    setError(null);
    try {
      const response = await placeService.getNearbyPlaces(latitude, longitude, radius);
      if (response && response[0]?.length > 0) {
        setPlaces(response[0]);
      } else {
        setPlaces([]);
      }
    } catch (err) {
      setError("There was an error fetching the places.");
      console.error("API Error:", err);
    } finally {
      setLoading(false);
    }
  };

  if (!apiKey) {
    return <div>Loading API key...</div>; // Show loading message until the API key is fetched
  }

  return (
    <LoadScript googleMapsApiKey={apiKey}>
      <div className="App">
        <div className="container text-center">
          <h1>Nearby Places</h1>
          <SearchForm onSearch={handleSearch} />
          {error && <div className="alert alert-danger">{error}</div>}
          {loading && <div>Loading...</div>}
          {!loading && places.length > 0 && <PlaceList places={places} />}
          {!loading && places.length > 0 && <GoogleMapComponent places={places} />}
        </div>
      </div>
    </LoadScript>
  );
}

export default App;
