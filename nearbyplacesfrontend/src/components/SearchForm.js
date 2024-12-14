import React, { useState } from 'react';
import { Button, Form, Container, Row, Col } from 'react-bootstrap';

const SearchForm = ({ onSearch }) => {
  const [latitude, setLatitude] = useState('');
  const [longitude, setLongitude] = useState('');
  const [radius, setRadius] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    onSearch(latitude, longitude, radius);
  };

  return (
    <Container className="d-flex justify-content-center align-items-center vh-100">
      <Row className="w-100 justify-content-center">
        <Col md={8} lg={6} xl={5} className="p-4 border rounded shadow-lg bg-light">
          <h3 className="text-center mb-4 font-weight-bold">Search Nearby Places</h3>
          
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="latitude" className="mb-3">
              <Form.Label>Latitude:</Form.Label>
              <Form.Control
                type="number"
                step="any"
                value={latitude}
                onChange={(e) => setLatitude(e.target.value)}
                required
                placeholder="Enter Latitude"
                className="form-control-lg"
              />
            </Form.Group>

            <Form.Group controlId="longitude" className="mb-3">
              <Form.Label>Longitude:</Form.Label>
              <Form.Control
                type="number"
                step="any"
                value={longitude}
                onChange={(e) => setLongitude(e.target.value)}
                required
                placeholder="Enter Longitude"
                className="form-control-lg"
              />
            </Form.Group>

            <Form.Group controlId="radius" className="mb-4">
              <Form.Label>Radius (in meters):</Form.Label>
              <Form.Control
                type="number"
                value={radius}
                onChange={(e) => setRadius(e.target.value)}
                required
                placeholder="Enter Radius"
                className="form-control-lg"
              />
            </Form.Group>

            <Button variant="primary" type="submit" className="w-100 py-2" size="lg">
              Search
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default SearchForm;
