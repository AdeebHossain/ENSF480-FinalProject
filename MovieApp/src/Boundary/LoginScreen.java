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
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            int role = loginController.validateUser(email, password);

            if (role == 1) { // Admin
                JOptionPane.showMessageDialog(frame, "Welcome, Admin!");
                frame.dispose();
                new AdminFrontPage(); // Navigate to Admin Front Page
            } else if (role == 0) { // Registered or Ordinary User
                JOptionPane.showMessageDialog(frame, "Welcome!");
                frame.dispose();
                FrontPage.main(new String[]{});
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginScreen::new);
    }
}
