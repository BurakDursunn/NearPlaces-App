import React from 'react';
import PlaceCard from './PlaceCard';
import { Row, Col } from 'react-bootstrap';

const PlaceList = ({ places }) => {
  return (
    <div className="container py-5">
      <Row className="g-4">
        {places.map((place, index) => (
          <Col key={index} xs={12} sm={6} md={4} lg={3}>
            <PlaceCard place={place} />
          </Col>
        ))}
      </Row>
    </div>
  );
};

export default PlaceList;
