package Boundary;

import javax.swing.*;
import java.awt.*;

public class SignupScreen {

    public SignupScreen() {
        // Create the main frame
        JFrame frame = new JFrame("Signup Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        // Create the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Name label and text field
        JLabel nameLabel = new JLabel("Name:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, constraints);

        JTextField nameField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(nameField, constraints);

        // Email label and text field
        JLabel emailLabel = new JLabel("Email:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(emailLabel, constraints);

        JTextField emailField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(emailField, constraints);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(passwordLabel, constraints);

        JPasswordField passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        // Confirm Password label and field
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(confirmPasswordLabel, constraints);

        JPasswordField confirmPasswordField = new JPasswordField(20);
        constraints.gridx = 1;
        panel.add(confirmPasswordField, constraints);

        // Register button
        JButton registerButton = new JButton("Register");
        constraints.gridx = 1;
        constraints.gridy = 4;
        panel.add(registerButton, constraints);

        // "I already have an account" button
        JButton backButton = new JButton("I already have an account");
        constraints.gridx = 1;
        constraints.gridy = 5;
        panel.add(backButton, constraints);

        // Action for Back button
        backButton.addActionListener(e -> {
            frame.dispose(); // Close current frame
            new LoginScreen(); // Open login screen
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
