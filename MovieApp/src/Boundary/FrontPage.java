package Boundary;

import javax.swing.*;

public class FrontPage {

    public FrontPage() {
        // Create the main frame
        JFrame frame = new JFrame("Movie App - Front Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        JLabel welcomeLabel = new JLabel("Welcome to the Movie App!", SwingConstants.CENTER);
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(18.0f));
        frame.add(welcomeLabel);

        frame.setVisible(true);
    }
}
