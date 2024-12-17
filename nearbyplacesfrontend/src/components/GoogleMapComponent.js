import React from "react";
import { GoogleMap, MarkerF } from "@react-google-maps/api";


const GoogleMapComponent = ({ places,latitude,longitude }) => {
  if (places.length === 0) {
    return <div>No places available</div>;
  }

  // Default center of the map is the first place
  const defaultCenter = {
    lat: parseFloat(latitude),
    lng: parseFloat(longitude), 
  };

  // Custom marker icon for the map 
  const largeGreenMarkerIcon = {
    url: "https://cdn.pixabay.com/photo/2014/04/03/10/03/google-309739_640.png",
    scaledSize: { width: 30, height: 40 }, 
  };

  console.log("Map Center:", defaultCenter); 

  // Google Map component embedded in the app
  return (
    <GoogleMap
      key={places.length} 
      mapContainerStyle={{ width: "100%", height: "500px" }} 
      center={defaultCenter} 
      zoom={13} 
      disableDefaultUI={true} 
    >
      {places.map((place, index) => {
        const position = {
          lat: parseFloat(place.latitude), 
          lng: parseFloat(place.longitude),
        };

        console.log(`Marker ${index + 1}:`, position); 

        return (
          <React.Fragment key={index}>
           
            {/* Marker */}
            <MarkerF
              position={position} 
              icon={largeGreenMarkerIcon} 
            />
           
          </React.Fragment>
        );
      })}
    </GoogleMap>
  );
};

export default GoogleMapComponent;
