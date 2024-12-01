package Controller;

import java.sql.*;

public class ShowtimeController {
    private final Connection connection;

    // Constructor to initialize the database connection
    public ShowtimeController(Connection connection) {
        this.connection = connection;
    }

    // Method to get the showtimes for a particular movie
    public String[] getShowtimeInfo(int id) throws SQLException {
        String query = "SELECT * FROM showtimes WHERE movie_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);  // Set the movie_id parameter
    
        ResultSet results = statement.executeQuery(); // Execute the query and get results
    
        // Assuming you want to return an array of strings with showtime information
        if (results.next()) {
            String[] showtimeInfo = new String[2];  // Adjust the size based on the number of columns you need
            showtimeInfo[0] = results.getString("showtime_id");
            showtimeInfo[1] = results.getString("start_time");  // Or any other column you need
    
            return showtimeInfo;
        } else {
            return null;  // Return null if no results are found
        }
    }
    
}