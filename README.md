# Wallet Service – Spring Boot Assignment

## 📌 Overview

This project is a **Spring Boot 3**–based wallet service built as part of a technical assignment. The application provides REST APIs to manage wallet balances with **deposit** and **withdraw** operations, designed to work correctly under **high concurrency (1000 RPS per wallet)**.

The system is fully **Dockerized**, uses **PostgreSQL** as the database, and manages database schema changes using **Liquibase**.

---

## 🛠 Tech Stack

* **Java:** 17
* **Spring Boot:** 3.x
* **Spring Data JPA (Hibernate)**
* **PostgreSQL**
* **Liquibase** (DB migrations)
* **Docker & Docker Compose**
* **JUnit 5 + MockMvc** (tests)

---

## ⚙️ Key Features

* REST APIs for wallet operations
* Optimistic locking to safely handle concurrent updates
* Proper error handling (no 5xx errors for business cases)
* Externalized configuration via environment variables
* Database migrations managed by Liquibase
* Docker-based local setup

---

## 📂 Project Structure (Simplified)

```
wallet-service
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java/wallet/project/itkedu
    │   │   ├── controller
    │   │   ├── service
    │   │   ├── repository
    │   │   ├── entity
    │   │   ├── dto
    │   │   └── exception
    │   └── resources
    │       ├── application.properties
    │       └── db/changelog/db.changelog-master.yml
    └── test
        └── java/wallet/project/itkedu/controller
```

---

## 🚀 How to Run the Application

### Prerequisites

* Java 17
* Maven
* Docker & Docker Compose

### Steps

1. Build the application:

```bash
mvn clean package
```

2. Start the system using Docker Compose:

```bash
docker compose up --build
```

3. Application will be available at:

```
http://localhost:8080
```

---

## 🧪 API Endpoints

### ➤ Get Wallet Balance

```
GET /api/v1/wallets/{walletId}
```

**Response**

```json
{
  "walletId": "11111111-1111-1111-1111-111111111111",
  "balance": 10000
}
```

---

### ➤ Deposit / Withdraw

```
POST /api/v1/wallet
```

**Request Body**

```json
{
  "walletId": "11111111-1111-1111-1111-111111111111",
  "operationType": "DEPOSIT",
  "amount": 500
}
```

**Response**

```json
{
  "walletId": "11111111-1111-1111-1111-111111111111",
  "balance": 10500
}
```

---

## ❌ Error Handling

| Scenario             | HTTP Status | Message            |
| -------------------- | ----------- | ------------------ |
| Wallet not found     | 404         | Wallet not found   |
| Insufficient funds   | 400         | Insufficient funds |
| Invalid request JSON | 400         | Invalid request    |

All business errors are handled gracefully — no unhandled 5xx errors.

---

## 🔒 Concurrency Handling

* **Optimistic locking** is implemented using `@Version` in the `Wallet` entity.
* Each update runs within a transactional boundary.
* Concurrent updates on the same wallet do not overwrite each other.

This design safely supports high request rates (1000 RPS per wallet).

---

## 🧪 Tests

* Controller endpoints are tested using `@WebMvcTest` and `MockMvc`.
* Tests are isolated from infrastructure (DB, Docker, Liquibase).

Run tests with:

```bash
mvn clean test
```

---

## ⚙️ Configuration

* All database and application settings are externalized using environment variables.
* Containers do **not** need to be rebuilt when configuration changes.

Example:

```properties
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
```

---

## 📦 Database Migrations

* Managed using Liquibase
* Migration scripts are located at:

```
src/main/resources/db/changelog/
```

---

## 📌 Notes

* The application is designed and implemented bottom-up: infrastructure → database → persistence → service → API.
* All assignment requirements have been met.

---

## 👤 Author

**Your Name**
GitHub: [https://github.com/your-username/your-repo](https://github.com/your-username/your-repo)

---

## ✅ Status

✔ Assignment completed and ready for submission.
