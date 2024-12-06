
# User Registration and Email Verification Demo

This is a simple Spring Boot project demonstrating a complete user registration and login flow with email verification. After a user registers, they must verify their email through a link sent to their email address. Only after successful verification can they log in to their account.

---

## Features
- **User Registration:** Users can register with their details.
- **Email Verification:** A verification email is sent to the user's email with a unique token.
- **Login Authentication:** Only verified users can log in.
- **Token Expiration:** Verification tokens are time-bound for security.

---

## Technologies Used
- **Java 17**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA (Hibernate)**
- **MySQL** (Database)
- **Jakarta Mail** (for sending emails)

---

## Setup and Run Instructions

### Prerequisites
- JDK 17 or higher
- MySQL database
- Maven

### Steps to Run
1. **Clone the Repository**
   ```bash
   git clone https://github.com/dailycodework/spb-email-verification-demo.git
   cd spb-email-verification-demo
   ```

2. **Configure the Database**
    - Create a MySQL database (e.g., `email_verification`).
    - Update the `application.properties` file with your database details:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/email_verification
      spring.datasource.username=your_username
      spring.datasource.password=your_password
      ```

3. **Configure Mail Settings**
    - Add your email server details to the `application.properties` file:
      ```properties
      spring.mail.host=smtp.gmail.com
      spring.mail.port=587
      spring.mail.username=your_email@gmail.com
      spring.mail.password=your_email_password
      spring.mail.properties.mail.smtp.auth=true
      spring.mail.properties.mail.smtp.starttls.enable=true
      ```

4. **Build and Run the Project**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application**
    - Open your browser and navigate to `http://localhost:8080`.

---

## API Endpoints
### 1. **Register User**
- **Endpoint:** `POST /api/v1/registration`
- **Payload Example:**
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "johndoe@example.com",
    "password": "password123"
  }
  ```

### 2. **Verify Email**
- The verification email contains a link like:
  ```
  http://localhost:8080/api/v1/registration/confirm?token=<verification_token>
  ```

### 3. **Login**
- **Endpoint:** `POST /api/v1/login`
- **Payload Example:**
  ```json
  {
    "email": "johndoe@example.com",
    "password": "password123"
  }
  ```

---

## How It Works
1. **Registration:** The user registers with their details. A verification email is sent with a unique token.
2. **Email Verification:** The user clicks the verification link in their email. The application validates the token and activates the account.
3. **Login:** After email verification, the user can log in using their credentials.

---
