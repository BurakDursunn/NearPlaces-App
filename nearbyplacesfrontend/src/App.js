import React, { useState } from "react";
import SearchForm from "./components/SearchForm";
import PlaceList from "./components/PlaceList";
import GoogleMapComponent from "./components/GoogleMapComponent";
import { LoadScript } from "@react-google-maps/api"; 
import placeService from "./services/placeService";

function App() {
  const apiKey = process.env.REACT_APP_API_KEY_GOOGLE;

  const [places, setPlaces] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

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
