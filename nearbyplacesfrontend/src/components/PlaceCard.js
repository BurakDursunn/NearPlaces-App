import React from 'react';
import { Card, CardContent, Typography, Box } from '@mui/material';

// style object for card component
const cardStyle = {
  maxWidth: 345,
  width: '100%', 
  minHeight: 350, 
  
  borderRadius: '16px',
  boxShadow: '0 4px 12px rgba(0, 0, 0, 0.15)',
  transition: 'transform 0.3s ease, box-shadow 0.3s ease', 
  '&:hover': {
    transform: 'scale(1.05)', 
    boxShadow: '0 12px 24px rgba(0, 0, 0, 0.2)', 
  },
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'space-between', 
  padding: '20px', 
  boxSizing: 'border-box',
  backgroundColor: '#fff', 
};

const titleStyle = {
  fontWeight: '600',
  color: '#1976d2',
  fontSize: '1.5rem',
  marginBottom: '12px', 
  textAlign: 'center',
};

const contentStyle = {
  color: '#555', 
  fontSize: '1rem',
  lineHeight: '1.5', 
  marginBottom: '8px',
};

const PlaceCard = ({ place }) => {
  return (
    // Card component for each place 
    <Card sx={cardStyle}>
      <CardContent>
        {/* Başlık */}
        <Typography
  variant="h5"
  sx={{
    ...titleStyle,
    display: "-webkit-box",       // Flexbox tabanlı bir kutu modeli
    WebkitBoxOrient: "vertical",  // Kutu yönü dikey
    WebkitLineClamp: 3,           // Maksimum 2 satır
    overflow: "hidden",           // Taşan kısmı gizler
  }}
>
  {place.name}
</Typography>

        {/* Vicinity */}
        <Box mt={2}>
          <Typography variant="body2" sx={contentStyle}>
            <strong>Vicinity:</strong> {place.vicinity}
          </Typography>
        </Box>

        {/* Koordinatlar */}
        <Box mt={2}>
          <Typography variant="body2" sx={contentStyle}>
              <strong>Coordinates:</strong>
              <br />
              Latitude: <strong>{place.latitude.toFixed(5)}</strong>,  <br />Longitude: <strong>{place.longitude.toFixed(5)}</strong>
          </Typography>

        </Box>
      </CardContent>
    </Card>
  );
};

export default PlaceCard;
