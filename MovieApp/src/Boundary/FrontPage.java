package Boundary;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.List;
import Controller.MovieController;
import Controller.ShowtimeController;

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

            // Fetch movie data from the database
            MovieController movieController = new MovieController();
            ShowtimeController showtimeController = new ShowtimeController();

            List<String[]> movies = movieController.getAllMovies(); // Assuming this method returns a list of [name, description]

            int movieIndex = 0;
            for (String[] movie : movies) {
                final String movieName = movie[0];
                final String movieDescription = movie[1];
                final int movieLength = Integer.parseInt(movie[2]); // Movie length is now parsed as an integer
                
                // Fetch the movie ID to retrieve showtimes
                int movieId = movieController.getMovieIdByName(movieName);

                // Retrieve showtimes using the ShowtimeController
                final String[] showtimes = showtimeController.getShowtimeInfo(movieId);
                // final String[] showtimes = {"10:00 AM", "1:00 PM", "6:00 PM"}; 

                // Placeholder for movie poster images
                final ImageIcon movieIcon;
                Image movieImage = new ImageIcon("../data/movie" + (movieIndex + 1) + ".jpg").getImage()
                    .getScaledInstance(150, 200, Image.SCALE_SMOOTH);
                movieIcon = new ImageIcon(movieImage);

                JButton movieButton = new JButton(movieIcon);
                movieButton.setPreferredSize(new Dimension(150, 200));
                movieButton.setFocusPainted(false);

                movieButton.addActionListener(e -> {
                    frame.dispose();
                    ShowtimesPage.show(
                        movieName,
                        showtimes,
                        movieDescription,
                        movieIcon,
                        movieLength // Send the movie length
                    );
                });

                moviePanel.add(movieButton);
                movieIndex++;
            }

            panel.add(moviePanel, BorderLayout.CENTER);

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}