Marlow Bank ATM System

## Overview
The Marlow Bank ATM System is a Spring Boot-based application designed to manage accounts, users, and transactions for a banking system. It supports features such as associating users with accounts, performing deposits and withdrawals, and maintaining a record of transactions.

## Prerequisites

1. **Database Setup**:
    - Ensure you have MySQL installed and running.
    - Create a database for the application. For example:
      ```sql
      CREATE DATABASE marlow_atm;
      ```
    - Update the database connection details (e.g., URL, username, password) in the `application.properties` or `application.yml` file.

2. **Java**:
    - Install Java 17 or higher.

3. **Maven**:
    - Ensure Maven is installed to build the project.

4. **Dependencies**:
    - All required dependencies will be downloaded automatically when you build the project using Maven.

## Features
- **Account**: Create bank accounts.
- **User**: Add users.
- **Account-User Association**: Associate users with accounts using a composite key.
- **Transactions**: Perform deposits and withdrawals on accounts.

## Technologies Used
- **Programming Language**: Java
- **Framework**: Spring Boot
- **Database**: SQL (Relational Database)
- **Build Tool**: Maven
- **ORM**: JPA (Hibernate)
- **Lombok**: For reducing boilerplate code

## Project Structure
### Models
- **`Account`**: Represents a bank account with fields such as `id`, `balance`, etc.
- **`User`**: Represents a user with fields such as `id`, `name`, etc.
- **`AccountUser`**: Represents the association between an account and a user using a composite key (`AccountUserId`).
- **`AccountUserId`**: Composite key for the `AccountUser` entity.
- **`Transaction`**: Represents a transaction (deposit or withdrawal) on an account.

### Repositories
- **`AccountRepository`**: Handles CRUD operations for `Account`.
- **`UserRepository`**: Handles CRUD operations for `User`.
- **`AccountUserRepository`**: Handles CRUD operations for `AccountUser`.
- **`TransactionRepository`**: Handles CRUD operations for `Transaction`.

### Services
- **`AccountUserService`**: Manages the association between users and accounts.
- **`TransactionService`**: Handles deposit and withdrawal operations.

### Key Feature: Associate User with Account

This feature allows associating a user with a bank account. It establishes a relationship between the `User` and `Account` entities using a composite key.

#### Workflow:
1. Retrieve the `User` and `Account` entities from the database.
2. Create an association between the `User` and `Account` using the `AccountUser` entity.
3. Save the association in the `account_user` table.

#### Database Involvement:
- **Tables**: `user`, `account`, `account_user` ,`transaction`
- **Composite Key**: `AccountUserId` (combines `user_id` and `account_id`)

#### API Endpoint:

### User Management
1. **Create User**
    - **Endpoint**: `POST /api/users`
    - **Description**: Creates a new user id should be same as accountid.
    - **Request Body**:
      ```json
      {
        "name": "entername"
      }
      ```
    - **Response**: 201 Created
      ```json
        {
        "id": 3,
        "name": "enteredname"
        }
      ```
2. **Get User**
    - **Endpoint**: `GET /api/users/`
    - **Description**: Retrieves all users.
    - **Response**: 200 OK
      ```json
      [
      {
        "id": 1,
        "name": "User Name"
      }
      ]
      ```
### Account Management
3. **Create Account**
    - **Endpoint**: `POST /api/accounts`
    - **Description**: Creates a new account.
    - **Request Body**:
      ```json
      {
        "accountNumber": "001",
        "name": "Enter Name",
        "balance": 1000.00
      }
      ```
    - **Response**: 201 Created
      ```json
      {
        "id": 1,
        "accountNumber": "001",
        "name": "Entered Name",
        "balance": 1000.00
      }
      ```
4. **Get Account**
    - **Endpoint**: `GET /api/accounts`
    - **Description**: Retrieves all accounts.
    - **Response**: 200 OK
      ```json
      [
        {
          "id": 1,
          "accountNumber": "001",
          "name": "Account Name",
          "balance": 1000.00
        }
      ]
      ```
      
5. **Joint Account**
- **Endpoint**: `POST /api/accounts/{accountid}/users/{userid}`
- **Description**: joint Account with user, body is not required.
-  **Response**: 200 OK
- **ResponseBody**: User added to account successfully.

6. **Deposit**
- **Endpoint**: `POST /api/transactions/deposit`
- **Description**: Deposits money into an account by creating a transaction.
- **Request Body**:
  ```json
  {
    "accountId": 1,
    "amount": 500.00,
    "userId": 1
  }
  ```
- **Response**: 200 OK
  ```json
  {
    "transactionId": 7,
    "accountId": 1,
    "amount": 500.0,
    "type": "DEPOSIT",
    "updatedBalance": 81900.0,
    "timestamp": "2025-04-15T16:18:02.204+00:00"
      }
  ```

  7. **Withdraw**
      - **Endpoint**: `POST /api/transactions/withdraw`
      - **Description**: Withdraws money from an account by creating a transaction.
      - **Request Body**:
        ```json
        {
          "accountId": 1,
          "amount": 200.00,
          "userId": 1
        }
        ```
      - **Response**: 200 OK
        ```json
        {
        "transactionId": 13,
        "accountId": 1,
        "amount": 650.0,
        "type": "WITHDRAWAL",
        "updatedBalance": 81000.0,
        "timestamp": "2025-04-15T16:18:02.204+00:00"
        }
        ``` 
## Database Schema
### Tables
1. **`account`**: Stores account details.
2. **`user`**: Stores user details.
3. **`account_user`**: Stores the association between accounts and users.
4. **`transaction`**: Stores transaction details.

### Relationships
- **`AccountUser`**: Uses a composite key (`AccountUserId`) to link `Account` and `User`.
- **`Transaction`**: Is linked to `Account`.


