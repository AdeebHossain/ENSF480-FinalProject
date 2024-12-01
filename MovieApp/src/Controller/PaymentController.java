package Controller;

import Entities.*;
import java.sql.*;
import java.util.Date;

public class PaymentController {

    private final Connection connection;

    // Constructor to initialize the database connection
    public PaymentController(Connection connection) {
        this.connection = connection;
    }

    // Process a payment and save it to the database
    public Payment processPayment(User user, Ticket ticket, double amount, String paymentMethod) throws SQLException {
        // Validate card details for card payments
        if (paymentMethod.equalsIgnoreCase("Card")) {
            if (!isValidCard(user.getUsername())) {
                throw new IllegalArgumentException("User does not have valid card information on file.");
            }
        }

        // Insert payment details into the database
        String insertPaymentQuery = "INSERT INTO Payment (ticket_ID, amount, payment_method, payment_status) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertPaymentQuery, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, ticket.getTicket_number());
        statement.setDouble(2, amount);
        statement.setString(3, paymentMethod);
        statement.setBoolean(4, true); // Assume payment is successful
        statement.executeUpdate();

        // Retrieve the generated payment ID
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int paymentId = generatedKeys.getInt(1);

            // Create and return a Payment object
            Payment payment = new Payment(paymentId, amount, paymentMethod, new Date(), true);
            payment.setTicket(ticket); // Associate the ticket with the payment
            return payment;
        } else {
            throw new SQLException("Failed to process payment, no ID obtained.");
        }
    }

    // Refund a payment in case of ticket cancellation
    public void refundPayment(Payment payment) throws SQLException {
        if (!payment.isPaymentStatus()) {
            throw new IllegalStateException("Cannot refund an already refunded payment.");
        }

        // Update payment status to refunded in the database
        String updatePaymentQuery = "UPDATE Payment SET payment_status = ? WHERE payment_ID = ?";
        PreparedStatement statement = connection.prepareStatement(updatePaymentQuery);
        statement.setBoolean(1, false); // Mark as refunded
        statement.setInt(2, payment.getPaymentId());
        statement.executeUpdate();

        // Reflect the refund in the Payment object
        payment.setPaymentStatus(false);
    }

    // Validate a payment record
    public boolean validatePayment(Payment payment) throws SQLException {
        String query = "SELECT * FROM Payment WHERE payment_ID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, payment.getPaymentId());
        ResultSet resultSet = statement.executeQuery();

        // If a matching record is found, the payment is valid
        return resultSet.next();
    }

    // Generate a receipt for a payment
    public Receipt generateReceipt(Payment payment, Ticket ticket, String username) throws SQLException {
        double total = payment.getAmount(); // The amount for one ticket
        Date date = new Date();

        // Insert receipt into the database
        String insertReceiptQuery = "INSERT INTO Receipt (ticket_ID, numTickets, total, date, user) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertReceiptQuery, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, ticket.getTicket_number());
        statement.setInt(2, 1); // Single ticket for this receipt
        statement.setDouble(3, total);
        statement.setDate(4, new java.sql.Date(date.getTime()));
        statement.setString(5, username);
        statement.executeUpdate();

        // Retrieve generated receipt ID
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int receiptId = generatedKeys.getInt(1);
            Receipt receipt = new Receipt(receiptId, 1, total, date, username);

            // Associate the receipt with the ticket
            ticket.setReceipt(receipt);
            return receipt;
        } else {
            throw new SQLException("Failed to generate receipt, no ID obtained.");
        }
    }

    // Helper method to check if a user has valid card information
    private boolean isValidCard(String username) throws SQLException {
        String query = "SELECT * FROM BankCardInfo b " +
                       "INNER JOIN RegisteredUser r ON b.registered_user_ID = r.registered_user_ID " +
                       "INNER JOIN User u ON r.user_ID = u.user_ID " +
                       "WHERE u.username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        // Return true if card information is found
        return resultSet.next();
    }
}
