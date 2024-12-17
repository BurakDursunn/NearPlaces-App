import axios from "axios";

const BASE_URL = process.env.REACT_APP_DEPLOYURL+"/places/nearby";

// Function to fetch nearby places from the API
const getNearbyPlaces = async (latitude, longitude, radius) => {
  try {
    const response = await axios.get(
      `${BASE_URL}?latitude=${latitude}&longitude=${longitude}&radius=${radius}`
    );

    console.log("API Response:", response.data); 

    return response.data; 
  } catch (error) {
    console.error("Error fetching nearby places:", error); 
    throw error;
  }
};


// export the service functions to be used in the components
const placeService = {
  getNearbyPlaces,
};

export default placeService;
