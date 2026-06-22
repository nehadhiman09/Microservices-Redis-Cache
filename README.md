# Microservices-Redis-Cache

# 🚀 Microservices Redis Cache

A Spring Boot Microservices project that implements **OTP-based Authentication**, **JWT Authentication**, **Redis Caching**, **Email OTP**, and **SMS OTP using Twilio**.

---

## 📖 Overview

This project follows a **Microservices Architecture** and provides secure authentication using **One-Time Passwords (OTP)**. OTPs are stored temporarily in **Redis** and can be delivered through **Email** or **SMS (Twilio)**. After successful verification, a **JWT Token** is generated for secure access.

---

## ✨ Features

* 🔐 OTP-based Authentication
* 📧 Email OTP using Spring Mail
* 📱 SMS OTP using Twilio
* ⚡ Redis Cache for OTP Storage
* 🔑 JWT Token Generation
* 🌐 Spring Cloud OpenFeign
* 🏛️ Eureka Service Discovery
* 🚪 API Gateway Integration
* 🛡️ Spring Security
* 🐳 Dockerized Redis

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot 3.x
* Spring Security
* Spring Cloud
* Spring Data Redis
* Redis
* JWT
* OpenFeign
* Eureka Server
* Twilio API
* Java Mail Sender
* Maven
* Docker

---

## 📂 Project Structure

```text
Microservices-Redis-Cache/
│
├── auth-service/
├── user-service/
├── api-gateway/
├── eureka-server/
├── docker/
└── README.md
```

---

## 🔄 Authentication Flow

1. User enters an Email ID or Mobile Number.
2. System generates a random OTP.
3. OTP is stored in Redis with an expiration time.
4. OTP is sent via:

   * 📧 Email
   * 📱 SMS using Twilio
5. User submits the OTP.
6. OTP is validated from Redis.
7. JWT Token is generated and returned.

---

## ⚙️ Configuration

Update your `application.properties` file:

```properties
# Server
server.port=8082

# Redis
spring.data.redis.host=localhost
spring.data.redis.port=6379

# JWT
jwt.secret=YOUR_SECRET_KEY
jwt.expiration=86400000

# Gmail
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=YOUR_EMAIL
spring.mail.password=YOUR_APP_PASSWORD

# Twilio
twilio.account.sid=YOUR_ACCOUNT_SID
twilio.auth.token=YOUR_AUTH_TOKEN
twilio.phone.number=YOUR_TWILIO_PHONE_NUMBER

# Eureka
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

> ⚠️ **Do not commit real credentials to GitHub.**

---

## 🐳 Run Redis Using Docker

```bash
docker run -d --name redis-server -p 6379:6379 redis:latest
```

Verify Redis:

```bash
docker exec -it redis-server redis-cli
```

Then execute:

```bash
PING
```

Expected output:

```text
PONG
```

---

## 🚀 Build the Project

```bash
mvn clean install
```

---

## ▶️ Run the Application

```bash
mvn spring-boot:run
```

Or run each microservice individually from your IDE.

---

## 📡 API Endpoints

### Send OTP

**POST** `/auth/send-otp`

#### Email Request

```json
{
  "email": "user@example.com"
}
```

#### Mobile Request

```json
{
  "mobile": "+919876543210"
}
```

---

### Verify OTP

**POST** `/auth/verify-otp`

#### Request

```json
{
  "email": "user@example.com",
  "otp": "123456"
}
```

or

```json
{
  "mobile": "+919876543210",
  "otp": "123456"
}
```

---

## 🔑 Success Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.xxxxxxxxx.yyyyyyyyy"
}
```

---

## 📥 Clone the Repository

```bash
git clone https://github.com/nehadhiman09/Microservices-Redis-Cache.git
```

```bash
cd Microservices-Redis-Cache
```

---

## 👩‍💻 Author

**Neha Dhiman**

* Java Developer
* Spring Boot Developer
* Microservices Enthusiast
* Redis Integration
* JWT Authentication
* Docker
* Twilio SMS Integration

---

## ⭐ Support

If you found this project useful, please consider giving it a **⭐ Star** on GitHub.
