package Boundary;

import javax.swing.*;
import java.awt.*;

public class FrontPage {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Movie App - Front Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Create the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10)); // Use BorderLayout to position components

        // Create a top panel for the title and description
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS)); // Stack components vertically

        // Add the welcome message at the top
        JLabel welcomeLabel = new JLabel("Welcome to AcmePlex", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Make the title bold and bigger
        topPanel.add(welcomeLabel);

        // Add a description below the welcome message
        JLabel descriptionLabel = new JLabel("Here are the currently airing movies", SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.ITALIC, 18)); // Italicize the description
        topPanel.add(descriptionLabel);

        // Add the top panel to the main panel
        panel.add(topPanel, BorderLayout.NORTH);

        // Create the movie grid panel to display the movie posters
        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new GridLayout(2, 4, 10, 10)); // 2 rows, 4 columns for 8 movies

        // Add movie posters as buttons (placeholder buttons for now)
        for (int i = 1; i <= 8; i++) {
            final int movieIndex = i;  // Declare movieIndex as final to use inside ActionListener

            JButton movieButton = new JButton("Movie " + movieIndex);  // Placeholder for movie poster
            movieButton.setPreferredSize(new Dimension(150, 200)); // Set size of movie posters
            movieButton.setBackground(Color.LIGHT_GRAY); // Set background color for visibility
            movieButton.setFocusPainted(false); // Removes the border when button is clicked

            // Add action listener to each movie button (click to view more details)
            movieButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(frame, "You clicked on Movie " + movieIndex); // Placeholder action
            });

            // Add button to the movie panel
            moviePanel.add(movieButton);
        }

        // Add the movie panel to the main panel
        panel.add(moviePanel, BorderLayout.CENTER);

        // Add the main panel to the frame
        frame.add(panel);

        // Show the frame
        frame.setVisible(true);
    }
}
