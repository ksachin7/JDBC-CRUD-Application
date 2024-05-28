# JDBC CRUD Application
This project is a simple Java application that demonstrates CRUD (Create, Read, Update, Delete) operations on a MySQL database using JDBC (Java Database Connectivity). The application includes functionalities for inserting, updating, deleting, and batch processing of employee records.

JDBC provides a set of interfaces and classes for Java applications to interact with databases. It allows Java programs to establish connections to databases, execute SQL queries and statements, process query results, and handle transactions. With JDBC, developers can perform CRUD operations to manage data stored in relational databases from their Java applications.

## Development Environment and Dependencies

**IDE:** IntelliJ IDEA  
**Java Version:** Java Development Kit (JDK) 11     
**Database:** MySQL 8.0     
**JDBC Driver:** MySQL Connector/J 8.0.33 (mysql-connector-java-8.0.33.jar)     
**Logging:** Java Util Logging (JUL)    

## Steps to set up project

### Step 1: Set Up Database
1. Install MySQL database server if not already installed.
2. Create a new database schema (e.g., `exampleDB`) using MySQL command line or a GUI tool like MySQL Workbench.
3. Create an Employee table within the database with columns `id` (INT AUTO_INCREMENT), `name` (VARCHAR), `email` (VARCHAR UNIQUE), and `country` (VARCHAR).
4. Optionally, insert some sample data into the Employee table.

### Step 2: Set Up Java Project
1. Create a new Java project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
2. Add the MySQL JDBC driver to your project's dependencies. You can download the MySQL Connector/J JAR file from the MySQL website or use a dependency management tool like Maven or Gradle.
    
    **To add MySQL Connector/J to IntelliJ IDEA:**
    ```
    Download mysql-connector-java-8.0.33.jar from the official MySQL website.
    Add the jar file to your project by navigating to File -> Project Structure -> Libraries -> + -> Java and selecting the downloaded jar file.
    ```

### Step 3: Create Employee Class
1. Create a Java class named `Employee` with fields corresponding to the columns in the Employee table (id, name, email, country).
2. Generate constructors, getters, and setters for the class.

### Step 4: Create Database Connection Class
1. Create a Java class named `DatabaseConnection` to manage database connections.
2. Implement a static method `getConnection()` to establish a connection to the MySQL database using the JDBC URL, username, and password.
3. Optionally, implement a method to initialize the database schema if needed.

### Step 5: Create Employee DAO Class
1. Create a Java class named `EmployeeDAO` to handle CRUD operations on the Employee table.
2. Implement methods to perform CRUD operations such as `insertEmployee`, `getAllEmployees`, `updateEmployee`, and `deleteEmployee`.
3. Use `PreparedStatement` to execute SQL queries and handle exceptions appropriately.

### Step 6: Create Main Class
1. Create a Java class named `Main` as the entry point of the application.
2. In the `Main` class, create an instance of `EmployeeDAO` and use its methods to perform CRUD operations.
3. Call methods to insert, retrieve, update, and delete employees as needed.

### Step 7: Test the Application
1. Run the `Main` class to test the JDBC application.
2. Verify that CRUD operations are executed successfully and that data is inserted, retrieved, updated, and deleted from the Employee table as expected.

### Step 8: Handle Exceptions and Close Resources
1. Implement proper exception handling to catch and handle SQL exceptions.
2. Close database resources (e.g., Connection, Statement, ResultSet) in a `finally` block or using try-with-resources to ensure proper resource management and prevent memory leaks.

By following these steps, you can create a basic JDBC application to interact with a MySQL database and perform CRUD operations on an Employee table.

### **Output**
```java
[Employee{id=1, name='John Doe', email='john.doe@example.com', country='USA'}, Employee{id=2, name='Jane Smith', email='jane.smith@example.com', country='UK'}, Employee{id=3, name='John', email='abc@gmail.com', country='USA'}, Employee{id=4, name='John D.', email='emp@gmail.com', country='London'}]
```

```java
Employee with ID 3 updated successfully.
After update:
        +----+--------------+---------------------------+---------------+
        | ID | Name         | Email                     | Country       |
        +----+--------------+---------------------------+---------------+
        |  1 | John Doe     |      john.doe@example.com | USA           |
        |  2 | Jane Smith   |    jane.smith@example.com | UK            |
        |  3 | John Snow    |     emp.updated@gmail.com | London        |
        |  4 | John D.      |             emp@gmail.com | London        |
        +----+--------------+---------------------------+---------------+

Employee with name 'Jane Smith' updated successfully.
        +----+--------------+---------------------------+---------------+
        | ID | Name         | Email                     | Country       |
        +----+--------------+---------------------------+---------------+
        |  1 | John Doe     |      john.doe@example.com | USA           |
        |  2 | Jane Doe     |        jane.doe@gmail.com | New York      |
        |  3 | John Snow    |     emp.updated@gmail.com | London        |
        |  4 | John D.      |             emp@gmail.com | London        |
        +----+--------------+---------------------------+---------------+

Employee with email 'emp@gmail.com' updated successfully.
        +----+--------------+---------------------------+---------------+
        | ID | Name         | Email                     | Country       |
        +----+--------------+---------------------------+---------------+
        |  1 | John Doe     |      john.doe@example.com | USA           |
        |  2 | Jane Doe     |        jane.doe@gmail.com | New York      |
        |  3 | John Snow    |     emp.updated@gmail.com | London        |
        |  4 | Jane Doe     |         emp.new@gmail.com | New York      |
        +----+--------------+---------------------------+---------------+

After delete:
        +----+--------------+---------------------------+---------------+
        | ID | Name         | Email                     | Country       |
        +----+--------------+---------------------------+---------------+
        |  1 | John Doe     |      john.doe@example.com | USA           |
        |  2 | Jane Doe     |        jane.doe@gmail.com | New York      |
        |  3 | John Snow    |     emp.updated@gmail.com | London        |
        +----+--------------+---------------------------+---------------+

May 28, 2024 10:47:14 AM EmployeeDAO batchInsertEmployees
INFO: Batch executed successfully. Inserted 4 records.
        +----+--------------+---------------------------+---------------+
        | ID | Name         | Email                     | Country       |
        +----+--------------+---------------------------+---------------+
        |  1 | John Doe     |      john.doe@example.com | USA           |
        |  2 | Jane Doe     |        jane.doe@gmail.com | New York      |
        |  3 | John Snow    |     emp.updated@gmail.com | London        |
        |  5 | Emily Davis  |         emily@example.com | Australia     |
        |  6 | David Brown  |         david@example.com | Germany       |
        |  7 | Laura Wilson |         laura@example.com | France        |
        |  8 | Mike Johnson |          mike@example.com | Canada        |
        +----+--------------+---------------------------+---------------+

```