package Boundary;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FrontPage {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Movie App - Front Page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null); // Center the frame on the screen

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout(10, 10)); // Use BorderLayout to position components

            // Top panel for the title and description
            JPanel topPanel = new JPanel();
            topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

            JLabel welcomeLabel = new JLabel("Welcome to AcmePlex", JLabel.CENTER);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
            topPanel.add(welcomeLabel);

            JLabel descriptionLabel = new JLabel("Here are the currently airing movies", JLabel.CENTER);
            descriptionLabel.setFont(new Font("Arial", Font.ITALIC, 18));
            topPanel.add(descriptionLabel);

            panel.add(topPanel, BorderLayout.NORTH);

            // Movie grid panel
            JPanel moviePanel = new JPanel();
            moviePanel.setLayout(new GridLayout(2, 4, 10, 10));

            Map<Integer, String[]> showtimesData = new HashMap<>();
            showtimesData.put(1, new String[]{"10:00 AM", "1:00 PM", "6:00 PM"});
            showtimesData.put(2, new String[]{"11:00 AM", "3:00 PM", "7:30 PM"});
            showtimesData.put(3, new String[]{"9:30 AM", "12:30 PM", "8:00 PM"});
            showtimesData.put(4, new String[]{"10:15 AM", "2:45 PM", "9:00 PM"});
            showtimesData.put(5, new String[]{"11:00 AM", "4:15 PM", "6:45 PM"});
            showtimesData.put(6, new String[]{"10:30 AM", "1:30 PM", "7:00 PM"});
            showtimesData.put(7, new String[]{"12:00 PM", "3:30 PM", "9:30 PM"});
            showtimesData.put(8, new String[]{"9:00 AM", "2:00 PM", "8:30 PM"});

            for (int i = 1; i <= 8; i++) {
                final int movieIndex = i;

                ImageIcon movieIcon = new ImageIcon("../data/movie" + movieIndex + ".jpg");
                Image movieImage = movieIcon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
                movieIcon = new ImageIcon(movieImage);

                JButton movieButton = new JButton(movieIcon);
                movieButton.setPreferredSize(new Dimension(150, 200));
                movieButton.setFocusPainted(false);

                movieButton.addActionListener(e -> {
                    frame.dispose();
                    ShowtimesPage.show(
                                        "Movie " + movieIndex,
                                        showtimesData.get(movieIndex),
                                        "Description of Movie " + movieIndex,
                                        new ImageIcon("path/to/movie" + movieIndex + ".jpg") // Replace with your image paths
                                    );
                });

                moviePanel.add(movieButton);
            }

            panel.add(moviePanel, BorderLayout.CENTER);

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
