# Prescription-Generator

A RESTful API built with Java and Spring Boot for generating and managing prescriptions securely. The API includes authentication using Spring Security and provides API documentation with Swagger.

## Features
- User authentication and authorization (Spring Security)
- Prescription creation and management
- H2 in-memory database for testing
- Consumes third party API using RestTemplate
- API documentation with Swagger UI

## Technologies Used
- Java
- Spring Boot
- Spring Security
- H2 Database
- Swagger (OpenAPI)

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/Prescription-Generator.git
   cd Prescription-Generator
   ```
2. Run the application:
   ```bash
   Open this project with IntelliJ IDEA and click on Run icon
   ```

## Run with Docker
You can pull the latest Docker image and run the container using:
```bash
docker pull tareq23/prescription-service:v1.0.0
docker run -p 9090:9090 tareq23/prescription-service:v1.0.0
```


## API Documentation
After running the application, you can access the Swagger UI:
```
http://localhost:9090/swagger-ui/index.html
```

## Usage
- Send API requests using Swagger UI or tools like Postman.
- Authenticate and manage prescriptions securely.

