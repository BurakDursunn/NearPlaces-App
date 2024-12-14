import axios from "axios";

const BASE_URL = "http://localhost:8070/places/nearby";

// Function to fetch nearby places from the API
const getNearbyPlaces = async (latitude, longitude, radius) => {
  try {
    const response = await axios.get(
      `${BASE_URL}?latitude=${latitude}&longitude=${longitude}&radius=${radius}`
    );

    console.log("API Response:", response.data); // Gelen veriyi kontrol edin

    return response.data; // Veriyi döndür
  } catch (error) {
    console.error("Error fetching nearby places:", error); // Hata durumunda log
    throw error;
  }
};


// export the service functions to be used in the components
const placeService = {
  getNearbyPlaces,
};

export default placeService;
