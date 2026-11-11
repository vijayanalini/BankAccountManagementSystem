BANK ACCOUNT MANAGEMENT SYSTEM

It is a console-based Bank Management System developed
using Java, JDBC, and MySQL.

This Project allows users to create bank account,
deposit money, withdraw money, and check account 
balance. It uses JDBC to connect Java application
with a MySQL database.

KEY FEATURES:
1. Account Creation: Allows users to create a new bank
account with account number, account holder name and 
initial balance.
2. Deposit Money: Enable the users to deposit money into an existing account.
3. Withdraw Money: Allows users to withdraw money while 
checking the available balance.
4. Balance Inquiry: Display the account holder's name and 
their current account balance.
5. Input Validation: Validates user inputs and prevents
invalid amounts and duplicate account numbers.
6. Database Integration: Stores and retrieves the account
information using MySQL through JDBC.

TECHNOLOGIES USED
1. Java: It is used to develop the application logic and 
implement the operations.
2. JDBC: It is used to establish the connectivity between 
the Java application and MySQL database.
3. MySQL: Used to store and manage bank account details.
4. Maven: Used for project configurations and dependency
management.

DATABASE SETUP
1. Open MySQL workbench or MySQL command line.
2. Execute the following SQL commands to create the 
database and table.

        CREATE DATABASE bank_db;
        USE bank_db;
        
        CREATE TABLE accounts (
        account_number VARCHAR(30) PRIMARY KEY,
        account_holder VARCHAR(50) NOT NULL,
        balance DECIMAL(12, 2) NOT NULL CHECK(balance>=0)
        );

DATABASE CONFIGURATION

Before running the application, update the database 
credentials in DBConnection.java.
1. Set your MySQL username in DB_USER.
2. Set your MySQL password in DB_PASS.
3. Make sure the MySQL server is running and the bank_db
database is created.

HOW TO RUN THE PROJECT
1. Clone or download this repository.
2. Open the project in any Java IDE.
3. Configure the MySQL database credentials in DBConnection.java.
4. Make sure the MySQL database and accounts table are created.
5. Run the AccountDAO.java file.
6. Follow the console menu to perform banking operations.

PROJECT STRUCTURE
1. Account.java: Represents a bank account and contains account 
details and basic banking operations.
2. AccountDAO.java: Handles database operations such as account 
creation, deposits, withdrawals, and balance inquiries.
3. DBConnection.java: Establishes a connection between the Java 
application and the MySQL database.
4. pom.xml: Manages project dependencies using Maven.

FUTURE ENHANCEMENT

1. Implement account deletion functionality.
2. Add transaction history to track deposits and withdrawals.
3. Introduce user authentication for secure account access.
4. Generate transaction receipts for successful deposits and withdrawals.
