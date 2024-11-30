package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieGUI {

    public MovieGUI() {
        // Main JFrame setup
        JFrame frame = new JFrame("Movie Booking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Panel for movie selection
        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new GridLayout(3, 1, 10, 10));
        JLabel movieLabel = new JLabel("Select a Movie:", SwingConstants.CENTER);
        String[] movies = {"Movie 1", "Movie 2", "Movie 3"};
        JComboBox<String> movieDropdown = new JComboBox<>(movies);

        // Panel for showtime selection
        JPanel showtimePanel = new JPanel();
        showtimePanel.setLayout(new GridLayout(3, 1, 10, 10));
        JLabel showtimeLabel = new JLabel("Select a Showtime:", SwingConstants.CENTER);
        String[] showtimes = {"12:00 PM", "3:00 PM", "6:00 PM", "9:00 PM"};
        JComboBox<String> showtimeDropdown = new JComboBox<>(showtimes);
        showtimePanel.setVisible(false); // Initially hidden

        // Panel for seat selection and confirmation
        JPanel seatPanel = new JPanel();
        JButton selectSeatButton = new JButton("Select Seats");
        JButton confirmSeatButton = new JButton("Confirm Seats");
        seatPanel.add(selectSeatButton);
        seatPanel.add(confirmSeatButton);
        seatPanel.setVisible(false); // Initially hidden

        // Add components to moviePanel
        moviePanel.add(movieLabel);
        moviePanel.add(movieDropdown);

        // Add components to showtimePanel
        showtimePanel.add(showtimeLabel);
        showtimePanel.add(showtimeDropdown);

        // Add all panels to the frame
        frame.add(moviePanel, BorderLayout.NORTH);
        frame.add(showtimePanel, BorderLayout.CENTER);
        frame.add(seatPanel, BorderLayout.SOUTH);

        // Add action listeners for interactions
        movieDropdown.addActionListener(e -> {
            // When a movie is selected, show the showtime panel
            showtimePanel.setVisible(true);
        });

        showtimeDropdown.addActionListener(e -> {
            // When a showtime is selected, show the seat selection panel
            seatPanel.setVisible(true);
        });

        selectSeatButton.addActionListener(e -> {
            // Popup for seat selection
            JFrame seatFrame = new JFrame("Select Your Seats");
            seatFrame.setSize(300, 300);
            seatFrame.setLayout(new GridLayout(5, 5, 5, 5));

            // Define rows and seats
            char[] rows = {'A', 'B', 'C', 'D', 'E'};
            int seatsPerRow = 5;

            // Add buttons for each seat
            for (char row : rows) {
                for (int seat = 1; seat <= seatsPerRow; seat++) {
                    String seatLabel = row + String.valueOf(seat);
                    JButton seatButton = new JButton(seatLabel);
                    seatButton.addActionListener(seatEvent -> {
                        JOptionPane.showMessageDialog(seatFrame,
                                "You selected " + seatLabel);
                    });
                    seatFrame.add(seatButton);
                }
            }

            seatFrame.setVisible(true);
        });

        confirmSeatButton.addActionListener(e -> {
            String selectedMovie = "Selected Movie Name"; // Replace with actual selected movie
            int numTickets = 2; // Replace with actual number of tickets
            double ticketPrice = 15.00; // Replace with actual ticket price

            frame.dispose(); // Close current frame
            new PaymentGUI(selectedMovie, numTickets, ticketPrice); // Pass the required arguments
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MovieGUI::new);
    }
}
