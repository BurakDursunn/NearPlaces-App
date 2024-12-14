import React from 'react';
import { Card } from 'react-bootstrap';

const PlaceCard = ({ place }) => {
  return (
    <Card className="place-card shadow-sm rounded">
      <Card.Body>
        <Card.Title className="text-center text-primary">{place.name}</Card.Title>
        <Card.Text>
          <strong>Vicinity:</strong> {place.vicinity}
        </Card.Text>
        <Card.Text>
          <strong>Coordinates:</strong> Latitude: {place.latitude}, Longitude: {place.longitude}
        </Card.Text>
      </Card.Body>
    </Card>
  );
};

export default PlaceCard;
