package Boundary;

import javax.swing.*;
import java.awt.*;

public class LoginScreen {

    public LoginScreen() {
        // Create the main frame
        JFrame frame = new JFrame("Login Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Create the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Username/email label and text field
        JLabel usernameLabel = new JLabel("Email:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(usernameLabel, constraints);

        JTextField usernameField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(usernameField, constraints);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        JPasswordField passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        // Login button
        JButton loginButton = new JButton("Login");
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(loginButton, constraints);

        // Continue as Guest button
        JButton guestButton = new JButton("Continue as Guest");
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(guestButton, constraints);

        // Register button
        JButton registerButton = new JButton("Register");
        constraints.gridx = 1;
        constraints.gridy = 4;
        panel.add(registerButton, constraints);

        // Cancel Bookings button
        JButton cancelBookingsButton = new JButton("Cancel Bookings");
        constraints.gridx = 1;
        constraints.gridy = 5;
        panel.add(cancelBookingsButton, constraints);

        // Action for Register button
        registerButton.addActionListener(e -> {
            frame.dispose(); // Close current frame
            new SignupScreen(); // Open signup screen
        });

        // Action for Continue as Guest button
        guestButton.addActionListener(e -> {
            frame.dispose(); // Close current frame
            FrontPage.main(null); // Open the FrontPage
        });

        // Action for Login button
        loginButton.addActionListener(e -> {
            frame.dispose(); // Close current frame
            FrontPage.main(null); // Open the FrontPage
        });

        // Action for Cancel Bookings button
        cancelBookingsButton.addActionListener(e -> {
            frame.dispose(); // Close current frame
            new BookingCancellation(); // Open BookingCancellation screen
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginScreen::new);
    }
}
