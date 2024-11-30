package Controller;

import Entities.*;
import java.sql.*;
import java.util.List;

public class BookingController {

    private final Connection connection;
    private int bookingId = 0;

    // Constructor to initialize the database connection
    public BookingController(Connection connection)  {
        this.connection = connection;
    }

    public Booking createBooking(User user, List<Ticket> tickets, Payment payment) throws SQLException{
        this.bookingId = bookingId + 1;  // Generate a unique booking ID
        
        // Insert the booking into the database
        String insertBookingQuery = "INSERT INTO Booking (user_ID, booking_date, isCancelled) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertBookingQuery, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getUsername());
        statement.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis())); 
        statement.setBoolean(3, false); // Initially not cancelled
        statement.executeUpdate();

        // Insert ticket details
        for (Ticket ticket : tickets) {
            String insertTicketQuery = "INSERT INTO Ticket (booking_ID, showtime_ID, seat_ID) VALUES (?, ?, ?)";
            PreparedStatement ticketStatement = connection.prepareStatement(insertTicketQuery);
            ticketStatement.setInt(1, bookingId);
            ticketStatement.setInt(2, ticket.getShowtime().getShowtimeId()); // Assuming Ticket class has a getShowtime() method
            ticketStatement.setString(3, ticket.getSeatNumber()); // Assuming Seat class has a getSeatId() method
            ticketStatement.executeUpdate();
        }


        // Process payment (assuming Payment class has getTicketId(), getAmount(), etc.)
        String insertPaymentQuery = "INSERT INTO Payment (ticket_ID, amount, payment_method, payment_status) VALUES (?, ?, ?, ?)";
        PreparedStatement paymentStatement = connection.prepareStatement(insertPaymentQuery);
        paymentStatement.setInt(1, payment.ticket.getTicket_number());
        paymentStatement.setDouble(2, payment.getAmount());
        paymentStatement.setString(3, payment.getPaymentMethod());
        paymentStatement.setBoolean(4, payment.isPaymentStatus());
        paymentStatement.executeUpdate();

        // Return a new Booking object with the created details
        return new Booking(bookingId, user, tickets, new java.util.Date()); // Booking created with current date

    }

    public void cancelBooking(Booking booking) {
        if (!booking.isCancelled()) {
            booking.cancelBooking(); // Call the cancelBooking method on Booking class

            // Update booking status in database
            try {
                String updateBookingQuery = "UPDATE Booking SET isCancelled = ? WHERE booking_ID = ?";
                PreparedStatement statement = connection.prepareStatement(updateBookingQuery);
                statement.setBoolean(1, true);
                statement.setInt(2, booking.getBookingId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addTicketsToBooking(Booking booking, List<Ticket> newTickets) {
        try {
            // Add new tickets to the database
            for (Ticket ticket : newTickets) {
                String insertTicketQuery = "INSERT INTO Ticket (booking_ID, showtime_ID, seat_ID) VALUES (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(insertTicketQuery);
                statement.setInt(1, booking.getBookingId());
                statement.setInt(2, ticket.getShowtime().getShowtimeId()); // Assuming Showtime class has a getShowtimeId() method
                statement.setString(3, ticket.getSeatNumber()); // Assuming Seat class has a getSeatId() method
                statement.executeUpdate();
            }

            // Add tickets to the booking's list
            booking.getTickets().addAll(newTickets);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Receipt generateReceipt(Booking booking) {
        double totalAmount = 0;

        // Calculate the total amount for the tickets in the booking
        for (Ticket ticket : booking.getTickets()) {
            totalAmount += 25; 
        }

        // Create a new Receipt object
        Receipt receipt;
        receipt = new Receipt(booking.getBookingId(), booking.getTickets().size(), totalAmount, new java.util.Date(), booking.getUser().getUsername()); 
        return receipt;
    }
}
