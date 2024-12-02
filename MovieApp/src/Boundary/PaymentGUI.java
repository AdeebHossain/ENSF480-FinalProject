package Boundary;

import Controller.SeatingController;
import Controller.TicketController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PaymentGUI {

    private boolean isRegisteredUser = false; // Set this based on check if user is logged in
    private List<String[]> selectedSeats; // List to hold selected seats

    // Constructor accepting movie name, number of tickets, and ticket price
    public PaymentGUI(String movie, int numTickets, double ticketPrice, List<String[]> selectedSeats) {
        // Constants
        double taxRate = 0.05; // 5% tax

        // Calculate total and tax
        double subtotal = numTickets * ticketPrice;
        double tax = subtotal * taxRate;
        double totalAmount = subtotal + tax;

        this.selectedSeats = selectedSeats;

        // Main JFrame setup
        JFrame frame = new JFrame("Payment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500); // Adjusted size
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around components
        gbc.anchor = GridBagConstraints.CENTER; // Center components

        // Set a background color
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background

        // Movie details
        JLabel movieDetailsLabel = new JLabel(
                String.format("Movie: %s | Tickets: %d", movie, numTickets),
                SwingConstants.CENTER
        );
        movieDetailsLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel subtotalLabel = new JLabel(String.format("Subtotal: $%.2f", subtotal), SwingConstants.CENTER);
        JLabel taxLabel = new JLabel(String.format("Tax (5%%): $%.2f", tax), SwingConstants.CENTER);
        JLabel totalLabel = new JLabel(String.format("Total Amount: $%.2f", totalAmount), SwingConstants.CENTER);

        // Email input
        JLabel emailLabel = new JLabel("Enter your email:", SwingConstants.CENTER);
        JTextField emailField = new JTextField(20);

        // Payment button
        JButton payButton = new JButton("Pay Now");
        payButton.setEnabled(false); // Initially disabled

        // Enable "Pay Now" when a valid email is entered
        emailField.addCaretListener(e -> {
            String email = emailField.getText();
            payButton.setEnabled(isValidEmail(email));
        });

        // Action listener for "Pay Now" button
        payButton.addActionListener(e -> {
            String email = emailField.getText();
            TicketController ticketController = new TicketController();
            SeatingController seatingController = new SeatingController();
            int ticketNumber = generateTicketNumber(); // Replace with logic to generate ticket number
            boolean seatsReserved = reserveSeats(seatingController);

            if (seatsReserved) {
                // Add ticket to the database
                int theatreId = 1; // Example, this should be dynamic based on the selected theatre
                double ticketPrice = 12.50; // Example, this should be dynamic based on the ticket price
                String showtime = "7:00 PM"; // Example, this should be dynamic based on the selected showtime
                ticketController.addTicket(email, ticketNumber, theatreId, ticketPrice, showtime);

                // Show receipt popup
                JOptionPane.showMessageDialog(frame,
                        String.format("Receipt\n\nTicket Number: %d\nTotal: $%.2f\nReceipt emailed to: %s",
                                ticketNumber, totalAmount, email),
                        "Receipt", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "There was an error reserving the seats.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            frame.dispose(); // Close the payment window
        });

        // Grid layout setup
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(movieDetailsLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        frame.add(subtotalLabel, gbc);

        gbc.gridy++;
        frame.add(taxLabel, gbc);

        gbc.gridy++;
        frame.add(totalLabel, gbc);

        gbc.gridy++;
        frame.add(emailLabel, gbc);

        gbc.gridy++;
        frame.add(emailField, gbc);

        gbc.gridy++;
        frame.add(payButton, gbc);

        frame.setVisible(true);
    }

    // Validate email format
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    // Method to reserve selected seats in the database
    private boolean reserveSeats(SeatingController seatingController) {
        boolean allReserved = true;
        for (String[] seat : selectedSeats) {
            String row = seat[0];
            int col = Integer.parseInt(seat[1]);
            if (!seatingController.reserveSeat(row, col)) {
                allReserved = false;
            }
        }
        return allReserved;
    }

    // Method to generate a ticket number (you can replace this with your actual logic)
    private int generateTicketNumber() {
        return (int) (Math.random() * 10000); // Example random ticket number
    }

    public static void main(String[] args) {
        // Example selected seats data
        List<String[]> selectedSeats = List.of(
                new String[]{"A", "1"},
                new String[]{"B", "2"}
        );

        SwingUtilities.invokeLater(() -> new PaymentGUI("Example Movie", 2, 12.50, selectedSeats));
    }
}
