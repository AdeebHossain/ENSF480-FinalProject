package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowtimeController {
    private  MySQLConnection connection;

    // Constructor to initialize the database connection
    public ShowtimeController() {
        connection = MySQLConnection.getInstance();
    }

    public String[] getShowtimeInfo(int id){
        String query = "SELECT time FROM showtimes WHERE movie_id = ?";
        ResultSet results = connection.query(query, id);
        List<String> showtimeList = new ArrayList<>();

        try {
            while (results.next()) {
                // Get the 'time' as a Timestamp and format it as a string
                java.sql.Timestamp showtime = results.getTimestamp("time");
                String formattedShowtime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(showtime);
                showtimeList.add(formattedShowtime);  // Add formatted showtime to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (results != null) {
                    results.close();  // Close the ResultSet after use
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Convert List to array and return
        return showtimeList.toArray(new String[0]);
    }
    
}