import axios from "axios";

const BASE_URL = "http://localhost:8070/places/nearby";

// Function to fetch nearby places from the API
const getNearbyPlaces = async (latitude, longitude, radius) => {
  try {
    // From the API, fetch nearby places based on the latitude, longitude
    const response = await axios.get(
      `${BASE_URL}?latitude=${latitude}&longitude=${longitude}&radius=${radius}`
    );
    return response.data; // Return the data from the response
  } catch (error) {
    // If there is an error, log the error and throw it
    console.error("Error fetching nearby places:", error);
    throw error; 
  }
};

// export the service functions to be used in the components
const placeService = {
  getNearbyPlaces,
};

export default placeService;
