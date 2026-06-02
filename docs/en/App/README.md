# 📘 CentroPlus Connect — Project Documentation

---

# 1. Functional Documentation

## 1.1 System Purpose

CentroPlus Connect is a management system designed to handle users, activities, reservations, and incidents within an organized platform.

It allows users to:
- Register and authenticate
- Book activities
- Manage reservations
- Report incidents
- Maintain a persistent session (remember-me functionality)

---

## 1.2 User Roles

### Student (ALUMNO)
- View activities
- Create reservations
- Report incidents

### Member (SOCIO)
- Same permissions as students
- Access additional activities if available

### Both (AMBOS)
- Combined privileges of student and member

---

## 1.3 Main Functionalities

- User authentication (login/logout)
- Persistent login (remember-me token system)
- Activity management
- Reservation system
- Incident reporting system
- User profile management
- Session handling

---

## 2. Use Cases

## 2.1 Login

**Actor:** User  
**Precondition:** User exists in database  

**Main flow:**
1. User enters DNI and password
2. System validates credentials
3. If correct, session is created
4. Optional: remember-me token is generated

**Errors:**
- Invalid credentials
- Missing fields

---

## 2.2 Logout

**Actor:** Authenticated user  
**Precondition:** User is logged in  

**Flow:**
1. System clears session
2. Deletes stored token (if exists)
3. Redirects to login screen

---

## 2.3 Create Reservation

**Actor:** User  
**Precondition:** User logged in  

**Flow:**
1. User selects activity
2. System checks availability
3. Reservation is created
4. Activity spots are updated

---

## 2.4 Cancel Reservation

**Actor:** User  
**Precondition:** Reservation exists  

**Flow:**
1. User selects reservation
2. System marks it as canceled
3. Activity spots are restored

---

## 2.5 Create Incident

**Actor:** User  
**Precondition:** User logged in  

**Flow:**
1. User submits incident form
2. System stores incident in database
3. Status is set to OPEN

---

## 3. System Architecture

## 3.1 Layers

The system follows a layered architecture:

- Presentation Layer → JavaFX Controllers
- Service Layer → Business logic
- Repository Layer → Database access (JDBC)
- Utility Layer → Session, tokens, navigation

---

## 3.2 Architecture Flow

UI (JavaFX)
↓
Controller
↓
Service
↓
Repository
↓
SQLite Database


---

## 4. Security

## 4.1 Password Security
- Passwords are stored hashed (SHA-256 or equivalent)
- No plain-text passwords are stored

---

## 4.2 Remember Me System
- Tokens are generated on login
- Tokens are hashed before storage
- Tokens have expiration date
- Stored in remember_tokens table

---

## 4.3 Session Management
- Active user stored in memory (Session class)
- Persistent user ID stored in preferences
- Session cleared on logout

---

## 5. Installation Guide

## 5.1 Requirements
- Java 17+
- Maven
- SQLite
- JavaFX

---

## 5.2 Execution Steps

1. Clone repository
2. Open project in IDE
3. Build with Maven
4. Run Main class

---

## 5.3 Database Setup

Database file location: mobile/src/main/resources/database/centroplus.db


No manual setup required (SQLite included).

---

## 6. User Manual

## 6.1 Login
- Enter DNI and password
- Optionally enable "Remember me"

## 6.2 Main Screen
- View activities
- Access reservations
- Open incidents

## 6.3 Profile
- View personal data
- Change password
- Logout

---

## 7. Testing Plan

## 7.1 Login Tests

- Valid credentials → success
- Invalid DNI → error
- Empty password → error
- Wrong password → error
- Non-existent user → error

---

## 7.2 Reservation Tests

- Create reservation successfully
- Cancel reservation successfully
- No available spots → error

---

## 7.3 Incident Tests

- Create incident successfully
- Empty fields → validation error

---

## 8. Code Architecture (Java Packages)

- controllers → UI logic
- services → business logic
- repositories → database access
- models → entities
- utils → helpers (Session, ScreenManager, TokenUtils)

---

## 9. Login Flow Diagram

User Input
↓
Validate credentials
↓
Check repository
↓
Verify password
↓
Create session
↓
Optional token generation
↓
Redirect to main screen


---

## 10. Summary

CentroPlus Connect is a layered Java application that integrates:
- Authentication system
- Activity management
- Reservation system
- Incident tracking
- Persistent login system
- SQLite persistence layer
