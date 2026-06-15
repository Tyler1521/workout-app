# Testing Spring Boot API

## Overview

This project is a Spring Boot REST API with JWT authentication, MongoDB integration, and Redis for caching and message queue.

## Features

- JWT-based authentication
- MongoDB data storage
- Redis caching for improved performance (e.g., dashboard data)
- Redis-based message queue (pub/sub) for asynchronous processing (e.g., game creation)
- RESTful endpoints for games, workouts, dashboard, etc.
- Swagger UI for API documentation

## Running the Application Locally

1. Configure your MongoDB URI and credentials in `src/main/resources/application.properties`.
2. Ensure you have a running Redis server (locally, via Docker, or Redis Cloud).
   - Example (Docker):  
     ```sh
     docker run --name redis -p 6379:6379 redis
     ```
3. Build and run the project:
   ```sh
   mvn spring-boot:run
   ```
4. Access the API at `http://localhost:8080/`.
5. Access Swagger UI at `http://localhost:8080/swagger-ui.html` or `/swagger-ui/index.html`.

## Redis Configuration

- Update `spring.redis.host`, `spring.redis.port`, and (if needed) `spring.redis.password` in `application.properties` to match your Redis instance.
- For Redis Cloud, also set `spring.redis.ssl.enabled=true`.

## Deployed Application

The application is deployed on Render:

- **Base URL:** [https://workout-app-rmfp.onrender.com](https://workout-app-rmfp.onrender.com)
- **Swagger UI:** [https://workout-app-rmfp.onrender.com/swagger-ui.html](https://workout-app-rmfp.onrender.com/swagger-ui.html) or [https://workout-app-rmfp.onrender.com/swagger-ui/index.html](https://workout-app-rmfp.onrender.com/swagger-ui/index.html)

## Authentication

- Obtain a JWT token via `/authenticate` using Basic Auth.
- Use the token in the `Authorization: Bearer <token>` header for protected endpoints.

## API Documentation

See [Swagger UI](https://workout-app-rmfp.onrender.com/swagger-ui.html) for interactive API docs.

## Redis Caching

- Frequently accessed data (e.g., dashboard statistics) is cached in Redis for faster response times.
- Cache is automatically invalidated when underlying data changes.

## Redis Message Queue

- Game creation requests are published to a Redis topic.
- A subscriber asynchronously processes these requests and updates the database, improving scalability and reliability under high load.
