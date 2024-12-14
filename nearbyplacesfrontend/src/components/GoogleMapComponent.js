import React from "react";
import { GoogleMap, Marker } from "@react-google-maps/api";

const GoogleMapComponent = ({ places }) => {
  const defaultCenter = places.length > 0
    ? { lat: parseFloat(places[0].latitude), lng: parseFloat(places[0].longitude) }
    : { lat: 0, lng: 0 };

  // Kırmızı marker simgesi
  const redMarkerIcon = {
    url: "http://maps.google.com/mapfiles/ms/icons/red-dot.png", // Kırmızı marker ikonu URL
    scaledSize: { width: 40, height: 40 }, // İkon boyutunu ayarla (isteğe bağlı)
  };

  return (
    <GoogleMap
      key={places.length} // Haritanın yeniden render edilmesi için benzersiz bir key
      mapContainerStyle={{ width: "100%", height: "400px" }} // Harita boyutu
      center={defaultCenter} // Harita merkezi
      zoom={14} // Zoom seviyesi
    >
      {places.map((place, index) => (
        <Marker
          key={index} // Benzersiz anahtar
          position={{ lat: parseFloat(place.latitude), lng: parseFloat(place.longitude) }} // Marker pozisyonu
          icon={redMarkerIcon} // Kırmızı marker ikonunu kullan
          label={{
            text: place.name, // Marker'ın etiketi
            fontSize: "114px",
            color: "blue",
          }}
        />
      ))}
    </GoogleMap>
  );
};

export default GoogleMapComponent;
