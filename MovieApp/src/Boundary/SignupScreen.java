package Boundary;

import Controller.SignUpController;

import javax.swing.*;
import java.awt.*;

public class SignupScreen {

    public SignupScreen() {
        // Create the main frame
        JFrame frame = new JFrame("Signup Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);

        // Create the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        JTextField usernameField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(usernameField, constraints);

        // Full Name
        JLabel fullNameLabel = new JLabel("Full Name:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(fullNameLabel, constraints);

        JTextField fullNameField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(fullNameField, constraints);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(emailLabel, constraints);

        JTextField emailField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(emailField, constraints);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(passwordLabel, constraints);

        JPasswordField passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        // Confirm Password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(confirmPasswordLabel, constraints);

        JPasswordField confirmPasswordField = new JPasswordField(20);
        constraints.gridx = 1;
        panel.add(confirmPasswordField, constraints);

        // Card Number
        JLabel cardNumberLabel = new JLabel("Card Number:");
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(cardNumberLabel, constraints);

        JTextField cardNumberField = new JTextField(16);
        constraints.gridx = 1;
        panel.add(cardNumberField, constraints);

        // Expiry Date
        JLabel expiryDateLabel = new JLabel("Expiry Date (MM/YY):");
        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(expiryDateLabel, constraints);

        JTextField expiryDateField = new JTextField(5);
        constraints.gridx = 1;
        panel.add(expiryDateField, constraints);

        // CVV
        JLabel cvvLabel = new JLabel("CVV:");
        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(cvvLabel, constraints);

        JTextField cvvField = new JTextField(3);
        constraints.gridx = 1;
        panel.add(cvvField, constraints);

        // Register Button
        JButton registerButton = new JButton("Register");
        constraints.gridx = 1;
        constraints.gridy = 8;
        panel.add(registerButton, constraints);

        // "I already have an account" Button
        JButton backButton = new JButton("I already have an account");
        constraints.gridx = 1;
        constraints.gridy = 9;
        panel.add(backButton, constraints);

        // Action for Back Button
        backButton.addActionListener(e -> {
            frame.dispose(); // Close current frame
            new LoginScreen(); // Open login screen
        });

        // Action for Register Button
        registerButton.addActionListener(e -> {
            // Get the data from the fields
            String username = usernameField.getText();
            String fullName = fullNameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String cardNumber = cardNumberField.getText();
            String expiryDate = expiryDateField.getText();
            String cvv = cvvField.getText();

            // Validate inputs
            if (username.isEmpty() || fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                cardNumber.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Use the SignUpController to register the user and add their card
            SignUpController signUpController = new SignUpController();
            boolean userAdded = signUpController.addRegUser(fullName, email, password);

            if (userAdded) {
                boolean cardAdded = signUpController.AddRegCard(fullName, cardNumber, "CVV", expiryDate); // Card number and expiry are passed for card addition

                if (cardAdded) {
                    JOptionPane.showMessageDialog(frame, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose(); // Close the signup frame
                    new LoginScreen(); // Open the login screen
                } else {
                    JOptionPane.showMessageDialog(frame, "Card could not be added", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "User already exists", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignupScreen::new);
    }
}
