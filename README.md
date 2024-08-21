# My First DevOps Project

This project demonstrates a simple CI/CD pipeline using Docker, Python, and a Spring Boot application with MySQL.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Project](#running-the-project)


## Prerequisites
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- Git

## Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/Vikomizrahi/My_First_DeVops.git
    cd My_First_DeVops
    ```
2. Build and run the Docker containers:
    ```sh
    docker-compose up --build
    ```
    This command will:
    - Build the Spring Boot application Docker image.
    - Build the Python test Docker image.
    - Start the MySQL database container.
    - Start the Spring Boot application container.
    - Run the tests in the Python container.
  
## API Documentation
The API documentation is available through Swagger UI. Once the application is running, you can access the Swagger UI at the following URL:

http://localhost:8080/swagger-ui.html

Swagger UI provides a user-friendly interface to interact with the API, view available endpoints, and test the API methods.

## Running the Project
To start the application, use the following command:
```sh
docker-compose up


