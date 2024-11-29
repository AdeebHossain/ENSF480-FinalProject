package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentGUI {

    private boolean isRegisteredUser = false; // Set this based on user status

    public PaymentGUI(String movie, int numTickets, double ticketPrice) {
        // Constants
        double taxRate = 0.15; // 15% tax

        // Calculate total and tax
        double subtotal = numTickets * ticketPrice;
        double tax = subtotal * taxRate;
        double totalAmount = subtotal + tax;

        // Main JFrame setup
        JFrame frame = new JFrame("Payment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new GridLayout(8, 1, 10, 10));

        // Reaffirm movie details
        JLabel movieDetailsLabel = new JLabel(
                String.format("Movie: %s | Tickets: %d", movie, numTickets),
                SwingConstants.CENTER
        );
        JLabel subtotalLabel = new JLabel(String.format("Subtotal: $%.2f", subtotal), SwingConstants.CENTER);
        JLabel taxLabel = new JLabel(String.format("Tax (15%%): $%.2f", tax), SwingConstants.CENTER);
        JLabel totalLabel = new JLabel(String.format("Total Amount: $%.2f", totalAmount), SwingConstants.CENTER);

        // Card information section
        JLabel cardInfoLabel = new JLabel("Enter Payment Information", SwingConstants.CENTER);
        JTextField cardNumberField = new JTextField("Card Number");
        JTextField expiryField = new JTextField("Expiry Date (MM/YY)");
        JTextField cvvField = new JTextField("CVV");

        // Conditional logic for registered users
        if (isRegisteredUser) {
            cardInfoLabel.setText("Using existing card on file");
            cardNumberField.setText("**** **** **** 1234"); // Masked card number
            cardNumberField.setEditable(false);
            expiryField.setEditable(false);
            cvvField.setEditable(false);
        }

        // Payment button
        JButton payButton = new JButton("Pay");
        payButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                    String.format("Booking Confirmed!\nMovie: %s\nTickets: %d\nTotal: $%.2f", movie, numTickets, totalAmount)
            );
            frame.dispose(); // Close the payment window
        });

        // Add components to the frame
        frame.add(movieDetailsLabel);
        frame.add(subtotalLabel);
        frame.add(taxLabel);
        frame.add(totalLabel);
        frame.add(cardInfoLabel);

        if (!isRegisteredUser) {
            frame.add(cardNumberField);
            frame.add(expiryField);
            frame.add(cvvField);
        }

        frame.add(payButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentGUI("Example Movie", 2, 12.50));
    }
}
