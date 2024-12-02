
# MovieApp - README

## 1. Database Setup
    - Install and configure a MySQL server on your system
    - Login: 
        mysql -u username -p
    - Save the DATABASE.sql file
    - Run this command:
        SOURCE path/to/DATABASE.sql;

## 2. Download Zip File of This Repository
    - Download ALL files
    - Naviagte to repo in your IDE terminal or shell

## 3. Compile and Execute the Project:
    - Navigate to the project root directory: 
        cd MovieApp/src
    - Run the following commands to compile the application:  
        javac -cp ".;../lib/mysql-connector-j-9.1.0.jar" App.java Boundary/*.java Controller/MovieController.java
    - Run the compiled application:
        java -cp ".;../lib/mysql-connector-j-9.1.0.jar" App

     
        
