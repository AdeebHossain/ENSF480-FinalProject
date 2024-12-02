package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowtimeController {
    private MySQLConnection connection;

    // Constructor to initialize the database connection
    public ShowtimeController() {
        connection = MySQLConnection.getInstance();
    }

    public String[] getShowtimeInfo(int id) {
        System.out.println("Executing getShowtimeInfo for movie ID: " + id); // Debug: Starting method
        String query = "SELECT time FROM showtimes WHERE movie_id = ?";
        ResultSet results = null;
        List<String> showtimeList = new ArrayList<>();
    
        try {
            results = connection.query(query, id); // Debug: Executing query
            System.out.println("Query executed: " + query); // Debug: Query execution
            while (results.next()) {
                System.out.println("Processing result set..."); // Debug: Processing result set
                // Get the 'time' as a Timestamp and format it as a string
                java.sql.Timestamp showtime = results.getTimestamp("time");
                System.out.println("Retrieved showtime from DB: " + showtime); // Debug: Retrieved raw showtime
                String formattedShowtime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(showtime);
                System.out.println("Formatted showtime: " + formattedShowtime); // Debug: Formatted showtime
                showtimeList.add(formattedShowtime); // Add formatted showtime to the list
            }
        } catch (SQLException e) {
            System.err.println("SQLException encountered: " + e.getMessage()); // Debug: SQL Exception
            e.printStackTrace();
        } finally {
            try {
                if (results != null) {
                    results.close(); // Close the ResultSet after use
                    System.out.println("ResultSet closed successfully."); // Debug: ResultSet closed
                }
            } catch (SQLException e) {
                System.err.println("Error closing ResultSet: " + e.getMessage()); // Debug: Error closing ResultSet
                e.printStackTrace();
            }
        }
    
        System.out.println("Returning showtime info as an array."); // Debug: Returning the result
        // Convert List to array and return
        return showtimeList.toArray(new String[0]);
    }
    
}