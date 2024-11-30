package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
    public static void main(String[] args) {
        // Define database credentials
        String url = "jdbc:mysql://localhost:3306/movietheatredb"; 
        String username = "root";
        String password = "P@$$w0rd123";

        // SQL statements to initialize the database and tables
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS MovieTheatreDB";
        String useDatabaseSQL = "USE MovieTheatreDB";
        String createTablesSQL = "CREATE TABLE IF NOT EXISTS Name ("
                + "name_ID INT AUTO_INCREMENT PRIMARY KEY, "
                + "firstName VARCHAR(50) NOT NULL, "
                + "middleName VARCHAR(50), "
                + "lastName VARCHAR(50) NOT NULL);"
                + "CREATE TABLE IF NOT EXISTS User ("
                + "user_ID INT AUTO_INCREMENT PRIMARY KEY, "
                + "name_ID INT NOT NULL, "
                + "username VARCHAR(50) UNIQUE NOT NULL, "
                + "FOREIGN KEY (name_ID) REFERENCES Name(name_ID) ON DELETE CASCADE);"
                + "CREATE TABLE IF NOT EXISTS RegisteredUser ("
                + "registered_user_ID INT AUTO_INCREMENT PRIMARY KEY, "
                + "user_ID INT NOT NULL, "
                + "password VARCHAR(255) NOT NULL, "
                + "email VARCHAR(255) UNIQUE NOT NULL, "
                + "address VARCHAR(255), "
                + "annual_fee DECIMAL(10, 2) DEFAULT 20.00, "
                + "has_discount BOOLEAN DEFAULT TRUE, "
                + "FOREIGN KEY (user_ID) REFERENCES User(user_ID) ON DELETE CASCADE);"
                + "CREATE TABLE IF NOT EXISTS OrdinaryUser ("
                + "ordinary_user_ID INT AUTO_INCREMENT PRIMARY KEY, "
                + "user_ID INT NOT NULL, "
                + "isRegistered BOOLEAN DEFAULT FALSE, "
                + "FOREIGN KEY (user_ID) REFERENCES User(user_ID) ON DELETE CASCADE);"
                + "CREATE TABLE IF NOT EXISTS DatabaseAdmin ("
                + "admin_ID INT AUTO_INCREMENT PRIMARY KEY, "
                + "registered_user_ID INT NOT NULL, "
                + "FOREIGN KEY (registered_user_ID) REFERENCES RegisteredUser(registered_user_ID) ON DELETE CASCADE);"
                + "CREATE TABLE IF NOT EXISTS BankCardInfo ("
                + "card_ID INT AUTO_INCREMENT PRIMARY KEY, "
                + "card_number VARCHAR(16) UNIQUE NOT NULL, "
                + "expiry_date DATE NOT NULL, "
                + "cvv INT NOT NULL, "
                + "registered_user_ID INT NOT NULL, "
                + "FOREIGN KEY (registered_user_ID) REFERENCES RegisteredUser(registered_user_ID) ON DELETE CASCADE);"
                + "CREATE TABLE IF NOT EXISTS Movie ("
                + "movie_ID INT AUTO_INCREMENT PRIMARY KEY, "
                + "movie_title VARCHAR(255) NOT NULL, "
                + "movie_genre VARCHAR(50), "
                + "movie_summary TEXT);"
                + "CREATE TABLE IF NOT EXISTS Announcement ("
                + "announcement_ID INT AUTO_INCREMENT PRIMARY KEY, "
                + "creation_date DATE NOT NULL, "
                + "release_date DATE NOT NULL, "
                + "movie_ID INT NOT NULL, "
                + "FOREIGN KEY (movie_ID) REFERENCES Movie(movie_ID) ON DELETE CASCADE);"
                + "CREATE TABLE IF NOT EXISTS Seat ("
                + "seat_ID INT AUTO_INCREMENT PRIMARY KEY, "
                + "seat_row CHAR(1) NOT NULL, "
                + "seat_number INT NOT NULL, "
                + "is_available BOOLEAN DEFAULT TRUE);"
                + "CREATE TABLE IF NOT EXISTS Showtime ("
                + "showtime_ID INT AUTO_INCREMENT PRIMARY KEY, "
                + "movie_ID INT NOT NULL, "
                + "start_time DATETIME NOT NULL, "
                + "is_active BOOLEAN DEFAULT TRUE, "
                + "FOREIGN KEY (movie_ID) REFERENCES Movie(movie_ID) ON DELETE CASCADE);"
                + "CREATE TABLE IF NOT EXISTS Booking ("
                + "booking_ID INT AUTO_INCREMENT PRIMARY KEY, "
                + "user_ID INT NOT NULL, "
                + "booking_date DATETIME NOT NULL, "
                + "isCancelled BOOLEAN DEFAULT FALSE, "
                + "FOREIGN KEY (user_ID) REFERENCES User(user_ID) ON DELETE CASCADE);"
                + "CREATE TABLE IF NOT EXISTS Ticket ("
                + "ticket_ID INT AUTO_INCREMENT PRIMARY KEY, "
                + "booking_ID INT NOT NULL, "
                + "showtime_ID INT NOT NULL, "
                + "seat_ID INT NOT NULL, "
                + "FOREIGN KEY (booking_ID) REFERENCES Booking(booking_ID) ON DELETE CASCADE, "
                + "FOREIGN KEY (showtime_ID) REFERENCES Showtime(showtime_ID) ON DELETE CASCADE, "
                + "FOREIGN KEY (seat_ID) REFERENCES Seat(seat_ID) ON DELETE CASCADE);"
                + "CREATE TABLE IF NOT EXISTS Payment ("
                + "payment_ID INT AUTO_INCREMENT PRIMARY KEY, "
                + "ticket_ID INT NOT NULL, "
                + "amount DECIMAL(10, 2) NOT NULL, "
                + "payment_method VARCHAR(50), "
                + "payment_status BOOLEAN DEFAULT TRUE, "
                + "FOREIGN KEY (ticket_ID) REFERENCES Ticket(ticket_ID) ON DELETE CASCADE);"
                + "CREATE TABLE IF NOT EXISTS Receipt ("
                + "receipt_ID INT AUTO_INCREMENT PRIMARY KEY, "
                + "ticket_ID INT NOT NULL, "
                + "numTickets INT NOT NULL, "
                + "total DECIMAL(10, 2) NOT NULL, "
                + "date DATE NOT NULL, "
                + "user VARCHAR(255), "
                + "FOREIGN KEY (ticket_ID) REFERENCES Ticket(ticket_ID) ON DELETE CASCADE);";
        

        // Establish connection to the database
        try (Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement()) {
             // Create the database if it doesn't exist
             statement.executeUpdate(createDatabaseSQL);
             statement.executeUpdate(useDatabaseSQL);
             // Create the tables if they don't exist
             statement.executeUpdate(createTablesSQL);
             System.out.println("Database and tables initialized successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
