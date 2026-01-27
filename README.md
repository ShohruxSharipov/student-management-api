## Student Management REST API

A simple Spring Boot REST API for managing students.  
This project was created to practice REST concepts such as controllers, services, DTOs, validation, and HTTP methods.

The application uses a PostgreSQL database for data persistence.

### Technologies

- Java
- Spring Boot
- Spring Web
- PostgreSQL
- HTML, CSS, JavaScript

### API Endpoints

- GET /students - get all students
- GET /students/{id} - get student by ID
- POST /students - create a new student
- PUT /students/{id} - update student
- DELETE /students/{id} - delete student
- GET /students/search - search students using query parameters


### Notes

- Data is stored in PostgreSQL
- Configure database URL, username, and password in `application.properties`
- Project is for learning purposes

### Author

Shohruh
