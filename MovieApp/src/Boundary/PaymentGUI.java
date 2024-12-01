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

        // Email input section
        JLabel emailLabel = new JLabel("Enter your email:", SwingConstants.CENTER);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField emailField = new JTextField(20); // Email input field
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));

        // OK button to confirm email entry
        JButton okButton = new JButton("OK");
        okButton.setBackground(new Color(34, 139, 34)); // Green button
        okButton.setFont(new Font("Arial", Font.BOLD, 14));
        okButton.setForeground(Color.WHITE);
        okButton.setEnabled(false); // Initially disabled

        // Card information section (Initially hidden)
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

        // Payment button
        JButton payButton = new JButton("Pay Now");
        payButton.setBackground(new Color(34, 139, 34)); // Green button
        payButton.setFont(new Font("Arial", Font.BOLD, 14));
        payButton.setForeground(Color.WHITE);
        payButton.setEnabled(false); // Initially disabled

        // Action listener to enable the OK button when a valid email is entered
        emailField.addCaretListener(e -> {
            String email = emailField.getText();
            if (isValidEmail(email)) {
                okButton.setEnabled(true); // Enable the OK button if email is valid
            } else {
                okButton.setEnabled(false); // Disable the button if email is not valid
            }
        });

        // Action listener for the OK button
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enable the "Pay" button and show the payment fields after OK
                payButton.setEnabled(true);
                cardInfoLabel.setVisible(true);
                cardNumberField.setVisible(true);
                expiryField.setVisible(true);
                cvvField.setVisible(true);
            }
        });

        // Action listener for the "Pay" button
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

        // Email and OK button beside each other, both centered
        gbc.gridy++;
        frame.add(emailLabel, gbc);

        gbc.gridy++;
        frame.add(emailField, gbc);

        // Initially hidden card information section
        gbc.gridy++;
        frame.add(cardInfoLabel, gbc);
        cardInfoLabel.setVisible(false); // Hide initially

        gbc.gridy++;
        frame.add(cardNumberField, gbc);
        cardNumberField.setVisible(false); // Hide initially

        gbc.gridy++;
        frame.add(expiryField, gbc);
        expiryField.setVisible(false); // Hide initially

        gbc.gridy++;
        frame.add(cvvField, gbc);
        cvvField.setVisible(false); // Hide initially

        // Payment button below email and OK button, still centered
        gbc.gridy++;
        frame.add(payButton, gbc);

        frame.setVisible(true);
    }

    // Method to validate the email format
    private boolean isValidEmail(String email) {
        // Basic email validation: checks if email contains '@' and '.'
        return email.contains("@") && email.contains(".");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentGUI("Example Movie", 2, 12.50));
    }
}
