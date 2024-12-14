import React from "react";
import { GoogleMap, Marker } from "@react-google-maps/api";


const GoogleMapComponent = ({ places }) => {
  const defaultCenter = places.length > 0
    ? { lat: parseFloat(places[0].latitude), lng: parseFloat(places[0].longitude) }
    : { lat: 0, lng: 0 };

  return (
    <GoogleMap
      key={places.length}
      mapContainerStyle={{ width: "100%", height: "400px" }}
      center={defaultCenter}
      zoom={14}
    >
      {places.map((place, index) => (
        <Marker
          key={index}
          position={{ lat: parseFloat(place.latitude), lng: parseFloat(place.longitude) }}
          label={{
            text: place.name,
            fontSize: "14px",
            color: "blue",
          }}
        />
      ))}
    </GoogleMap>
  );
};

export default GoogleMapComponent;
