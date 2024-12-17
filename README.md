### Nearby Places API

#### **Overview**
Nearby Places is a full-stack application with a Spring Boot backend, React frontend and PostgreSQL database. It helps users discover places near their location by integrating with the Google Places API. The backend includes features like rate limiting, caching with PostgreSQL, and integrates with Google Maps API for the frontend.

<img width="800" height: auto alt="Ekran Resmi 2024-12-18 00 50 18" src="https://github.com/user-attachments/assets/57ce4a12-7a6e-414d-bb04-079023491f40" />
<br>
<img width="800" height: auto alt="Ekran Resmi 2024-12-18 00 50 33" src="https://github.com/user-attachments/assets/78545324-8535-43e8-884b-1304779cecbb" />
<br>
<img width="800" height: auto alt="Ekran Resmi 2024-12-18 00 50 46" src="https://github.com/user-attachments/assets/4a9dcda4-e8a4-4599-8ace-89dd571ab43b" />

### **Backend**

#### **Features**
- **Nearby Places Search**: Query places near a location using latitude, longitude, and radius.
- **Caching**: PostgreSQL stores cached data to minimize redundant API calls.
- **Rate Limiting**: Implemented with Bucket4j to control the number of requests.
- **CORS**: Configured for cross-origin requests from frontend apps.
- **Exception Handling**: Global handler for meaningful error responses.

#### **Technologies**
- **Java 17**
- **Spring Boot**
- **PostgreSQL**
- **Bucket4j**
- **RestTemplate** for API calls



### **Getting Started**

1. **Clone the Repository**:  
   Clone the project from GitHub and navigate to the backend directory.  
   ```bash
   git clone https://github.com/BurakDursunn/NearPlaces-App.git
   cd nearbyplacesapi
   ```

2. **Database Setup**:  
   - Create a new database (e.g., `nearbyplaces`) in your PostgreSQL server.

3. **Set Up Google API Key**:  
   - Obtain your Google API Key from the [Google Cloud Console](https://console.cloud.google.com/).

4. **Export Environment Variables**:  
   Export the required environment variables for the database and Google API key:  
   ```bash
   export DB_URL=your_database_url  
   export DB_USERNAME=your_database_username  
   export DB_PASSWORD=your_database_password  
   export GOOGLE_API_KEY=your_google_api_key  
   ```

5. **Run the Application**:  
   Start the Spring Boot application using Maven:  
   ```bash
   mvn spring-boot:run
   ```

#### **API Endpoints**

- **GET /places/nearby**: Fetch nearby places.
  - Parameters: `latitude`, `longitude`, `radius`
  - Example:
    ```bash
    GET http://localhost:8070/places/nearby?latitude=37.7749&longitude=-122.4194&radius=500
    ```

---

### **Frontend**

#### **Installation**

1. **Clone Repository**:
   ```bash
   git clone https://github.com/BurakDursunn/NearPlaces-App.git
   cd nearbyplacesfrontend
   ```

2. **Install Dependencies**:
   ```bash
   npm install
   ```

3. **Run App**:
   ```bash
   npm start
   ```

4. **Build for Production**:
   ```bash
   npm run build
   ```

---

### **Database**

The **Place** table stores cached Google Places data with the following structure:

| **Column**       | **Type**            | **Description**                                                |
|------------------|---------------------|----------------------------------------------------------------|
| `id`             | `BIGINT             | Primary key.                                                  |
| `latitude`       | `DOUBLE`            | Latitude of the requested location.                           |
| `longitude`      | `DOUBLE`            | Longitude of the requested location.                          |
| `radius`         | `DOUBLE`            | Search radius in meters.                                      |
| `query_key`      | `VARCHAR(255)`      | Unique identifier for the query.                              |
| `name`           | `VARCHAR(255)`      | Place name.                                                   |
| `vicinity`       | `VARCHAR(255)`      | Vicinity of the place.                                        |
|------------------|---------------------|----------------------------------------------------------------|


---

### **Deployment**

1. **Backend**: Dockerize and deploy to **Render** with environment variables.
2. **Frontend**: Deploy React app to **Render** as a static site.

---

**Summary**: Full-stack application for finding nearby places using Google Places API, with caching in PostgreSQL, rate limiting, and easy deployment to Render.com.
