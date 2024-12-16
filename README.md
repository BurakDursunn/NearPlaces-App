# Nearby Places API

## Overview
Nearby Places is a full-stack application comprising a Spring Boot backend and a React frontend, designed to help users discover places near their location. 
The backend integrates with the Google Places API to fetch real-time location data and includes features like rate limiting and caching with MySQL to optimize 
performance and manage API usage effectively. The React frontend provides an intuitive interface for users to search and view nearby places on an interactive map, 
powered by the Google Maps API. Together, the system ensures a seamless user experience for finding places within a specified radius of given coordinates. 
This project demonstrates a robust combination of backend efficiency and frontend usability for location-based services.


# Nearby Places Backend
The Nearby Places API is a Spring Boot application designed to help users find nearby places based on geographical coordinates (latitude and longitude) 
and a specified search radius. This API integrates with the Google Places API to fetch real-time data about various locations and implements rate limiting 
to manage the number of requests effectively. The application also caches results in a MySQL database to enhance performance and reduce the number of API calls made to Google.

## Features

- **Find Nearby Places**: Users can query for places near a specific location using latitude, longitude, and radius.
- **Caching Mechanism**: The application caches results in a MySQL database to minimize redundant API calls and improve response times.
- **Rate Limiting**: Implements rate limiting using the Bucket4j library to prevent abuse and ensure fair usage of the API.
- **CORS Configuration**: Configured to allow cross-origin requests from specified frontend applications, facilitating integration with web clients.
- **Exception Handling**: Global exception handling to provide meaningful error messages and HTTP status codes for various scenarios.

## Technologies Used

- **Java 17**: The application is built using Java 17, leveraging its features for modern development.
- **Spring Boot**: A powerful framework for building RESTful APIs, providing easy configuration and setup.
- **MySQL**: The database used for storing cached place data, ensuring persistence and quick access.
- **Bucket4j**: A Java library for implementing rate limiting, allowing control over the number of requests.
- **RestTemplate**: A Spring utility for making HTTP requests to external APIs, such as the Google Places API.

## Getting Started

### Prerequisites
Before you begin, ensure you have the following installed:

- **Java 17 or higher**
- **Maven**: For building and managing the project.
- **MySQL Database**: For storing cached data.
- **Google Places API Key**: Required for accessing the Google Places API.

### Installation Steps
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/nearbyplacesapi.git
   cd nearbyplacesapi
   ```

2. **Set Up the MySQL Database**:
   - Create a new database (e.g., `nearbyplaces`).
   - Update the `src/main/resources/application.properties` file with your database connection details.

3. **Add Your Google Places API Key**:
   - In the `application.properties` file, set your Google API key:
     ```properties
     google.api.key=YOUR_GOOGLE_API_KEY
     ```

4. **Build the Application**:
   ```bash
   mvn clean install
   ```

5. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

### Configuration

The application can be configured through the `src/main/resources/application.properties` file. Key configurations include:

- **Database URL**: Set the URL for your MySQL database.
- **Database Credentials**: Set the username and password for your database.
- **Google API Key**: Set your Google Places API key.

Example configuration:
```properties
# Spring Application Name
spring.application.name=nearbyplacesapi

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/nearbyplaces
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Google Places API Key
google.api.key=YOUR_GOOGLE_API_KEY

# Server Port
server.port=8070
```

### API Endpoints

- **GET /places/nearby**: Fetches nearby places based on latitude, longitude, and radius.
  - **Parameters**:
    - `latitude`: Latitude of the location (e.g., `37.7749`).
    - `longitude`: Longitude of the location (e.g., `-122.4194`).
    - `radius`: Search radius in meters (e.g., `500`).

### Example Request

To find nearby places, you can make a GET request as follows:

```
GET http://localhost:8070/places/nearby?latitude=37.7749&longitude=-122.4194&radius=500
```

### Rate Limiting

The API is configured to allow a maximum of **2 requests per minute**. If the limit is exceeded, a `429 Too Many Requests` response will be returned, indicating that the user should try again later.

### Exception Handling

The application includes a global exception handler that returns appropriate HTTP status codes for different types of errors:

- **Rate Limit Exceeded**: Returns a `429` status code.
- **General Exceptions**: Returns a `500` status code for unexpected errors.







# Nearby Places Frontend

Nearby Places is a React application that allows users to search for places in their vicinity. Utilizing the Google Maps API, it displays locations 
within a specified radius of a given latitude and longitude. This project aims to provide a user-friendly interface for searching and viewing places.

## Table of Contents

## Requirements

- **Node.js**: v14 or higher
- **npm**: Node Package Manager

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/username/nearbyplacesfrontend.git
   ```

2. Navigate to the project directory:

   ```bash
   cd nearbyplacesfrontend
   ```

3. Install the necessary dependencies:

   ```bash
   npm install
   ```

## Running the Application

To start the application in development mode, run:

```bash
npm start
```

