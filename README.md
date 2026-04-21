// filepath: c:\Users\tcf895\testing\README.md
# Testing Spring Boot API

## Overview

This project is a Spring Boot REST API with JWT authentication and MongoDB integration.

## Features

- JWT-based authentication
- MongoDB data storage
- RESTful endpoints for games, workouts, dashboard, etc.
- Swagger UI for API documentation

## Running the Application

1. Configure your MongoDB URI and credentials in `src/main/resources/application.properties`.
2. Build and run the project:
   ```sh
   mvn spring-boot:run
   ```
3. Access the API at `http://localhost:8080/`.
4. Access Swagger UI at `http://localhost:8080/swagger-ui.html` or `/swagger-ui/index.html`.

## Authentication

- Obtain a JWT token via `/authenticate` using Basic Auth.
- Use the token in the `Authorization: Bearer <token>` header for protected endpoints.

## API Documentation

See [Swagger UI](http://localhost:8080/swagger-ui.html) for interactive API docs.