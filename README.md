# 🏨 Hotel Reservation Management System

A full-stack backend application built using **Spring Boot**, **Spring Security (JWT)**, and **MySQL** for managing hotel reservations, rooms, billing, and payments with role-based access for Admin and Customer.

---

## 🚀 Features

### 👤 Authentication & Authorization
- Secure login using JWT (JSON Web Token)
- Role-based access control (ADMIN / CUSTOMER)
- Spring Security integration

### 🏨 Hotel Management
- Add, update, delete hotels
- View all hotels

### 🛏️ Room Management
- Add rooms to hotels
- Update room details
- Filter rooms by hotel
- Manage room status (AVAILABLE / BOOKED)

### 📅 Reservation System
- Book rooms
- Cancel reservations
- Checkout system
- View reservations by user and admin

### 💳 Payment System
- Make payment for reservations
- Payment tracking by reservation
- Support for frontend-provided amount

### 🧾 Billing System
- Generate bills after reservation
- View bills by customer or reservation
- View bills of logged-in user

---

## 🏗️ Tech Stack

- Backend: Spring Boot
- Security: Spring Security + JWT
- Database: MySQL
- ORM: Spring Data JPA / Hibernate
- Build Tool: Maven
- Java Version: 17+

---

## 📁 Project Structure

com.hotel.Hotel_Reservation_Management  
│  
├── controller        → REST APIs  
├── service           → Business logic interfaces  
├── serviceImpl       → Implementation classes  
├── repository        → Database layer (JPA)  
├── entity            → Database models  
├── dto               → Data Transfer Objects  
├── security          → JWT + Spring Security  
├── enums             → Status & roles  
└── exception         → Global exception handling  

---

## 🔐 Security Flow

1. User logs in with username & password  
2. JWT token is generated  
3. Token sent in request header (Bearer Token)  
4. JwtFilter validates token  
5. User authentication is set in SecurityContext  
6. Access granted based on role  

---

## 🔄 Architecture Flow

Client → Controller → Service → Repository → Database  
                     ↑  
                JWT Filter  

---

## 👥 User Roles

### ADMIN
- Manage hotels
- Manage rooms
- View all users and reservations
- Full system control

### CUSTOMER
- Register/Login
- View hotels and rooms
- Book rooms
- Make payments
- View bills and reservations

---

## 🧪 Sample API Endpoints

### Authentication
POST /api/auth/login  
POST /api/auth/register  

### Hotels
GET /api/hotels  
POST /api/hotels  

### Rooms
GET /api/rooms  
GET /api/rooms/hotel/{hotelId}  

### Reservations
POST /api/reservations  
GET /api/reservations/{id}  

### Payments
POST /api/payments/pay/{reservationId}  

### Billing
GET /api/billing/{id}  
GET /api/billing/customer/{customerId}  

---

## 🗄️ Database Design

- MySQL used as database
- JPA/Hibernate handles ORM mapping

### Relationships:
- Hotel → Rooms (One-to-Many)
- Customer → Reservations (One-to-Many)
- Reservation → Payment (One-to-One)
- Reservation → Billing (One-to-One)

---

## ⚙️ Setup Instructions

### 1. Clone Repository
git clone <your-repo-url>

### 2. Configure Database
Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/hotel_db  
spring.datasource.username=root  
spring.datasource.password=yourpassword  

### 3. Run Project
mvn spring-boot:run

---

## 🔑 JWT Details

- Algorithm: HS256
- Token contains:
  - username
  - role
- Expiration: 5 hours

---

## 📌 Highlights

- Clean layered architecture
- Secure JWT authentication
- Role-based authorization
- DTO-based design
- Real-world hotel booking workflow

---

## 👨‍💻 Author

Developed by: Shana Shekh and Srishti Nehra

---

## 📄 License

This project is for educational purposes only.
