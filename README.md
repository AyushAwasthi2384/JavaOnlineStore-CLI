# E-Commerce Store Application

Welcome to the E-Commerce Store Application! This project is a Java-based console application that allows users to manage products, customers, and orders. This README will guide you step-by-step to set up and run the application on your local machine.

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Installing Java](#installing-java)
3. [Installing Maven](#installing-maven)
4. [Setting Up the Database](#setting-up-the-database)
5. [Cloning the Repository](#cloning-the-repository)
6. [Building the Project](#building-the-project)
7. [Running the Application](#running-the-application)
8. [Using the Application](#using-the-application)
9. [Troubleshooting](#troubleshooting)

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)**: This is necessary to run Java applications.
- **Apache Maven**: This is a build automation tool for Java projects.
- **MySQL**: This will be used as the database for the application.

## Installing Java

1. **Download JDK**:
   - Go to the [Oracle JDK download page](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
   - Select your operating system and download the installer.

2. **Install JDK**:
   - Run the installer and follow the instructions.
   - Set the JAVA_HOME environment variable:
     - On Windows: 
       - Right-click on 'This PC' or 'My Computer', and choose 'Properties'.
       - Click on 'Advanced system settings' and then 'Environment Variables'.
       - Under 'System variables', click 'New' and set:
         - Variable name: `JAVA_HOME`
         - Variable value: `C:\Program Files\Java\jdk-11.x.x` (replace with your JDK installation path).
     - On macOS/Linux:
       - Open your terminal and add the following line to your `~/.bash_profile` or `~/.bashrc` file:
         ```bash
         export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11.x.x.jdk/Contents/Home
         ```
       - Replace the path with your actual JDK installation path.

3. **Verify Installation**:
   - Open your command prompt or terminal and type:
     ```bash
     java -version
     ```
   - You should see the version of Java installed.

## Installing Maven

1. **Download Maven**:
   - Go to the [Maven download page](https://maven.apache.org/download.cgi).
   - Download the binary zip archive.

2. **Install Maven**:
   - Extract the downloaded zip file to a directory of your choice (e.g., `C:\Program Files\Apache\maven`).
   - Set the MAVEN_HOME environment variable:
     - On Windows:
       - Follow similar steps as setting `JAVA_HOME`, but set:
         - Variable name: `MAVEN_HOME`
         - Variable value: `C:\Program Files\Apache\maven` (or wherever you extracted Maven).
     - On macOS/Linux:
       - Add the following line to your `~/.bash_profile` or `~/.bashrc` file:
         ```bash
         export MAVEN_HOME=/path/to/your/maven
         export PATH=$MAVEN_HOME/bin:$PATH
         ```

3. **Verify Installation**:
   - Open your command prompt or terminal and type:
     ```bash
     mvn -version
     ```
   - You should see the version of Maven installed.

## Setting Up the Database

1. **Install MySQL**:
   - Download and install MySQL from the [MySQL Community Server download page](https://dev.mysql.com/downloads/mysql/).

2. **Create a Database**:
   - Open the MySQL command line or MySQL Workbench.
   - Run the following commands:
     ```sql
     CREATE DATABASE store_management;
     USE store_management;

     CREATE TABLE customers (
         customer_id INT AUTO_INCREMENT PRIMARY KEY,
         name VARCHAR(100) NOT NULL,
         email VARCHAR(100) NOT NULL,
         phone VARCHAR(15) NOT NULL
     );

     CREATE TABLE products (
         product_id INT AUTO_INCREMENT PRIMARY KEY,
         name VARCHAR(100) NOT NULL,
         category VARCHAR(100) NOT NULL,
         price DOUBLE NOT NULL,
         stock_quantity INT NOT NULL
     );

     CREATE TABLE orders (
         order_id INT AUTO_INCREMENT PRIMARY KEY,
         customer_id INT,
         order_date DATETIME,
         FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
     );

     CREATE TABLE order_items (
         order_item_id INT AUTO_INCREMENT PRIMARY KEY,
         order_id INT,
         product_id INT,
         quantity INT,
         price DOUBLE,
         FOREIGN KEY (order_id) REFERENCES orders(order_id),
         FOREIGN KEY (product_id) REFERENCES products(product_id)
     );
     ```

## Cloning the Repository

1. **Clone the Project**:
   - Open your command prompt or terminal.
   - Navigate to the directory where you want to clone the project and run:
     ```bash
     https://github.com/AyushAwasthi2384/JavaOnlineStore-CLI.git
     ```

## Building the Project

1. **Build the Project**:
   ```bash
   mvn clean install
   ```
   - This will download the necessary dependencies and build the project.

## Running the Application

1. **Run the Application**:
   ```bash
   mvn exec:java -Dexec.mainClass="com.store.App"
   ```
   - This command will execute the `App` class, which is the entry point of the application.

## Using the Application

1. **Follow the Prompts**:
   - The application will present a menu with options to add products, update stock, place orders, and fetch customer orders.
   - Enter your choice by typing the corresponding number and follow the on-screen instructions.

## Troubleshooting

- **MySQL Connection Issues**: Ensure that your MySQL server is running and the connection settings in your `DatabaseUtility` class are correct.
- **Dependency Issues**: Make sure you have a stable internet connection when building the project, as Maven will download required dependencies.

If you encounter any other issues, please feel free to ask for help.

## License

This project is open-source and available under the MIT License.

## Credits

- ### Development: [AYUSH AWASTHI🚀](https://github.com/AyushAwasthi2384/)
- ### Documentation: [AYUSH AWASTHI🚀](https://github.com/AyushAwasthi2384/)
