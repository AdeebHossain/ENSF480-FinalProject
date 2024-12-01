package Controller;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.sql.SQLException;

public class TicketController {

    private final MySQLConnection connection;

    // Default Constructor
    public TicketController() {
        connection = MySQLConnection.getInstance();
    }

    // Method to add a ticket to the database
    public boolean addTicket(String userEmail, int seatId, int theatreId, double ticketPrice, String showtime) {
        String query = "INSERT INTO tickets (user_email, seat_id, purchased_date, theatre_id, ticket_price, showtime) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            // Get the current date in YYYY-MM-DD format
            String currentDate = LocalDate.now().toString();

            // Execute the query
            connection.execute(query, userEmail, seatId, currentDate, theatreId, ticketPrice, showtime);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to remove a ticket from the database
    public boolean removeTicket(int ticketId) {
        String query = "DELETE FROM tickets WHERE ticket_id = ?";
        try {
            connection.execute(query, ticketId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get ticket information by ticket ID
    public String[] getTicketInfo(int ticketId) {
        String query = "SELECT * FROM tickets WHERE ticket_id = ?";
        ResultSet results = connection.query(query, ticketId);
        try {
            if (results.next()) {
                String userEmail = results.getString("user_email");
                int seatId = results.getInt("seat_id");
                String purchaseDate = results.getString("purchased_date");
                int theatreId = results.getInt("theatre_id");
                double ticketPrice = results.getDouble("ticket_price");
                String showtime = results.getString("showtime");
                return new String[]{userEmail, String.valueOf(seatId), purchaseDate, String.valueOf(theatreId), String.valueOf(ticketPrice), showtime};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Additional utility method: Get all tickets for a user
    public ResultSet getUserTickets(String userEmail) {
        String query = "SELECT * FROM tickets WHERE user_email = ?";
        return connection.query(query, userEmail);
    }
}
