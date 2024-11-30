package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
    public static void main(String[] args) {
        // Define database credentials
        String url = "jdbc:mysql://localhost:3306/movietheatredb"; 
        String username = "root";
        String password = "P@$$w0rd123";

        // Establish connection to the database
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the database");

            // Create a statement and execute a query
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM your_table_name"; // Example query
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result set
            while (resultSet.next()) {
                // Example of accessing data from the result set
                System.out.println(resultSet.getString("column_name")); // Replace with actual column names
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
