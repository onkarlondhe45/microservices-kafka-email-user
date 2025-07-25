# Microservices Project - User and Email Services

This is a microservices demo project built using Spring Boot that showcases communication between services via Apache Kafka. It demonstrates resilience using Resilience4j, service discovery with Eureka Server, centralized routing with API Gateway, and structured logging with SLF4J.

---

## 🛠️ Technologies Used

- **Spring Boot**
- **Apache Kafka**
- **Spring Cloud Eureka Server (Service Discovery)**
- **Spring Cloud Gateway (API Gateway)**
- **Resilience4j (Circuit Breaker)**
- **SLF4J (Logging)**
- **Lombok**

---

## 📁 Microservices Included

### 1. `user-service`
- Registers users
- Sends registration confirmation messages to Kafka
- Uses Resilience4j circuit breaker when sending email fails
- Registers with Eureka Server

### 2. `email-service`
- Consumes Kafka messages
- Sends email notifications
- Registers with Eureka Server

### 3. `eureka-server`
- Acts as a service registry for all microservices

### 4. `api-gateway`
- Routes API requests to `user-service` and `email-service`
- Load balancing via Eureka
- Centralized entry point to the system

---

## 🔁 Flow of Communication

1. User registers via `user-service`.
2. `user-service` publishes an email event to Kafka.
3. `email-service` consumes the event and sends an email.
4. If email fails, `user-service` fallback method (Resilience4j) logs the issue.

---

## 🚀 Running the Application

### Prerequisites:
- Java 17+
- Apache Kafka & Zookeeper running
- Maven

### Run Order:
```bash
# 1. Start Kafka and Zookeeper (e.g., via local install or Docker)
# 2. Start Eureka Server
cd eureka-server
mvn spring-boot:run

# 3. Start API Gateway
cd api-gateway
mvn spring-boot:run

# 4. Start Email Service
cd email-service
mvn spring-boot:run

# 5. Start User Service
cd user-service
mvn spring-boot:run
