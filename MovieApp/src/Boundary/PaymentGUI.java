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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around components

        // Set a background color or image (using a simple color here)
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background

        // Reaffirm movie details
        JLabel movieDetailsLabel = new JLabel(
                String.format("Movie: %s | Tickets: %d", movie, numTickets),
                SwingConstants.CENTER
        );
        movieDetailsLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Bold font for better visibility
        movieDetailsLabel.setForeground(new Color(34, 34, 34)); // Dark text color

        JLabel subtotalLabel = new JLabel(String.format("Subtotal: $%.2f", subtotal), SwingConstants.CENTER);
        subtotalLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel taxLabel = new JLabel(String.format("Tax (5%%): $%.2f", tax), SwingConstants.CENTER);
        taxLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel totalLabel = new JLabel(String.format("Total Amount: $%.2f", totalAmount), SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Card information section
        JLabel cardInfoLabel = new JLabel("Enter Payment Information", SwingConstants.CENTER);
        cardInfoLabel.setFont(new Font("Arial", Font.ITALIC, 14));

        JTextField cardNumberField = new JTextField("Card Number");
        cardNumberField.setFont(new Font("Arial", Font.PLAIN, 14));
        cardNumberField.setColumns(20);

        JTextField expiryField = new JTextField("Expiry Date (MM/YY)");
        expiryField.setFont(new Font("Arial", Font.PLAIN, 14));
        expiryField.setColumns(20);

        JTextField cvvField = new JTextField("CVV");
        cvvField.setFont(new Font("Arial", Font.PLAIN, 14));
        cvvField.setColumns(10);

        // Conditional logic for registered users
        if (isRegisteredUser) {
            cardInfoLabel.setText("Using existing card on file");
            cardNumberField.setText("**** **** **** 1234"); // Masked card number -- get card number from database if user is registered, otherwise prompt for card number 
            cardNumberField.setEditable(false);
            expiryField.setEditable(false);
            cvvField.setEditable(false);
        }

        // Payment button
        JButton payButton = new JButton("Pay");
        payButton.setBackground(new Color(34, 139, 34)); // Green button
        payButton.setFont(new Font("Arial", Font.BOLD, 14));
        payButton.setForeground(Color.WHITE);
        payButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                    String.format("Booking Confirmed!\nMovie: %s\nTickets: %d\nTotal: $%.2f", movie, numTickets, totalAmount)
            );
            frame.dispose(); // Close the payment window
        });

        // Grid layout adjustments
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
        frame.add(cardInfoLabel, gbc);

        gbc.gridy++;
        frame.add(cardNumberField, gbc);

        gbc.gridy++;
        frame.add(expiryField, gbc);

        gbc.gridy++;
        frame.add(cvvField, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        frame.add(payButton, gbc);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentGUI("Example Movie", 2, 12.50));
    }
}
