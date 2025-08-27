# JBank API

## Project Overview 🚀

JBank is a complete banking API built with **Spring Boot**. This project was an incredible journey to solidify my knowledge in backend development, focusing on robust and scalable solutions. It features core banking functionalities like creating wallets, making deposits, and viewing transaction statements, all powered by a well-structured and secure architecture.

## Key Features & Technologies 💡

* **API Development with Spring Web**: Designed and implemented a RESTful API with custom Handlers for a clean, maintainable structure.
* **Request & Response Management**: Utilized **Filters** and **Interceptors** for request manipulation, ensuring every transaction is properly tracked and audited.
* **Advanced Data Validation**: Implemented powerful validation rules with **Hibernate Validator** to ensure data integrity and provide clear, immediate feedback for invalid requests.
* **Transaction Integrity & Concurrency Control**: Leveraged Spring's `@Transactional` and `@Version` for optimistic locking to handle concurrent operations and guarantee data consistency.
* **Optimized Data Retrieval**: Employed **JPA Projections** for complex queries, retrieving only the necessary data from the database, which significantly boosts performance.
* **Custom Exception Handling**: Created custom exception handlers to provide meaningful and consistent error responses to the client.

## Getting Started ⚙️

### Prerequisites

* Java 17+
* Maven
* PostgreSQL or another relational database

### Running the Application

1.  Clone the repository:
    `git clone <repository-url>`
2.  Navigate to the project directory:
    `cd jbank`
3.  Configure your database connection in `src/main/resources/application.properties`.
4.  Run the application using Maven:
    `mvn spring-boot:run`

The API will be available at `http://localhost:8080`.

## API Endpoints 🎯

### Wallet Endpoints (`/wallets`)

| Method   | Endpoint                          | Description                               |
| :------- | :-------------------------------- | :---------------------------------------- |
| `POST`   | `/wallets`                        | Creates a new wallet.                     |
| `DELETE` | `/wallets/{walletId}`             | Deletes a wallet by its ID.               |
| `POST`   | `/wallets/{walletId}/deposits`    | Deposits money into a wallet.             |
| `GET`    | `/wallets/{walletId}/statements`  | Retrieves a paginated list of wallet statements. |

### Transfer Endpoints (`/transfers`)

| Method   | Endpoint                          | Description                             |
| :------- | :-------------------------------- | :-------------------------------------- |
| `POST`   | `/transfers`                      | Transfers money between two wallets.    |

---

**#SpringBoot #JPA #HibernateValidator #BackendDevelopment #Java #API #SoftwareEngineering**
