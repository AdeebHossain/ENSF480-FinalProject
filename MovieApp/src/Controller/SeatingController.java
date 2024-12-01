package Controller;

import Entities.Seat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatingController {

    private final Connection connection;

    // Constructor to initialize the database connection
    public SeatingController(Connection connection) {
        this.connection = connection;
    }

    // Fetch available seats for a specific showtime
    public List<Seat> getAvailableSeats(int showtimeId) {
        List<Seat> availableSeats = new ArrayList<>();
        String query = "SELECT s.seat_ID, s.seat_row, s.seat_number " +
                       "FROM Seat s " +
                       "JOIN Ticket t ON s.seat_ID = t.seat_ID " +
                       "WHERE t.showtime_ID = ? AND s.is_available = TRUE";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, showtimeId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Seat seat = new Seat(
                    resultSet.getInt("seat_ID"),
                    resultSet.getString("seat_row").charAt(0),
                    resultSet.getInt("seat_number"),
                    true
                );
                availableSeats.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableSeats;
    }

    // Reserve a seat for a user (update seat availability)
    public boolean reserveSeat(int seatId, int userId) {
        String updateQuery = "UPDATE Seat SET is_available = FALSE WHERE seat_ID = ?";
        String insertTicketQuery = "INSERT INTO Ticket (seat_ID, booking_ID, showtime_ID) " +
                                   "VALUES (?, ?, ?)";

        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
             PreparedStatement insertStatement = connection.prepareStatement(insertTicketQuery)) {

            // Step 1: Update seat availability
            updateStatement.setInt(1, seatId);
            int rowsUpdated = updateStatement.executeUpdate();

            if (rowsUpdated == 0) {
                System.out.println("Seat reservation failed: Seat may already be reserved.");
                return false;
            }

            // Step 2: Insert ticket information (link user to the seat)
            insertStatement.setInt(1, seatId);
            insertStatement.setInt(2, userId);
            insertStatement.setInt(3, /* Pass the appropriate showtime ID here */ 1); // Example showtime ID
            insertStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Release a seat (set it back to available if a booking is cancelled)
    public void releaseSeat(int seatId) {
        String updateQuery = "UPDATE Seat SET is_available = TRUE WHERE seat_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setInt(1, seatId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch all seats (for administrative purposes or seat map visualization)
    public List<Seat> getAllSeats() {
        List<Seat> seats = new ArrayList<>();
        String query = "SELECT seat_ID, seat_row, seat_number, is_available FROM Seat";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Seat seat = new Seat(
                    resultSet.getInt("seat_ID"),
                    resultSet.getString("seat_row").charAt(0),
                    resultSet.getInt("seat_number"),
                    resultSet.getBoolean("is_available")
                );
                seats.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seats;
    }
}
