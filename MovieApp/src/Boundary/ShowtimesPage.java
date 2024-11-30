package Boundary;

import javax.swing.*;
import java.awt.*;

public class ShowtimesPage {

    public static void show(String movieTitle, String[] showtimes, String movieDescription, ImageIcon movieImage) {
        JFrame frame = new JFrame("Showtimes - " + movieTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        frame.add(mainPanel);

        // Top Panel: Movie image and description
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Movie image
        JLabel imageLabel = new JLabel(movieImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // Movie description
        JTextArea descriptionArea = new JTextArea(movieDescription);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

        // Add components to the top panel
        topPanel.add(imageLabel);
        topPanel.add(descriptionScrollPane);

        // Bottom Panel: Dropdown menu and buttons
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Dropdown menu
        JComboBox<String> showtimeDropdown = new JComboBox<>(showtimes);
        showtimeDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel dropdownLabel = new JLabel("Select a Showtime:", JLabel.CENTER);
        dropdownLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel dropdownPanel = new JPanel();
        dropdownPanel.setLayout(new BoxLayout(dropdownPanel, BoxLayout.Y_AXIS));
        dropdownPanel.add(dropdownLabel);
        dropdownPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        dropdownPanel.add(showtimeDropdown);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton backButton = new JButton("Back to Movie Selection");
        JButton seatSelectionButton = new JButton("Proceed to Seat Selection");

        buttonPanel.add(backButton);
        buttonPanel.add(seatSelectionButton);

        // Add dropdown and buttons to the bottom panel
        bottomPanel.add(dropdownPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Seat selection panel (hidden initially)
        JPanel seatPanel = new JPanel(new GridLayout(5, 5, 5, 5)); // 5x5 seat grid
        seatPanel.setVisible(false);

        // Create seat buttons
        for (char row = 'A'; row <= 'E'; row++) {
            for (int col = 1; col <= 5; col++) {
                String seatLabel = row + String.valueOf(col);
                JButton seatButton = new JButton(seatLabel);

                seatButton.addActionListener(e -> {
                    JOptionPane.showMessageDialog(frame, "You selected seat " + seatLabel);
                });

                seatPanel.add(seatButton);
            }
        }

        // Add panels to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.CENTER);
        mainPanel.add(seatPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        // Button actions
        backButton.addActionListener(e -> {
            frame.dispose();
            FrontPage.main(null); // Return to the FrontPage
        });

        seatSelectionButton.addActionListener(e -> {
            dropdownPanel.setVisible(false); // Hide dropdown menu
            buttonPanel.setVisible(false);  // Hide navigation buttons
            seatPanel.setVisible(true);     // Show seat selection
        });
    }
}
