package Boundary;

import javax.swing.*;
import java.awt.*;

public class ShowtimesPage {

    public static void show(JFrame parentFrame, String movieTitle, String[] showtimes) {
        // Dispose of the current frame to ensure only one page is open
        parentFrame.dispose();

        // Create a new frame for the showtimes page
        JFrame frame = new JFrame("Showtimes - " + movieTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Create the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10)); // Use BorderLayout to position components

        // Add a title at the top
        JLabel titleLabel = new JLabel("Showtimes for " + movieTitle, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Create a list of showtimes in the center
        JPanel showtimesPanel = new JPanel();
        showtimesPanel.setLayout(new BoxLayout(showtimesPanel, BoxLayout.Y_AXIS));

        for (String showtime : showtimes) {
            JLabel showtimeLabel = new JLabel(showtime, JLabel.CENTER);
            showtimeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            showtimesPanel.add(showtimeLabel);
        }

        panel.add(showtimesPanel, BorderLayout.CENTER);

        // Add a back button at the bottom
        JButton backButton = new JButton("Back to Movies");
        backButton.addActionListener(e -> {
            frame.dispose();
            FrontPage.main(null); // Return to the front page
        });

        panel.add(backButton, BorderLayout.SOUTH);

        // Add the main panel to the frame
        frame.add(panel);

        // Show the frame
        frame.setVisible(true);
    }
}
