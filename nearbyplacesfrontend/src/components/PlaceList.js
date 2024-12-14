import React from "react";
import PlaceCard from "./PlaceCard";

const PlaceList = ({ places }) => {
  return (
    <div className="place-list">
      {places.map((place, index) => (
        <PlaceCard key={index} place={place} />
      ))}
    </div>
  );
};

export default PlaceList;
