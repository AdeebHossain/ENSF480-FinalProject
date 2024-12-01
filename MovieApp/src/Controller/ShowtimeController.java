package Controller;

import Entities.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowtimeController {
    private final Connection connection;

    // Constructor to initialize the database connection
    public ShowtimeController(Connection connection) {
        this.connection = connection;
    }

    public Showtime addShowtime(Movie movie, String startTime) throws SQLException {
        String insertQuery = "INSERT INTO Showtime (movie_ID, start_time, is_active) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            // Set parameters
            statement.setInt(1, movie.getMovie_ID());
            statement.setString(2, startTime);
            statement.setBoolean(3, true); // Assume the showtime is active by default

            // Execute the query
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                // Retrieve the generated showtime_ID
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int showtimeID = generatedKeys.getInt(1);
                        return new Showtime(showtimeID, movie, startTime);
                    } else {
                        throw new SQLException("Failed to add showtime; no ID obtained.");
                    }
                }
            } else {
                throw new SQLException("Failed to add showtime; no rows affected.");
            }
        }
    }

    public void removeShowtime(Showtime showtime) throws SQLException {
        String deleteQuery = "DELETE FROM Showtime WHERE showtime_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            // Set parameter
            statement.setInt(1, showtime.getShowtimeId());

            // Execute the query
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No showtime found with ID: " + showtime.getShowtimeId());
            }
        }
    }

    public List<Showtime> getShowtimesForMovie(Movie movie) throws SQLException {
        String selectQuery = "SELECT * FROM Showtime WHERE movie_ID = ? AND is_active = true"; // Only active showtimes
        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            // Set parameter
            statement.setInt(1, movie.getMovie_ID());

            // Execute the query and process results
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Showtime> showtimes = new ArrayList<>();
                while (resultSet.next()) {
                    int showtimeID = resultSet.getInt("showtime_ID");
                    String startTime = resultSet.getString("start_time");
                    boolean isActive = resultSet.getBoolean("is_active");

                    showtimes.add(new Showtime(showtimeID, movie, startTime));
                }
                return showtimes;
            }
        }
    }
}