# Banking Management System

A Java-based Banking Management System developed using Java Swing and MySQL that simulates core banking operations such as account management, deposits, withdrawals, money transfers, transaction tracking, and admin monitoring.

---

## Features

* User Registration & Login
* Secure Password Hashing (SHA-256)
* Deposit & Withdraw Money
* Fund Transfer
* Transaction History
* Admin Dashboard
* Account Balance Management
* MySQL Database Integration

---

## Tech Stack

* Java
* Java Swing
* MySQL
* JDBC

---

## Project Structure

```bash id="e0k0vz"
src/
├── gui/
├── models/
├── services/
├── security/
├── utils/
└── database/
```

---

## Database Setup

Create a MySQL database:

```sql id="b6m6px"
CREATE DATABASE banking_system;
```

Update database credentials in the project configuration:

```java id="u9m49g"
private static final String URL = "jdbc:mysql://localhost:3306/banking_system";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```

---

## How to Run

1. Clone the repository

```bash id="49wf8m"
git clone https://github.com/YOUR_USERNAME/banking-management-system.git
```

2. Open the project in IntelliJ IDEA / Eclipse / VS Code

3. Add MySQL JDBC Driver

4. Run:

```bash id="rjlwm0"
Main.java
```

---

## Future Improvements

* REST API Integration
* JWT Authentication
* OTP Verification
* Cloud Deployment
* Advanced Analytics Dashboard
* Docker Support

---

## Learning Outcomes

This project helped in understanding:

* Object-Oriented Programming
* Java Swing GUI Development
* JDBC & MySQL Integration
* Authentication Systems
* Software Architecture Basics

---

## Author

G N Bhuvaneshwaran
