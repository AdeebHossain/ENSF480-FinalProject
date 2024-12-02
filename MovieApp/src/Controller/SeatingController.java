package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatingController {

    private final MySQLConnection connection;

    // Constructor to initialize the database connection via MySQLConnection singleton
    public SeatingController() {
        connection = MySQLConnection.getInstance();
    }

    // Method to get the seating arrangement and availability for all seats (without using theatreId)
    public List<String[]> getSeatsAvailability() {
        String query = "SELECT row, col, reserved FROM seats";
        List<String[]> seatAvailability = new ArrayList<>();

        try (ResultSet rs = connection.query(query)) {
            while (rs.next()) {
                String row = rs.getString("row");
                int col = rs.getInt("col");
                boolean isReserved = rs.getInt("reserved") == 1;

                // Convert the boolean reserved status to a string representation
                String status = isReserved ? "Reserved" : "Available";

                // Add the seat info as an array to the list
                seatAvailability.add(new String[]{row, String.valueOf(col), status});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seatAvailability;
    }

    // Method to reserve a seat based on row and column
    public boolean reserveSeat(String row, int col) {
        String query = "UPDATE seats SET reserved = 1 WHERE row = ? AND col = ?";
        
        try {
            connection.execute(query, row, col);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }

    // Method to release a seat based on row and column
    public boolean releaseSeat(int seatId) {
        String query = "UPDATE seats SET reserved = 0 WHERE seat_id = ?";
        
        try {
            connection.execute(query, seatId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
