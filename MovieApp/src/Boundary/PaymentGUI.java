package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentGUI {

    private boolean isRegisteredUser = false; // Set this based on check if user is logged in

    public PaymentGUI(String movie, int numTickets, double ticketPrice) {
        // Constants
        double taxRate = 0.05; // 5% tax

        // Calculate total and tax
        double subtotal = numTickets * ticketPrice;
        double tax = subtotal * taxRate;
        double totalAmount = subtotal + tax;

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
            int ticketNumber = 1234; // Dummy ticket number

            // Show receipt popup
            JOptionPane.showMessageDialog(frame,
                    String.format("Receipt\n\nTicket Number: %d\nTotal: $%.2f\nReceipt emailed to: %s",
                            ticketNumber, totalAmount, email),
                    "Receipt", JOptionPane.INFORMATION_MESSAGE);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentGUI("Example Movie", 2, 12.50));
    }
}
