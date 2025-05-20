🐦 Twitter API Clone (Spring Boot + PostgreSQL)

This is a backend-focused Twitter clone built with Spring Boot and PostgreSQL, designed as a RESTful API that supports tweeting, liking, commenting, retweeting, and user management with secure authentication.

🚀 Features

✅ User registration & login with JWT authentication

✅ Role-based access control using Spring Security

✅ Create, update, delete tweets

✅ Like, comment, retweet functionality

✅ Get all users and their tweets

✅ Clean DTO structure to avoid infinite recursion in JSON

✅ Tested via Postman




🛠️Tech Stack


Backend: Java, Spring Boot, Spring Security, JPA (Hibernate)

Database: PostgreSQL

Authentication: JWT (JSON Web Token)

Testing: Postman

Build Tool: Maven


📮 Sample Endpoints
| Method | Endpoint               | Description         |
| ------ | ---------------------- | ------------------- |
| POST   | `/auth/register`       | Register new user   |
| POST   | `/auth/login`          | Login and get token |
| POST   | `/tweets`              | Create a tweet      |
| GET    | `/tweets`              | Get all tweets      |
| POST   | `/tweets/{id}/like`    | Like a tweet        |
| POST   | `/tweets/{id}/comment` | Comment on a tweet  |
| POST   | `/tweets/{id}/retweet` | Retweet             |
| GET    | `/users`               | Get all users       |