Open your browser and navigate to [http://localhost:3000](http://localhost:3000). The application will automatically reload if you make changes to the code.


This command will execute the tests and display the results in the console.

## Building for Production

To build the application for production, run:

```bash
npm run build
```

This command will create an optimized and minified version of your application in the `build` folder, ready for deployment.

## How It Works

1. **User Input**: The application allows users to input their current location (latitude and longitude) and a search radius.
2. **API Call**: Upon submission, the application makes a request to the backend API, which fetches nearby places based on the provided coordinates and radius.
3. **Data Display**: The results are displayed in a list format and on a map, providing users with a visual representation of the locations.
4. **Responsive Design**: The application is designed to be responsive, ensuring a seamless experience across various devices.

## Usage

Users can search for places by entering their latitude, longitude, and the desired search radius. The application will return a list of places along with their details, which can be viewed on a map. Each place entry includes essential information such as name, vicinity, and coordinates.

### Features

- **Place Search**: Users can search for places within a specified radius.
- **Map Visualization**: Found places are displayed on a map for easy navigation.
- **Responsive Design**: The application adapts to different screen sizes for optimal usability.








### Database

The **Nearby Places API** uses a **MySQL database** to cache responses from the Google Places API. This approach minimizes redundant API calls, enhances performance, and ensures a faster response time for recurring queries. The database is designed to store place details and associate them with a unique query key to efficiently retrieve cached results for identical requests.

#### **Database Table: `Place`**

The `Place` table is the central storage for cached place information. It is structured as follows:

| **Column**       | **Type**           | **Description**                                                                 |
|-------------------|--------------------|---------------------------------------------------------------------------------|
| `id`             | `BIGINT AUTO_INCREMENT` | Primary key for the table.                                                    |
| `latitude`       | `DOUBLE`           | Latitude of the requested location.                                            |
| `longitude`      | `DOUBLE`           | Longitude of the requested location.                                           |
| `radius`         | `DOUBLE`           | Search radius for the query in meters.                                         |
| `query_key`      | `VARCHAR(255)`     | Unique key generated using the combination of latitude, longitude, and radius. |
| `name`           | `VARCHAR(255)`     | Name of the place returned by the Google Places API.                           |
| `vicinity`       | `VARCHAR(255)`     | Vicinity or address of the place.                                              |
| `created_at`     | `TIMESTAMP`        | Timestamp indicating when the record was created (used for cache expiry).      |

#### **Query Key Mechanism**

- The `query_key` is a unique identifier generated for each API query by concatenating the `latitude`, `longitude`, and `radius` values, typically hashed to ensure uniqueness. 
- When a request is made to the backend, the application first checks if a result with the corresponding `query_key` exists in the database:
  - **If found**: Cached results are returned directly from the database.
  - **If not found**: A new API request is made to Google Places, and the response is stored in the database with the generated `query_key`.




### Deployment

The **Nearby Places** application is deployed as a full-stack solution, with the backend, frontend, and database hosted on separate platforms to ensure scalability, availability, and ease of management.

#### **Database Deployment**

The **MySQL database** is hosted on **[X Hosting Platform]**, providing reliable database services with robust scaling and backup features. The database instance is configured to allow secure connections from the backend API. 

#### **Backend Deployment**

The **Nearby Places API** backend is deployed on **Render.com** using Docker. Docker ensures a consistent environment across development and production. The deployment steps are as follows:

1. **Create a Dockerfile**:
   ```dockerfile
   FROM openjdk:17-jdk-slim
   COPY target/nearbyplacesapi-0.0.1-SNAPSHOT.jar app.jar
   ENTRYPOINT ["java", "-jar", "/app.jar"]
   ```

2. **Build the Docker Image**:
   ```bash
   docker build -t nearbyplaces-api .
   ```

3. **Push to Docker Registry** (optional, if using a registry):
   ```bash
   docker tag nearbyplaces-api your-dockerhub-username/nearbyplaces-api
   docker push your-dockerhub-username/nearbyplaces-api
   ```

4. **Deploy on Render**:
   - Create a new service on Render.
   - Select "Docker" as the environment.
   - Connect your GitHub repository or Docker image.
   - Set environment variables for database credentials and Google API key:
     - `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `GOOGLE_API_KEY`.

5. **Expose the Service**:
   Configure the service to listen on port `8070` and expose it as a public endpoint.

#### **Frontend Deployment**

The React frontend is also deployed on **Render.com**:

1. **Build the React App**:
   ```bash
   npm run build
   ```

2. **Deploy Static Files**:
   - On Render, create a new static site.
   - Upload the contents of the `build` folder.
   - Configure the environment variable `REACT_APP_API_URL` to point to the backend API URL.

3. **Custom Domain** (Optional):
   Link a custom domain to the Render service for a professional user experience.

---

### **Summary**

- **Database**: MySQL hosted on **X Platform** with caching and a query key mechanism.
- **Backend**: Spring Boot API hosted on **Render.com**, containerized using Docker for consistency and scalability.
- **Frontend**: React app deployed as a static site on **Render.com**, connecting seamlessly to the backend API.

