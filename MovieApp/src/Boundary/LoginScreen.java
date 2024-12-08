package Boundary;

import javax.swing.*;
import java.awt.*;
import Controller.LoginController;

public class LoginScreen {

    private final LoginController loginController;

    public LoginScreen() {
        loginController = new LoginController();
        initLoginScreen();
    }

    private void initLoginScreen() {
        JFrame frame = new JFrame("Login Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

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

        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        JPasswordField passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        JButton loginButton = new JButton("Login");
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(loginButton, constraints);

        JButton guestButton = new JButton("Continue as Guest");
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(guestButton, constraints);

        // Button for cancelling bookings
        JButton cancelBookingsButton = new JButton("Cancel Bookings");
        constraints.gridx = 1;
        constraints.gridy = 4;
        panel.add(cancelBookingsButton, constraints);

        JButton signUpButton = new JButton("Sign Up");
        constraints.gridx = 1;
        constraints.gridy = 5;
        panel.add(signUpButton, constraints);


        // Action for Register button
        cancelBookingsButton.addActionListener(e -> {
            frame.dispose(); // Close current frame
            new BookingCancellation(); // Open the BookingCancellation screen
        });

        // Action for Login button
        loginButton.addActionListener(e -> {
            String email = usernameField.getText();
            String password = new String(passwordField.getPassword());
        
            // Validate user login
            int role = loginController.validateUser(email, password);
            boolean feeDue = loginController.checkAnnualFee(email);
        
            if (role == 0) { // Admin
                JOptionPane.showMessageDialog(frame, "Welcome, Admin!");
                frame.dispose();
                new AdminFrontPage();
            } else if (role == 1) { // Registered User
                if (feeDue) {
                    JOptionPane.showMessageDialog(frame, "You still need to pay your $20 annual fee. The amount will be withdrawn from the card on file.", "Payment Reminder", JOptionPane.WARNING_MESSAGE);
                }
        
                // Show a popup with general movie news
                JTextArea newsArea = new JTextArea("🎬 Latest Movie News 🎥\n\n"
                    + "- Upcoming release: 'Blockbuster Hit' on 2024-12-25\n"
                    + "- Oscar nominations announced: Best Picture includes 'Cinematic Marvel'\n"
                    + "- Local theatres hosting a Classic Movie Night next week\n\n"
                    + "Enjoy your movie experience!");
                newsArea.setEditable(false);
                newsArea.setLineWrap(true);
                newsArea.setWrapStyleWord(true);
        
                JScrollPane scrollPane = new JScrollPane(newsArea);
                scrollPane.setPreferredSize(new Dimension(300, 200));
        
                JOptionPane.showMessageDialog(frame, scrollPane, "Movie News", JOptionPane.INFORMATION_MESSAGE);
        
                frame.dispose();
                FrontPage.main(new String[]{});
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });        

        // Action for Continue as Guest button
        guestButton.addActionListener(e -> {
            frame.dispose();
            FrontPage.main(null);
        });

        signUpButton.addActionListener(e -> {
            frame.dispose(); // Close the login screen
            new SignupScreen(); // Open the SignUpScreen (you need to create this class)
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginScreen::new);
    }
}
