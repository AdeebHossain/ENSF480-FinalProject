package Controller;

import java.sql.*;

public class SeatingController {

    private final MySQLConnection connection;

    // Constructor to initialize the database connection via MySQLConnection singleton
    public SeatingController() {
        this.connection = MySQLConnection.getInstance();
    }

    // Method to get the seating arrangement and availability for all seats (without using theatreId)
    public void getSeatsAvailability() {
        String query = "SELECT row, col, reserved FROM seats";
        try (ResultSet rs = connection.query(query)) {
            while (rs.next()) {
                String row = rs.getString("row");
                int col = rs.getInt("col");
                boolean isReserved = rs.getInt("reserved") == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
