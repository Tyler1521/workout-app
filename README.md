# Testing Spring Boot API

## Overview

This project is a Spring Boot REST API with JWT authentication and MongoDB integration.

## Features

- JWT-based authentication
- MongoDB data storage
- RESTful endpoints for games, workouts, dashboard, etc.
- Swagger UI for API documentation

## Running the Application Locally

1. Configure your MongoDB URI and credentials in `src/main/resources/application.properties`.
2. Build and run the project:
   ```sh
   mvn spring-boot:run
   ```
3. Access the API at `http://localhost:8080/`.
4. Access Swagger UI at `http://localhost:8080/swagger-ui.html` or `/swagger-ui/index.html`.

## Deployed Application

The application is deployed on Render:

- **Base URL:** [https://workout-app-rmfp.onrender.com](https://workout-app-rmfp.onrender.com)
- **Swagger UI:** [https://workout-app-rmfp.onrender.com/swagger-ui.html](https://workout-app-rmfp.onrender.com/swagger-ui.html) or [https://workout-app-rmfp.onrender.com/swagger-ui/index.html](https://workout-app-rmfp.onrender.com/swagger-ui/index.html)

## Authentication

- Obtain a JWT token via `/authenticate` using Basic Auth.
- Use the token in the `Authorization: Bearer <token>` header for protected endpoints.

## API Documentation

See [Swagger UI](https://workout-app-rmfp.onrender.com/swagger-ui.html) for interactive API docs.